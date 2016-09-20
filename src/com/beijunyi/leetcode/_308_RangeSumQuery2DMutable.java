package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Hard;

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
public class _308_RangeSumQuery2DMutable implements Hard {

  public interface Solution {

    void init(int[][] matrix);

    void update(int row, int col, int val);

    int sumRegion(int row1, int col1, int row2, int col2);

  }

  public static class Solution1 implements Solution {

    @Override
    public void init(int[][] matrix) {

    }

    @Override
    public void update(int row, int col, int val) {

    }

    @Override
    public int sumRegion(int row1, int col1, int row2, int col2) {
      return 0;
    }

  }



}
