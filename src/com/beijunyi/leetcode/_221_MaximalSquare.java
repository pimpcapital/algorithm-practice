package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *   1 0 1 0 0
 *   1 0 1 1 1
 *   1 1 1 1 1
 *   1 0 0 1 0
 *   Return 4.
 */
public class _221_MaximalSquare implements Medium {

  public interface Solution {
    int maximalSquare(char[][] matrix);
  }

  public static class Solution1 implements Solution, DynamicPrograming {
    @Override
    public int maximalSquare(char[][] matrix) {
      int rows = matrix.length;
      int cols = rows == 0 ? 0 : matrix[0].length;
      if(rows == 0 || cols == 0) return 0;

      int[][] cache = new int[rows][cols];
      int max = 0;
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < cols; c++) {
          if(matrix[r][c] == '1') {
            int dimension = 1;
            if(r > 0 && c > 0) {
              dimension += Math.min(cache[r - 1][c - 1], Math.min(cache[r][c - 1], cache[r - 1][c]));
            }
            cache[r][c] = dimension;
            max = Math.max(dimension, max);
          }
        }
      }

      return max * max;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public int maximalSquare(char[][] matrix) {
      int rows = matrix.length;
      int cols = rows == 0 ? 0 : matrix[0].length;
      if(rows == 0 || cols == 0) return 0;

      int[] lastRow = new int[cols];
      int max = 0;
      for(int r = 0; r < rows; r++) {
        int[] thisRow = new int[cols];
        for(int c = 0; c < cols; c++) {
          if(matrix[r][c] == '1') {
            int dimension = 1;
            if(r > 0 && c > 0) {
              dimension += Math.min(lastRow[c - 1], Math.min(thisRow[c - 1], lastRow[c]));
            }
            thisRow[c] = dimension;
            max = Math.max(dimension, max);
          }
        }
        lastRow = thisRow;
      }

      return max * max;
    }
  }

  public static void main(String args[]) {
    char[][] matrix;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      matrix = new char[][]{
        {'1', '0', '1', '0', '0'},
        {'1', '0', '1', '1', '1'},
        {'1', '1', '1', '1', '1'},
        {'1', '0', '0', '1', '0'}
      };
      result = s.maximalSquare(matrix);
      System.out.println(result);
    }
  }

}
