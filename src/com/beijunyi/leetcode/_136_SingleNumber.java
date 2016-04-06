package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class _136_SingleNumber implements Medium {

  public static class Solution {

    public int singleNumber(int[] A) {
      int result = 0;
      for(int a : A)
        result ^= a;
      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().singleNumber(new int[] {1, 2, 1}));
  }


}
