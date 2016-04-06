package com.beijunyi.leetcode;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 */
public class _198_HouseRobber {

  public static class Solution {

    public int rob(int[] nums) {
      int[] cache = new int[nums.length];
      Arrays.fill(cache, -1);
      return rob(nums, 0, cache);
    }

    private static int rob(int[] nums, int offset, int[] cache) {
      if(offset >= nums.length) return 0;
      if(cache[offset] != -1) return cache[offset];

      int robCurrent = nums[offset];

      int next = offset + 1;
      int robFromNext = rob(nums, next, cache);

      int after = next + 1;
      int robFromAfter = rob(nums, after, cache);

      int result = Math.max(robCurrent + robFromAfter, robFromNext);
      cache[offset] = result;
      return result;
    }

  }

  public static void main(String args[]) {
    Solution solution = new Solution();
    int[] input = new int[] {104,209,137,52,158,67,213,86,141,110,151,127,238,147,169,138,240,185,246,225,147,203,83,83,131,227,54,78,165,180,214,151,111,161,233,147,124,143};
    System.out.println(solution.rob(input));
  }

}
