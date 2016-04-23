package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *  * Integers in each row are sorted in ascending from left to right.
 *  * Integers in each column are sorted in ascending from top to bottom.
 *
 * For example, consider the following matrix:
 *  [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 *  ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class _240_SearchA2DMatrixTwo implements Medium {

  public interface Solution {
    boolean searchMatrix(int[][] matrix, int target);
  }


  /**
   * Time: O(n^0.79), Space: O(n^0.79) where
   *   n is the total number of cells in the matrix
   *
   * This algorithm divides a big problem into 3 smaller problems whose sizes are 1/4 of the big problem.
   * T(n) = 3T(0.25n) + O(1) -> T(n) = O(n^(log(4)/log(3))) ~= O(n^0.79)
   */
  public static class Solution1 implements Solution, Recursive, BinarySearch {

    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
      int rows = matrix.length;
      int columns = rows != 0 ? matrix[0].length : 0;
      return searchMatrix(matrix, target, 0, columns, 0, rows);
    }

    private static boolean searchMatrix(int[][] matrix, int target, int left, int right, int top, int bottom) {
      if(left == right || top == bottom) return false;
      int x = (left + right) / 2;
      int y = (top + bottom) / 2;
      if(matrix[y][x] == target) return true;
      if(matrix[y][x] < target)
        return searchMatrix(matrix, target, x + 1, right, y + 1, bottom)
                 || searchMatrix(matrix, target, left, right, y + 1, bottom)
                 || searchMatrix(matrix, target, x + 1, right, top, bottom);
      else
        return searchMatrix(matrix, target, left, x, top, y)
                 || searchMatrix(matrix, target, left, right, top, y)
                 || searchMatrix(matrix, target, left, x, top, bottom);
    }

  }

  /**
   * Time: O(r+c), Space: O(1) where
   *   r is the number of rows in the matrix
   *   c is the number of columns in the matrix
   */
  private static class Solution2 implements Solution, Iterative, BinarySearch {
    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        return false;

      int r = 0;
      int c = matrix[0].length - 1;

      while(r < matrix.length && c >= 0) {
        int num = matrix[r][c];
        if(num == target)
          return true;
        else if (num > target)
          c--;
        else
          r++;
      }

      return false;
    }
  }

  public static void main(String[] args) {
    int[][] matrix;
    boolean r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      matrix = new int[][] {
        {1,   4,  7, 11, 15},
        {2,   5,  8, 12, 19},
        {3,   6,  9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30},
      };
      r = s.searchMatrix(matrix, 5);
      System.out.println(r);
    }
  }

}
