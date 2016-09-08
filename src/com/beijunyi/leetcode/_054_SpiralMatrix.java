package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class _054_SpiralMatrix implements Medium {

  public interface Solution {
    List<Integer> spiralOrder(int[][] matrix);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<Integer> spiralOrder(int[][] matrix) {
      int rows = matrix.length;
      int cols = rows != 0 ? matrix[0].length : 0;
      List<Integer> ret = new ArrayList<>();
      if(rows == 0 || cols == 0) return ret;

      int[] current = new int[] {0, 0};
      int[] dir = new int[] {1, 0};
      int[] boundaries = new int[] {0, cols, 0, rows};

      while(boundaries[0] < boundaries[1] && boundaries[2] < boundaries[3]) {
        ret.add(matrix[current[1]][current[0]]);
        adjustXDir(current, dir, boundaries);
        adjustYDir(current, dir, boundaries);
        move(current, dir);
      }
      return ret;
    }

    private static void adjustXDir(int[] current, int[] dir, int[] boundaries) {
      if(dir[0] == 0) return;
      int newX = current[0] + dir[0];
      if(newX >= boundaries[1] || newX < boundaries[0]) {
        if(dir[0] == -1) boundaries[3]--;
        else boundaries[2]++;
        dir[1] = dir[0];
        dir[0] = 0;
      }
    }

    private static void adjustYDir(int[] current, int[] dir, int[] boundaries) {
      if(dir[1] == 0) return;
      int newY = current[1] + dir[1];
      if(newY >= boundaries[3] || newY < boundaries[2]) {
        if(dir[1] == -1) boundaries[0]++;
        else boundaries[1]--;
        dir[0] = -dir[1];
        dir[1] = 0;
      }
    }

    private static void move(int[] current, int[] dir) {
      current[0] += dir[0];
      current[1] += dir[1];
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public List<Integer> spiralOrder(int[][] matrix) {

      List<Integer> res = new ArrayList<>();

      if (matrix.length == 0) {
        return res;
      }

      int rowBegin = 0;
      int rowEnd = matrix.length-1;
      int colBegin = 0;
      int colEnd = matrix[0].length - 1;

      while (rowBegin <= rowEnd && colBegin <= colEnd) {
        // Traverse Right
        for (int j = colBegin; j <= colEnd; j ++) {
          res.add(matrix[rowBegin][j]);
        }
        rowBegin++;

        // Traverse Down
        for (int j = rowBegin; j <= rowEnd; j ++) {
          res.add(matrix[j][colEnd]);
        }
        colEnd--;

        if (rowBegin <= rowEnd) {
          // Traverse Left
          for (int j = colEnd; j >= colBegin; j --) {
            res.add(matrix[rowEnd][j]);
          }
        }
        rowEnd--;

        if (colBegin <= colEnd) {
          // Traver Up
          for (int j = rowEnd; j >= rowBegin; j --) {
            res.add(matrix[j][colBegin]);
          }
        }
        colBegin ++;
      }

      return res;
    }
  }

  public static void main(String args[]) {
    int[][] matrix;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      matrix = new int[][] {
        {2, 5},
        {8, 4},
        {0, -1}
      };
      result = s.spiralOrder(matrix);
      System.out.println(result);
    }
  }

}
