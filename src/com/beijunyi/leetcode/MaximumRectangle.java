package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */
public class MaximumRectangle implements Hard {

  public static class Solution2 {
    public int maximalRectangle(char[][] matrix) {
      if(matrix.length == 0)
        return 0;
      int c = matrix[0].length;
      if(c == 0)
        return 0;

      int[] heights = new int[c];
      int max = 0;
      for(char[] row : matrix) {
        for(int j = 0; j <= c; j++) {
          boolean solid = j != c && row[j] == '1';
          if(solid)
            heights[j]++;
          else if(j < c)
            heights[j] = 0;
          if(j != 0 && (j == c || heights[j] < heights[j - 1])) {
            int minHeight = heights[j - 1];
            for(int k = j - 1; k >= 0; k--) {
              minHeight = Math.min(minHeight, heights[k]);
              max = Math.max(max, (j - k) * minHeight);
            }
          }
        }
      }
      return max;
    }
  }

  /**
   * The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row r and column c be computed by [right(r,c) - left(r,c)]*height(r,c).
   *
   * All the 3 variables left, right, and height can be determined by the information from previous row, and also information from the current row. So it can be regarded as a DP solution. The transition equations are:
   *
   * left(r,c)    =  max(left(r-1,c), curLeft), curLeft can be determined from the current row
   * right(r,c)   =  min(right(r-1,c), curRight), curRight can be determined from the current row
   * height(r,c)  =  height(r-1,c) + 1, if matrix[r][c]=='1';
   * height(r,c)  =  0, if matrix[r][c]=='0'
   * The loops can be combined for speed but I separate them for more clarity of the algorithm.
   */
  public static class Solution1 {
    public int maximalRectangle(char[][] matrix) {
      if(matrix.length == 0)
        return 0;
      int col = matrix[0].length;
      int[] left = new int[col];
      int[] right = new int[col];
      int[] height = new int[col];
      int max = 0;
      for(char[] row : matrix) {
        int curLeft = 0, curRight = col;
        for(int c = 0; c < col; c++) { // compute height (can do this from either side)
          if(row[c] == '1') height[c]++;
          else height[c] = 0;
        }
        for(int c = 0; c < col; c++) { // compute left (from left to right)
          if(row[c] == '1')
            left[c] = Math.max(left[c], curLeft);
          else {
            left[c] = 0;
            curLeft = c + 1;
          }
        }
        // compute right (from right to left)
        for(int c = col - 1; c >= 0; c--) {
          if(row[c] == '1')
            right[c] = Math.min(right[c], curRight);
          else {
            right[c] = col;
            curRight = c;
          }
        }
        // compute the area of rectangle (can do this from either side)
        for(int j = 0; j < col; j++)
          max = Math.max(max, (right[j] - left[j]) * height[j]);
      }
      return max;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().maximalRectangle(new char[][] {
                                                                     {'0', '1', '1', '0', '1'},
                                                                     {'1', '1', '0', '1', '0'},
                                                                     {'0', '1', '1', '1', '0'},
                                                                     {'1', '1', '1', '1', '0'},
                                                                     {'1', '1', '1', '1', '1'},
                                                                     {'0', '0', '0', '0', '0'},
    }));
    System.out.println(new Solution2().maximalRectangle(new char[][] {
                                                                      {'0', '1', '1', '0', '1'},
                                                                      {'1', '1', '0', '1', '0'},
                                                                      {'0', '1', '1', '1', '0'},
                                                                      {'1', '1', '1', '1', '0'},
                                                                      {'1', '1', '1', '1', '1'},
                                                                      {'0', '0', '0', '0', '0'},
    }));
  }


}
