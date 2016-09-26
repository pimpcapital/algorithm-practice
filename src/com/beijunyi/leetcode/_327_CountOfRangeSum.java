package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 *   A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 * Example:
 *   Given nums = [-2, 5, -1], lower = -2, upper = 2,
 *   Return 3.
 *   The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class _327_CountOfRangeSum implements Hard {

  public interface Solution {
    int countRangeSum(int[] nums, int lower, int upper);
  }

  public static class Solution1 implements Solution {

    @Override
    public int countRangeSum(int[] nums, int lower, int upper) {
      return 0;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int lower;
    int upper;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[]{-2, 5, -1};
      lower = -2;
      upper = 2;
      result = s.countRangeSum(nums, lower, upper);
      System.out.println(result);
    }
  }

}
