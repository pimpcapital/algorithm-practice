package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.solution.PrefixSum;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum
 * is no larger than k.
 *
 * Example:
 *   Given matrix = [
 *     [1,  0, 1],
 *     [0, -2, 3]
 *     ]
 *   k = 2
 *   The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k
 *   (k = 2).
 *
 * Note:
 *   The rectangle inside the matrix must have an area > 0.
 *   What if the number of rows is much larger than the number of columns?
 */
public class _363_MaxSumOfRectangleNoLargerThanK implements Hard, Important {

  public interface Solution {
    int maxSumSubmatrix(int[][] matrix, int k);
  }

  public static class Solution1 implements Solution, PrefixSum {
    @Override
    public int maxSumSubmatrix(int[][] matrix, int k) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        return 0;
      int rows = matrix.length, cols = matrix[0].length;
      int[][] areas = new int[rows][cols];
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          int area = matrix[r][c];
          if (r-1 >= 0)
            area += areas[r-1][c];
          if (c-1 >= 0)
            area += areas[r][c-1];
          if (r-1 >= 0 && c-1 >= 0)
            area -= areas[r-1][c-1];
          areas[r][c] = area;
        }
      }
      int max = Integer.MIN_VALUE;
      for(int r1 = 0; r1 < rows; r1++) { // start row
        for(int r2 = r1; r2 < rows; r2++) {
          TreeSet<Integer> tree = new TreeSet<>();
          tree.add(0);    // padding
          for(int c = 0; c < cols; c++) {
            int area = areas[r2][c];
            if(r1 > 0) area -= areas[r1-1][c];
            Integer ceiling = tree.ceiling(area - k);
            if(ceiling != null) max = Math.max(max, area - ceiling);
            tree.add(area);
          }
        }
      }
      return max;
    }
  }

  /**
   * Same idea as solution1. More modular
   */
  public static class Solution2 implements Solution {
    private int[][] matrix;
    private int rows;
    private int cols;

    @Override
    public int maxSumSubmatrix(int[][] matrix, int k) {
      this.matrix = matrix;
      rows = matrix.length;
      cols = rows == 0 ? 0 : matrix[0].length;

      convertToPrefixSum();

      int max = Integer.MIN_VALUE;
      for(int r1 = 0; r1 < rows; r1++) {
        for(int r2 = r1; r2 < rows; r2++) {
          max = Math.max(max, maxSumNoLargerThanK(r1, r2, k));
        }
      }
      return max;
    }

    private void convertToPrefixSum() {
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < cols; c++) {
          if(r != 0) matrix[r][c] += matrix[r - 1][c];
          if(c != 0) matrix[r][c] += matrix[r][c - 1];
          if(r != 0 && c != 0) matrix[r][c] -= matrix[r - 1][c - 1];
        }
      }
    }

    private int maxSumNoLargerThanK(int startRow, int endRow, int k) {
      int max = Integer.MIN_VALUE;
      TreeSet<Integer> sums = new TreeSet<>();
      sums.add(0);
      for(int c = 0; c < cols; c++) {
        int sum = matrix[endRow][c];
        if(startRow != 0) sum -= matrix[startRow - 1][c];
        Integer smallerSum = sums.ceiling(sum - k);
        if(smallerSum != null) max = Math.max(max, sum - smallerSum);
        sums.add(sum);
      }
      return max;
    }
  }

  public static void main(String args[]) {
    int[][] matrix;
    int k;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      matrix = new int[][]{
        {1, 0, 1},
        {0, -2, 3}
      };
      k = 2;
      result = s.maxSumSubmatrix(matrix, k);
      System.out.println(result);

      matrix = new int[][]{
        {1, 0, 1},
        {0, -2, 3}
      };
      k = 4;
      result = s.maxSumSubmatrix(matrix, k);
      System.out.println(result);
    }
  }

}
