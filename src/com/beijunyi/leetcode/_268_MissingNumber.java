package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * For example,
 * Given nums = [0, 1, 3] return 2.
 *
 * Note:
 *   Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space
 *   complexity?
 */
public class _268_MissingNumber implements Medium {

  public interface Solution {
    int missingNumber(int[] nums);
  }

  public static class Solution1 implements Solution, BitManipulation {

    @Override
    public int missingNumber(int[] nums) {
      int mask = 0;
      for(int i = 1; i <= nums.length; i++) mask ^= i;
      for(int num : nums) mask ^= num;
      return mask;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public int missingNumber(int[] nums) {
      int len = nums.length;
      int sum = (len) * (len + 1) / 2;
      for(int num : nums) sum -= num;
      return sum;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[]{1};
      result = s.missingNumber(nums);
      System.out.println(result);

      nums = new int[]{0, 1, 3};
      result = s.missingNumber(nums);
      System.out.println(result);

      nums = new int[]{2, 0, 3};
      result = s.missingNumber(nums);
      System.out.println(result);

      nums = new int[]{2, 0, 1};
      result = s.missingNumber(nums);
      System.out.println(result);
    }
  }

}
