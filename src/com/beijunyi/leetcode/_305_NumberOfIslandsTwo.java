package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns
 * the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after
 * each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 * vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *   Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 *   Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *     0 0 0
 *     0 0 0
 *     0 0 0
 *   Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *     1 0 0
 *     0 0 0   Number of islands = 1
 *     0 0 0
 *   Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *     1 1 0
 *     0 0 0   Number of islands = 1
 *     0 0 0
 *   Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *     1 1 0
 *     0 0 1   Number of islands = 2
 *     0 0 0
 *   Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *     1 1 0
 *     0 0 1   Number of islands = 3
 *     0 1 0
 *   We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *   Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class _305_NumberOfIslandsTwo implements Hard {

  public interface Solution {
    List<Integer> numIslands2(int m, int n, int[][] positions);
  }

  public static class Solution1 implements Solution {

    private static final int[][] NEIGHBOURS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int rows;
    private int cols;
    private int[] roots;
    private int count;

    @Override
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
      rows = m;
      cols = n;
      roots = new int[m * n];
      count = 0;
      Arrays.fill(roots, -1);
      List<Integer> ret = new ArrayList<>();
      for(int[] land : positions) {
        union(land);
        ret.add(count);
      }
      return ret;
    }

    private void union(int[] land) {
      int r = land[0];
      int c = land[1];
      int index = r * cols + c;
      roots[index] = index;
      count++;
      for(int[] neighbour : NEIGHBOURS) {
        int nr = r + neighbour[0];
        int nc = c + neighbour[1];
        if(nr < 0 || nc < 0 || nr == rows || nc == cols) continue;
        int nRoot = find(nr * cols + nc);
        if(nRoot == -1) continue;
        if(nRoot != index) {
          roots[nRoot] = index;
          count--;
        }
      }
    }

    private int find(int index) {
      if(roots[index] != -1) {
        while(roots[index] != index) {
          roots[index] = roots[roots[index]];
          index = roots[index];
        }
      }
      return roots[index];
    }

  }

  public static class Solution2 implements Solution {

    private int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    @Override
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
      UnionFind2D islands = new UnionFind2D(m, n);
      List<Integer> ans = new ArrayList<>();
      for (int[] position : positions) {
        int x = position[0], y = position[1];
        int p = islands.add(x, y);
        for (int[] d : dir) {
          int q = islands.getID(x + d[0], y + d[1]);
          if (q > 0 && !islands.find(p, q))
            islands.unite(p, q);
        }
        ans.add(islands.size());
      }
      return ans;
    }
  }

  private static class UnionFind2D {
    private int[] id;
    private int[] sz;
    private int m, n, count;

    public UnionFind2D(int m, int n) {
      this.count = 0;
      this.n = n;
      this.m = m;
      this.id = new int[m * n + 1];
      this.sz = new int[m * n + 1];
    }

    public int index(int x, int y) { return x * n + y + 1; }

    public int size() { return this.count; }

    public int getID(int x, int y) {
      if (0 <= x && x < m && 0<= y && y < n)
        return id[index(x, y)];
      return 0;
    }

    public int add(int x, int y) {
      int i = index(x, y);
      id[i] = i; sz[i] = 1;
      ++count;
      return i;
    }

    public boolean find(int p, int q) {
      return root(p) == root(q);
    }

    public void unite(int p, int q) {
      int i = root(p), j = root(q);
      if (sz[i] < sz[j]) { //weighted quick union
        id[i] = j; sz[j] += sz[i];
      } else {
        id[j] = i; sz[i] += sz[j];
      }
      --count;
    }

    private int root(int i) {
      for (;i != id[i]; i = id[i])
        id[i] = id[id[i]]; //path compression
      return i;
    }
  }

  public static void main(String args[]) {
    int m;
    int n;
    int[][] positions;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      m = 3;
      n = 3;
      positions = new int[][]{
        {0, 0}, {0, 1}, {1, 2}, {2, 1}
      };
      result = s.numIslands2(m, n, positions);
      System.out.println(result); 
      
      m = 3;
      n = 3;
      positions = new int[][]{
        {0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}
      };
      result = s.numIslands2(m, n, positions);
      System.out.println(result);
    }
  }

}
