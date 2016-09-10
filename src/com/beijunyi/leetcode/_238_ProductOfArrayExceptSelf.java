package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of
 * all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6].
 *
 * Follow up:
 *   Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the
 *   purpose of space complexity analysis.)
 */
public class _238_ProductOfArrayExceptSelf implements Medium {

  public interface Solution {
    int[] productExceptSelf(int[] nums);
  }

  public static class Solution1 implements Solution {
    @Override
    public int[] productExceptSelf(int[] nums) {
      long product = 1;
      int zeroes = 0;
      int zeroIndex = -1;
      for(int i = 0; i < nums.length; i++) {
        int num = nums[i];
        if(num == 0) {
          zeroIndex = i;
          zeroes++;
          if(zeroes > 1) break;
        } else {
          product *= num;
        }
      }
      if(zeroes != 0) {
        Arrays.fill(nums, 0);
        if(zeroes == 1) nums[zeroIndex] = (int) product;
      } else {
        for(int i = 0; i < nums.length; i++) nums[i] = (int) (product / nums[i]);
      }
      return nums;
    }
  }

  /**
   * First pass res[i] = product of nums between 0 and i-1
   * Second pass res[i] *= product of nums between i+1 and n-1
   */
  public static class Solution2 implements Solution {
    @Override
    public int[] productExceptSelf(int[] nums) {
      int n = nums.length;
      int[] res = new int[n];
      res[0] = 1;
      for(int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
      }
      int right = 1;
      for(int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
      }
      return res;
    }
  }

  /**
   * Same idea as solution2 but even neater
   */
  public static class Solution3 implements Solution {
    public int[] productExceptSelf(int[] nums) {
      int n = nums.length;
      int[] a = new int[n];
      for (int i = 0, left = 1; i < n; i++) {
        a[i] = left;
        left *= nums[i];
      }
      for (int i = n - 1, right = 1; i >= 0; i--) {
        a[i] *= right;
        right *= nums[i];
      }
      return a;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int[] result;
    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {1, 2, 3, 4};
      result = s.productExceptSelf(nums);
      System.out.println(Arrays.toString(result));
    }
  }

}
