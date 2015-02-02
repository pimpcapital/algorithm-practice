package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater implements Hard {

  public static class Solution {
    public int trap(int[] A) {
      int i = 0, j = A.length - 1, result = 0, plank = 0;
      while(i <= j){
        plank = plank < Math.min(A[i], A[j]) ? Math.min(A[i], A[j]) : plank;
        result = A[i] >= A[j] ? result + (plank - A[j--]) : result + (plank - A[i++]);
      }
      return result;
    }
  }



  public static void main(String args[]) {
    System.out.println(new Solution().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
  }

}
