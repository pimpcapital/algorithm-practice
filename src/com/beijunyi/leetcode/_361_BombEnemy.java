package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum
 * enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the
 * wall is too strong to be destroyed.
 *
 * Note that you can only put the bomb at an empty cell.
 *
 * Example:
 *   For the given grid
 *   0 E 0 0
 *   E 0 W E
 *   0 E 0 0
 *   return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */
public class _361_BombEnemy implements Medium, PremiumQuestion {

  public interface Solution {
    int maxKilledEnemies(char[][] grid);
  }

  public static class Solution1 implements Solution {
    @Override
    public int maxKilledEnemies(char[][] grid) {
      int rows = grid.length;
      int cols = rows != 0 ? grid[0].length : 0;
      int max = 0;
      if(rows != 0 && cols != 0) {
        int[][] hc = horizontalCounts(grid);
        int[][] vc = verticalCounts(grid);
        for(int r = 0; r < rows; r++) {
          for(int c = 0; c < cols; c++) {
            if(grid[r][c] == '0') {
              max = Math.max(max, hc[r][c] + vc[r][c]);
            }
          }
        }
      }
      return max;
    }

    private static int[][] horizontalCounts(char[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;
      int[][] ret = new int[rows][cols];
      for(int r = 0; r < rows; r++) {
        int start = 0;
        int end = 0;
        int count = 0;
        while(end <= cols) {
          char cell = end < cols ? grid[r][end] : 'W';
          switch(cell) {
            case 'E':
              count++;
              break;
            case 'W':
              for(int c = start; c < end; c++) {
                ret[r][c] = count;
              }
              count = 0;
              start = end + 1;
              break;
          }
          end++;
        }
      }
      return ret;
    }

    private static int[][] verticalCounts(char[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;
      int[][] ret = new int[rows][cols];
      for(int c = 0; c < cols; c++) {
        int start = 0;
        int end = 0;
        int count = 0;
        while(end <= rows) {
          char cell = end < rows ? grid[end][c] : 'W';
          switch(cell) {
            case 'E':
              count++;
              break;
            case 'W':
              for(int r = start; r < end; r++) {
                ret[r][c] = count;
              }
              count = 0;
              start = end + 1;
              break;
          }
          end++;
        }
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution {
    public int maxKilledEnemies(char[][] grid) {
      if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

      int m = grid.length;
      int n = grid[0].length;
      int[][] dp = new int[m][n];

      // from left to right
      for (int i = 0; i < m; i++) {
        int current = 0;
        for (int j = 0; j < n; j++)
          current = process(grid, dp, i, current, j);
      }
      // from top to bottom
      for (int j = 0; j < n; j++) {
        int current = 0;
        for (int i = 0; i < m; i++)
          current = process(grid, dp, i, current, j);
      }
      // from right to left
      for (int i = 0; i < m; i++) {
        int current = 0;
        for (int j = n - 1; j >= 0; j--)
          current = process(grid, dp, i, current, j);
      }
      int ans = 0;
      // from bottom to top
      for (int j = 0; j < n; j++) {
        int current = 0;
        for (int i = m - 1; i >= 0; i--) {
          current = process(grid, dp, i, current, j);
          if (grid[i][j] == '0')  ans = Math.max(ans, dp[i][j]);
        }
      }

      return ans;
    }

    private static int process(char[][] c, int[][] dp, int i, int current, int j) {
      if (c[i][j] == 'W') current = 0;
      if (c[i][j] == 'E') current++;
      dp[i][j] += current;
      return current;
    }
  }

  public static void main(String args[]) {
    char[][] grid;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      grid = new char[][] {
        {'0', 'E', '0', '0'},
        {'E', '0', 'W', 'E'},
        {'0', 'E', '0', '0'}
      };
      result = s.maxKilledEnemies(grid);
      System.out.println(result);
    }
  }

}
