package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BinarySearch;

/**
 * Write an efficient algorithm that searches for a value in an m * n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * For example, consider the following matrix:
 *  [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 *  ]
 * Given target = 3, return true.
 */
public class _074_SearchA2DMatrix implements Medium {

  public interface Solution {
    boolean searchMatrix(int[][] matrix, int target);
  }

  public static class Solution1 implements Solution, BinarySearch {

    @Override
    public boolean searchMatrix(int[][] matrix, int target) {
      if (matrix == null || matrix.length == 0) {
        return false;
      }
      int start = 0, rows = matrix.length, cols = matrix[0].length;
      int end = rows * cols - 1;
      while (start <= end) {
        int mid = (start + end) / 2;
        if (matrix[mid / cols][mid % cols] == target) {
          return true;
        }
        if (matrix[mid / cols][mid % cols] < target) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      }
      return false;
    }

  }

  public static void main(String[] args) {
    int[][] matrix = new int[][] {
      {1,   3,  5,  7},
      {10, 11, 16, 20},
      {23, 30, 34, 50},
    };
    Solution s = new Solution1();
    boolean r = s.searchMatrix(matrix, 3);
    System.out.println(r);
  }

}
