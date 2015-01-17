package com.beijunyi.leetcode;

import java.util.Arrays;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 *
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {

  public static class Solution1 {
    public void setZeroes(int[][] matrix) {
      boolean zeroPrevRow = false;
      for(int i = 0; i <= matrix.length; i++) {
        boolean zeroRow = false;
        if(i < matrix.length) {
          int[] row = matrix[i];
          for(int j = 0; j < row.length; j++) {
            if(row[j] == 0)
              zeroRow = true;
            if(i != 0) {
              if(matrix[i - 1][j] == 0)
                row[j] = 0;
              else if(row[j] == 0) {
                for(int k = 0; k < i; k++)
                  matrix[k][j] = 0;
              }
            }
          }
        }
        if(zeroPrevRow) {
          int[] prevRow = matrix[i - 1];
          Arrays.fill(prevRow, 0);
        }
        zeroPrevRow = zeroRow;
      }
    }
  }

  public static class Solution2 {
    public void setZeroes(int[][] matrix) {
      int rowTemp = -1;   // select a row to store the column indices for the zero element
      int colTemp = -1;   // select a column to store the row indices for the zero element

      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          if (matrix[i][j] == 0) {
            // find the first zero element
            if (rowTemp == -1) {
              rowTemp = i;
              colTemp = j;
            }
            // update indice in the row and column temp
            else {
              matrix[rowTemp][j] = 0;
              matrix[i][colTemp] = 0;
            }
          }
        }
      }
      // no zero in the matrix
      if (rowTemp == -1)
        return;
      // set rows to zero
      for (int i = 0; i < matrix.length; i++) {
        if (i == rowTemp)   // skip the temp row
          continue;
        if (matrix[i][colTemp] == 0) {
          for (int j = 0; j < matrix[0].length; j++)
            matrix[i][j] = 0;
        }
      }
      // set columns to zero
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[rowTemp][j] == 0) {
          for (int i = 0; i < matrix.length; i++)
            matrix[i][j] = 0;
        }
      }
      // set the final temp row to zero
      for (int j = 0; j < matrix[0].length; j++)
        matrix[rowTemp][j] = 0;
    }
  }

  public static void main(String args[]) {
    int[][] input1 = new int[][] {
                                  {1, 1, 0},
                                  {1, 1, 1},
                                  {0, 1, 1},
    };
    new Solution1().setZeroes(input1);
    System.out.println(Arrays.deepToString(input1));

    int[][] input2 = new int[][] {
                                   {1, 1, 0},
                                   {1, 1, 1},
                                   {0, 1, 1},
    };
    new Solution2().setZeroes(input2);
    System.out.println(Arrays.deepToString(input2));
  }

}
