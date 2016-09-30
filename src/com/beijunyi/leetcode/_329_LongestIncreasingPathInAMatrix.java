package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.Memoization;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move
 * outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *   nums = [
 *     [9,9,4],
 *     [6,6,8],
 *     [2,1,1]
 *   ]
 *   Return 4
 *   The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 *   nums = [
 *     [3,4,5],
 *     [3,2,6],
 *     [2,2,1]
 *   ]
 *   Return 4
 *   The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class _329_LongestIncreasingPathInAMatrix implements Hard {

  public interface Solution {
    int longestIncreasingPath(int[][] matrix);
  }

  public static class Solution1 implements Solution, Memoization, DepthFirstSearch {

    private static final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int[][] matrix;
    private int[][] cache;
    private int rows;
    private int cols;

    @Override
    public int longestIncreasingPath(int[][] matrix) {
      this.matrix = matrix;
      rows = matrix.length;
      cols = rows == 0 ? 0 : matrix[0].length;
      cache = new int[rows][cols];
      int max = 0;
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < cols; c++) {
          max = Math.max(max, longestIncreasingPath(r, c));
        }
      }
      return max;
    }

    private int longestIncreasingPath(int r, int c) {
      if(cache[r][c] != 0) return cache[r][c];
      int value = matrix[r][c];
      int max = 1;
      for(int[] dir : directions) {
        int nr = r + dir[0];
        int nc = c + dir[1];
        if(nr < 0 || nc < 0 || nr == rows || nc == cols) continue;
        int nVal = matrix[nr][nc];
        if(nVal <= value) continue;
        max = Math.max(max, 1 + longestIncreasingPath(nr, nc));
      }
      return cache[r][c] = max;
    }
  }

  public static void main(String args[]) {
    int[][] matrix;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      matrix = new int[][]{
        {9, 9, 4},
        {6, 6, 8},
        {2, 1, 1}
      };
      result = s.longestIncreasingPath(matrix);
      System.out.println(result);
    }
  }

}
