package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Follow up:
 * Could you do it without using any loop / recursion?
 */
public class _326_PowerOfThree implements Easy {

  public interface Solution {
    boolean isPowerOfThree(int n);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isPowerOfThree(int n) {
      // 1162261467
      int largestPowerOfThreeInt = (int) Math.pow(3, Math.floor(Math.log(Integer.MAX_VALUE) / Math.log(3.0)));
      return n > 0 && largestPowerOfThreeInt % n == 0;
    }

  }

  public static void main(String args[]) {
    int n;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 45;
      result = s.isPowerOfThree(n);
      System.out.println(result);
    }
  }

}
