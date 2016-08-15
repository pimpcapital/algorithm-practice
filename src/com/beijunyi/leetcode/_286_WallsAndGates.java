package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.InPlaceModification;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * 1. -1 - A wall or an obstacle.
 * 2. 0 - A gate.
 * 3. INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that
 *    the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled
 * with INF.
 *
 * For example, given the 2D grid:
 *   INF  -1  0  INF
 *   INF INF INF  -1
 *   INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * After running your function, the 2D grid should be:
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 */
public class _286_WallsAndGates implements Medium, PremiumQuestion {

  public interface Solution {
    void wallsAndGates(int[][] rooms);
  }

  /**
   * BFS solution. Guarantee O(n) runtime
   */
  public static class Solution1 implements Solution, BreadthFirstSearch, InPlaceModification {
    @Override
    public void wallsAndGates(int[][] rooms) {
      List<int[]> gatesPos = findGates(rooms);
      Queue<int[]> q = new LinkedList<>();
      q.addAll(gatesPos);
      while(!q.isEmpty()) {
        int[] cellXY = q.poll();
        int cell = rooms[cellXY[1]][cellXY[0]];
        for(int xd = -1; xd <= 1; xd++) {
          for(int yd = -1; yd <= 1; yd++) {
            if(xd == 0 && yd == 0 || xd != 0 && yd != 0) continue;
            int x = cellXY[0] + xd;
            int y = cellXY[1] + yd;
            if(y < 0 || y >= rooms.length || x < 0 || x >= rooms[0].length) continue;
            if(rooms[y][x] > cell + 1) {
              rooms[y][x] = cell + 1;
              q.offer(new int[] {x, y});
            }
          }
        }
      }

    }

    private static List<int[]> findGates(int[][] rooms) {
      List<int[]> ret = new ArrayList<>();
      for(int y = 0; y < rooms.length; y++) {
        for(int x = 0; x < rooms[y].length; x++) {
          if(rooms[y][x] == 0) ret.add(new int[] {x, y});
        }
      }
      return ret;
    }
  }

  /**
   * DFS solution. O(n^2) runtime but faster for the leetcode test cases
   */
  public static class Solution2 implements Solution, DepthFirstSearch, InPlaceModification {

    @Override
    public void wallsAndGates(int[][] rooms) {
      for(int i = 0; i < rooms.length; ++i) {
        for(int j = 0; j < rooms[0].length; ++j) {
          if(rooms[i][j] == 0) {
            dfs(rooms, i, j, 0);
          }
        }
      }
    }
    private void dfs(int[][] rooms, int i, int j, int distance) {
      if (i < 0 || i >= rooms.length || j < 0 ||  j >= rooms[0].length) return;
      if(rooms[i][j] >= distance) {
        rooms[i][j] = distance;
        dfs(rooms, i + 1, j, distance + 1);
        dfs(rooms, i - 1, j, distance + 1);
        dfs(rooms, i, j + 1, distance + 1);
        dfs(rooms, i, j - 1, distance + 1);
      }
    }
  }

  public static void main(String args[]) {
    int[][] rooms;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      rooms = new int[][]{
        {2147483647,         -1,          0, 2147483647},
        {2147483647, 2147483647, 2147483647,         -1},
        {2147483647,         -1, 2147483647,         -1},
        {         0,         -1, 2147483647, 2147483647}
      };
      s.wallsAndGates(rooms);
      for(int[] row : rooms) {
        System.out.println(Arrays.toString(row));
      }
      System.out.println();
    }
  }

}
