package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.UnionFind;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to find the number of connected components in an undirected graph.
 *
 * Example 1:
 *     0          3
 *     |          |
 *     1 --- 2    4
 *   Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 * Example 2:
 *     0           4
 *     |           |
 *     1 --- 2 --- 3
 *   Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 *
 * Note:
 *   You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 *   [1, 0] and thus will not appear together in edges.
 */
public class _323_NumberOfConnectedComponentsInAnUndirectedGraph implements Medium, PremiumQuestion {

  public interface Solution {
    int countComponents(int n, int[][] edges);
  }

  public static class Solution1 implements Solution, DepthFirstSearch {

    @Override
    public int countComponents(int n, int[][] edges) {
      boolean[][] matrix = indexGraph(n, edges);
      int ret = 0;
      for(int i = 0; i < n; i++) {
        if(clearConnectedNodes(i, matrix)) ret++;
      }
      return ret;
    }

    private static boolean[][] indexGraph(int n, int[][] edges) {
      boolean[][] ret = new boolean[n][n];
      for(int[] edge : edges) {
        ret[edge[0]][edge[1]] = ret[edge[1]][edge[0]] = true;
      }
      return ret;
    }

    private static boolean clearConnectedNodes(int current, boolean[][] matrix) {
      boolean[] connections = matrix[current];
      if(connections == null) return false;
      matrix[current] = null;
      for(int i = 0; i < connections.length; i++) {
        boolean connected = connections[i];
        if(connected) clearConnectedNodes(i, matrix);
      }
      return true;
    }

  }

  public static class Solution2 implements Solution, UnionFind {

    @Override
    public int countComponents(int n, int[][] edges) {
      int[] roots = new int[n];
      for(int i = 0; i < n; i++) roots[i] = i;

      for(int[] e : edges) {
        int root1 = find(roots, e[0]);
        int root2 = find(roots, e[1]);
        if(root1 != root2) {
          roots[root1] = root2;  // union
          n--;
        }
      }
      return n;
    }

    private static int find(int[] roots, int id) {
      while(roots[id] != id) {
        roots[id] = roots[roots[id]];  // optional: path compression
        id = roots[id];
      }
      return id;
    }
  }

  public static class Solution3 implements Solution, UnionFind {
    private int[] roots;

    @Override
    public int countComponents(int n, int[][] edges) {
      roots = new int[n];
      Arrays.fill(roots, -1);
      for(int[] edge : edges) {
        if(union(edge[0], edge[1])) n--;
      }
      return n;
    }

    private boolean union(int v1, int v2) {
      int major = find(v1);
      int minor = find(v2);
      if(major == minor) return false;
      roots[minor] = roots[major];
      return true;
    }

    private int find(int v) {
      if(roots[v] == -1) roots[v] = v;
      if(roots[v] == roots[roots[v]]) return roots[v];
      return roots[v] = find(roots[v]);
    }

  }

  public static void main(String args[]) {
    int n;
    int[][] edges;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      n = 5;
      edges = new int[][] {
        {0, 1}, {1, 2}, {3, 4}
      };
      result = s.countComponents(n, edges);
      System.out.println(result);
    }
  }

}
