package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 *
 * A = [3,2,1,0,4], return false.
 */
public class JumpGame implements Medium {

  public static class Solution {
    public boolean canJump(int[] A) {
      int i = 0;
      for(int reach = 0; i < A.length && i <= reach; ++i)
        reach = Math.max(i + A[i], reach);
      return i == A.length;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().canJump(new int[]{2, 3, 1, 1, 4}));
  }
}
