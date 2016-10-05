package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can
 * only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 *
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 *   1 - 0 - 2 - 0 - 1
 *   |   |   |   |   |
 *   0 - 0 - 0 - 0 - 0
 *   |   |   |   |   |
 *   0 - 0 - 1 - 0 - 0
 *   The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 *
 * Note:
 *   There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class _317_ShortestDistanceFromAllBuildings implements Hard, PremiumQuestion {

  public interface Solution {
    int shortestDistance(int[][] grid);
  }

  public static class Solution1 implements Solution {

    static final int[] delta = new int[]{0, 1, 0, -1, 0};
    int min = Integer.MAX_VALUE;

    @Override
    public int shortestDistance(int[][] grid) {
      int[][] dist = new int[grid.length][grid[0].length];
      int start = 1;
      for(int i = 0; i < grid.length; i++) {
        for(int j = 0; j < grid[0].length; j++) {
          if(grid[i][j] == 1) {
            bfsVisit(grid, dist, i, j, --start);
          }
        }
      }
      return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void bfsVisit(int[][] grid, int[][] dist, int row, int col, int start) {
      Queue<int[]> que = new ArrayDeque<>();
      que.offer(new int[]{row, col});
      int level = 0;
      min = Integer.MAX_VALUE;
      while(!que.isEmpty()) {
        int size = que.size();
        level++;
        for(int k = 0; k < size; k++) {
          int[] node = que.poll();
          for(int i = 1; i < delta.length; i++) {
            int newRow = node[0] + delta[i - 1];
            int newCol = node[1] + delta[i];
            if(newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == start) {
              que.offer(new int[]{newRow, newCol});
              dist[newRow][newCol] += level;
              min = Math.min(min, dist[newRow][newCol]);
              grid[newRow][newCol]--;
            }
          }
        }
      }
    }

  }

  public static void main(String args[]) {
    int[][] grid;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      grid = new int[][]{
        {1, 0, 2, 0, 1},
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0}
      };
      result = s.shortestDistance(grid);
      System.out.println(result);
    }
  }

}
