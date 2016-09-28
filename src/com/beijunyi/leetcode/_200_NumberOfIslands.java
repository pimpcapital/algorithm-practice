package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 *
 * Example 1:
 *   11110
 *   11010
 *   11000
 *   00000
 *   Answer: 1
 *
 * Example 2:
 *   11000
 *   11000
 *   00100
 *   00011
 *   Answer: 3
 */
public class _200_NumberOfIslands implements Medium, Important {

  public interface Solution {
    int numIslands(char[][] grid);
  }

  public static class Solution1 implements Solution, DepthFirstSearch {

    @Override
    public int numIslands(char[][] grid) {
      int rows = grid.length;
      if(rows == 0) return 0;
      int cols = grid[0].length;

      int count = 0;
      for(int r = 0; r < rows; r++) {
        char[] row = grid[r];
        for(int c = 0; c < cols; c++) {
          char tile = row[c];
          if(tile == '1') {
            sinkIsland(grid, r, c, rows, cols);
            count++;
          }
        }
      }
      return count;
    }

    private static void sinkIsland(char[][] grid, int r, int c, int rows, int cols) {
      if(grid[r][c] == '0') return;
      grid[r][c] = '0';
      if(r - 1 >= 0) sinkIsland(grid, r - 1, c, rows, cols);
      if(r + 1 < rows) sinkIsland(grid, r + 1, c, rows, cols);
      if(c - 1 >= 0) sinkIsland(grid, r, c - 1, rows, cols);
      if(c + 1 < cols) sinkIsland(grid, r, c + 1, rows, cols);
    }

  }

  public static void main(String args[]) {
    char[][] grid;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      grid = new char[][] {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
      };
      result = s.numIslands(grid);
      System.out.println(result);
    }
  }

}
