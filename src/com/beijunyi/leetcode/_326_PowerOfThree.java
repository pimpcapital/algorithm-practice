package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

public class _326_PowerOfThree implements Easy {

  public interface Solution {
    boolean isPowerOfThree(int n);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isPowerOfThree(int n) {
      // The expression "(int) Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3.0))" returns max integer that is "power of 3"
      return n > 0 && (int) Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3.0))) % n == 0;
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
