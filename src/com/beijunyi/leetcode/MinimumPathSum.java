package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum implements Medium {

  public static class Solution {
    public int minPathSum(int[][] grid) {
      int r = grid.length;
      int c = r == 0 ? 0 : grid[0].length;
      if(r == 0 || c == 0)
        return 0;
      for(int i = 0; i < r; i++) {
        for(int j = 0; j < c; j++) {
          if(i > 0 && j > 0)
            grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
          else if(j > 0)
            grid[i][j] = grid[i][j - 1] + grid[i][j];
          else if(i > 0)
            grid[i][j] = grid[i - 1][j] + grid[i][j];
          else
            grid[i][j] = grid[i][j];
        }
      }
      return grid[r - 1][c - 1];
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().minPathSum(new int[][] {
                                                               {1, 2},
                                                               {1, 1}
    }));
  }


}
