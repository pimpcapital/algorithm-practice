package com.beijunyi.leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 *
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {

  public static class Solution {
    public int maxSubArray(int[] A) {
      int maxSoFar=A[0], maxEndingHere=A[0];
      for (int i=1; i<A.length; ++i){
        maxEndingHere= Math.max(maxEndingHere+A[i], A[i]);
        maxSoFar=Math.max(maxSoFar, maxEndingHere);
      }
      return maxSoFar;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().maxSubArray(new int[]{-2, 1,-3, 4,-1,2,1,-5,4}));
  }

}
