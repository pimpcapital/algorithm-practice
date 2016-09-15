package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 *
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <img src="./_223_RectangleArea.png"/>
 * Assume that the total area is never beyond the maximum possible value of int.
 *
 */
public class _223_RectangleArea implements Easy {

  public interface Solution {
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H);
  }

  public static class Solution1 implements Solution {
    @Override
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
      int deltaX1 = C - A;
      int deltaY1 = D - B;
      int deltaX2 = G - E;
      int deltaY2 = H - F;

      int rec1 = deltaX1 * deltaY1;
      int rec2 = deltaX2 * deltaY2;
      int overlap;
      if(A >= G || B >= H || C <= E || D <= F){
        overlap = 0;
      } else {
        int deltaX3 = Math.min(C, G) - Math.max(A, E);
        int deltaX4 = Math.min(D, H) - Math.max(B, F);
        overlap = deltaX3 * deltaX4;
      }

      return rec1 + rec2 - overlap;
    }
  }

  public static void main(String args[]) {
    int A, B, C, D, E, F, G, H;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      A = -3;
      B = 0;
      C = 3;
      D = 4;
      E = 0;
      F = -1;
      G = 9;
      H = 2;
      result = s.computeArea(A, B, C, D, E, F, G, H);
      System.out.println(result);
    }
  }


}
