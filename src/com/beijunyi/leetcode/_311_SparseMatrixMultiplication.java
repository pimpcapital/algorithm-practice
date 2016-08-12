package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *   A = [
 *     [ 1, 0, 0],
 *     [-1, 0, 3]
 *   ]
 *
 *   B = [
 *     [ 7, 0, 0 ],
 *     [ 0, 0, 0 ],
 *     [ 0, 0, 1 ]
 *   ]
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 */
public class _311_SparseMatrixMultiplication implements Medium, PremiumQuestion {

  public interface Solution {
    int[][] multiply(int[][] A, int[][] B);
  }

  public static class Solution1 implements Solution {

    @Override
    public int[][] multiply(int[][] A, int[][] B) {
      if(A.length == 0 || B.length == 0) return new int[0][0];
      assert A[0].length == B.length;
      return multiply(A, B, findSkippableRows(A), findSkippableCols(B));
    }

    private static boolean[] findSkippableRows(int[][] A) {
      boolean[] ret = new boolean[A.length];
      for(int r = 0; r < A.length; r++) {
        boolean allZero = true;
        for(int c = 0; c < A[r].length; c++) {
          if(A[r][c] != 0) {
            allZero = false;
            break;
          }
        }
        ret[r] = allZero;
      }
      return ret;
    }

    private static boolean[] findSkippableCols(int[][] B) {
      boolean[] ret = new boolean[B[0].length];
      for(int c = 0; c < B[0].length; c++) {
        boolean allZero = true;
        for(int[] row : B) {
          if(row[c] != 0) {
            allZero = false;
            break;
          }
        }
        ret[c] = allZero;
      }
      return ret;
    }

    private static int[][] multiply(int[][] A, int[][] B, boolean[] skippedRows, boolean[] skippedCols ) {
      int rows = A.length;
      int cols = B[0].length;
      int[][] ret = new int[rows][cols];
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < cols; c++) {
          if(!skippedRows[r] && !skippedCols[c]) {
            for(int i = 0; i < B.length; i++) {
              ret[r][c] += A[r][i] * B[i][c];
            }
          }
        }
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public int[][] multiply(int[][] A, int[][] B) {
      int m = A.length, n = A[0].length, nB = B[0].length;
      int[][] C = new int[m][nB];

      for(int i = 0; i < m; i++) {
        for(int k = 0; k < n; k++) {
          if(A[i][k] != 0) {
            for(int j = 0; j < nB; j++) {
              C[i][j] += A[i][k] * B[k][j];
            }
          }
        }
      }
      return C;
    }

  }

  public static void main(String args[]) {
    int[][] A;
    int[][] B;
    int[][] result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      A = new int[][] {
        { 1, 0, 0},
        {-1, 0, 3}
      };
      B = new int[][] {
        {7, 0, 0},
        {0, 0, 0},
        {0, 0, 1}
      };
      result = s.multiply(A, B);
      for(int[] row : result) {
        System.out.println(Arrays.toString(row));
      }
    }
  }

}
