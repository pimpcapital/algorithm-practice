package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaximumProductSubarray implements Medium {

  /**
   * Loop through the array, each time remember the max and min value for the previous product.
   * the most important thing is to update the max and min value:
   *   we have to compare among max * A[i], min * A[i] as well as A[i], since this is product, a negative * negative could be positive.
   */
  public static class Solution {
    public int maxProduct(int[] A) {
      if (A == null || A.length == 0) {
        return 0;
      }
      int max = A[0], min = A[0], result = A[0];
      for (int i = 1; i < A.length; i++) {
        int temp = max;
        max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
        min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
        if (max > result) {
          result = max;
        }
      }
      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().maxProduct(new int[] {2, 3, -2, 4}));
  }

}
