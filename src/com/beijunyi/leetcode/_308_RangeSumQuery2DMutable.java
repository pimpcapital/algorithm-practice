package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.BinaryIndexedTree;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1,
 * col1) and lower right corner (row2, col2).
 *
 * Example:
 *   Given matrix = [
 *     [3, 0, 1, 4, 2],
 *     [5, 6, 3, 2, 1],
 *     [1, 2, 0, 1, 5],
 *     [4, 1, 0, 1, 7],
 *     [1, 0, 3, 0, 5]
 *   ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 *   The matrix is only modifiable by the update function.
 *   You may assume the number of calls to update and sumRegion function is distributed evenly.
 *   You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 */
public class _308_RangeSumQuery2DMutable implements Hard, PremiumQuestion {

  public interface Solution {

    void init(int[][] matrix);

    void update(int row, int col, int val);

    int sumRegion(int row1, int col1, int row2, int col2);

  }

  public static class Solution1 implements Solution, BinaryIndexedTree {

    private int[][] nums;
    private int rows;
    private int cols;
    private int[][] bit;

    @Override
    public void init(int[][] matrix) {
      rows = matrix.length;
      cols = rows == 0 ? 0 : matrix[0].length;
      nums = new int[rows][cols];
      bit = new int[rows + 1][cols + 1];
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < cols; c++) {
          update(r, c, matrix[r][c]);
        }
      }
    }

    @Override
    public void update(int row, int col, int val) {
      int delta = val - nums[row][col];
      for(int r = row + 1; r <= rows; r += (r & -r)) {
        for(int c = col + 1; c <= cols; c += (c & -c)) {
          bit[r][c] += delta;
        }
      }
      nums[row][col] += delta;
    }

    @Override
    public int sumRegion(int row1, int col1, int row2, int col2) {
      return sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1);
    }

    private int sum(int row, int col) {
      int sum = 0;
      for(int r = row + 1; r > 0; r -= (r & -r)) {
        for(int c = col + 1; c > 0; c -= (c & -c)) {
          sum += bit[r][c];
        }
      }
      return sum;
    }

  }

  public static void main(String args[]) {
    int[][] matrix;

    for(Solution s : Arrays.asList(new Solution1())) {
      matrix = new int[][] {
        {3, 0, 1, 4, 2},
        {5, 6, 3, 2, 1},
        {1, 2, 0, 1, 5},
        {4, 1, 0, 1, 7},
        {1, 0, 3, 0, 5}
      };
      s.init(matrix);
      System.out.println(s.sumRegion(2, 1, 4, 3));
      s.update(3, 2, 2);
      System.out.println(s.sumRegion(2, 1, 4, 3));

    }

  }



}
