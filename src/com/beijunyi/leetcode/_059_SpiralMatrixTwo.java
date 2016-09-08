package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * For example,
 * Given the following matrix:
 *   [
 *     [ 1, 2, 3 ],
 *     [ 4, 5, 6 ],
 *     [ 7, 8, 9 ]
 *   ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class _059_SpiralMatrixTwo implements Medium {

  public interface Solution {
    int[][] generateMatrix(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public int[][] generateMatrix(int n) {
      int[][] ret = new int[n][n];
      int max = n * n;
      int num = 1;
      int left = 0;
      int right = n;
      int top = 0;
      int bottom = n;
      while(num <= max) {
        for(int x = left; x < right; x++) ret[top][x] = num++;
        top++;
        for(int y = top; y < bottom; y++) ret[y][right - 1] = num++;
        right--;
        for(int x = right - 1; x >= left; x--) ret[bottom - 1][x] = num++;
        bottom--;
        for(int y = bottom - 1; y >= top; y--) ret[y][left] = num++;
        left++;
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    int n;
    int[][] result;
    for(Solution s : Arrays.asList(new Solution1())) {
      n = 3;
      result = s.generateMatrix(n);
      for(int[] row : result) {
        System.out.println(Arrays.toString(row));
      }
    }
  }

}
