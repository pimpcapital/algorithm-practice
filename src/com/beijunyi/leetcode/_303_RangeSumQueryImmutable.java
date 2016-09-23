package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.ReallyEasy;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 *   Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 *   You may assume that the array does not change.
 *   There are many calls to sumRange function.
 */
public class _303_RangeSumQueryImmutable implements Easy, ReallyEasy {

  public interface Solution {

    void init(int[] nums);

    int sumRange(int i, int j);
  }

  public static class Solution1 implements Solution {
    @Override
    public void init(int[] nums) {

    }

    @Override
    public int sumRange(int i, int j) {
      return 0;
    }
  }

  public static void main(String args[]) {

    int[] nums;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {-2, 0, 3, -5, 2, -1};
      s.init(nums);
      System.out.println(s.sumRange(0, 2));
      System.out.println(s.sumRange(2, 5));
      System.out.println(s.sumRange(0, 5));
    }

  }

}
