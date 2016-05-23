package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 *
 * Follow up:
 *  Could you do this in-place?
 */
public class _048_RotateImage implements Medium {

  public interface Solution {
    void rotate(int[][] matrix);
  }

  public static class Solution1 implements Solution {

    @Override
    public void rotate(int[][] matrix) {
      int layers = matrix.length / 2; // if length is odd number, the last layer has one pixel only, no need to rotate
      for(int i = 0; i < layers; i++)
        rotateLayer(matrix, i);
    }

    private static void rotateLayer(int[][] matrix, int layer) {
      int n = matrix.length - (layer * 2) - 1; // cells to rotate
      for(int i = 0; i < n; i++) {
        int[][] pos = findPos(matrix.length, layer, i);
        int[] values = readValues(matrix, pos);
        replaceValues(matrix, pos, values);
      }
    }

    private static int[][] findPos(int n, int layer, int index) {
      return new int[][] {
    //   x                    , y
        {layer + index        , layer                },  // top left
        {n - layer - 1        , layer + index        },  // top right
        {n - index - layer - 1, n - layer - 1        },  // bottom right
        {layer                , n - index - layer - 1}   // bottom left
      };
    }

    private static int[] readValues(int[][] matrix, int[][] corners) {
      int[] ret = new int[4];
      for(int i = 0; i < 4; i++) {
        int[] corner = corners[i];
        int x = corner[0];
        int y = corner[1];
        ret[i] = matrix[y][x];
      }
      return ret;
    }

    private static void replaceValues(int[][] matrix, int[][] pos, int[] values) {
      for(int j = 0; j < 4; j++) {
        int x = pos[j][0];
        int y = pos[j][1];
        matrix[y][x] = values[(j - 1 + 4) % 4];
      }
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public void rotate(int[][] M) {
      for(int i = 0; i < (M.length + 1) / 2; i++) { // column
        for(int j = 0; j < M.length / 2; j++) {     // row
          int tmp = M[i][j];
          M[i][j] = M[M.length - j - 1][i];
          M[M.length - j - 1][i] = M[M.length - i - 1][M.length - j - 1];
          M[M.length - i - 1][M.length - j - 1] = M[j][M.length - i - 1];
          M[j][M.length - i - 1] = tmp;
        }
      }
    }

  }

  public static void main(String args[]) {
    int[][] matrix;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      matrix = new int[][] {
        {1, 2},
        {3, 4}
      };
      s.rotate(matrix);
      for(int[] row : matrix)
        System.out.print(Arrays.toString(row));
      System.out.println();

      matrix = new int[][] {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
      };
      s.rotate(matrix);
      for(int[] row : matrix)
        System.out.print(Arrays.toString(row));
      System.out.println();

      matrix = new int[][] {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
      };
      s.rotate(matrix);
      for(int[] row : matrix)
        System.out.print(Arrays.toString(row));
      System.out.println();
    }

  }

}
