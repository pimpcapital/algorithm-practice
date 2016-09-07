package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

public class _231_PowerOfTwo implements Easy {

  public interface Solution {
    boolean isPowerOfTwo(int n);
  }

  public static class Solution1 implements Solution {

    private static final int MAX_POW_OF_TWO = findMaxPowOfTwo();

    @Override
    public boolean isPowerOfTwo(int n) {
      if(n <= 0) return false;
      return MAX_POW_OF_TWO % n == 0;
    }

    private static int findMaxPowOfTwo() {
      double maxExponent = Math.floor(Math.log(Integer.MAX_VALUE) / Math.log(2));
      return (int) Math.pow(2, maxExponent);
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public boolean isPowerOfTwo(int n) {
      if(n <= 0) return false;
      int mask = n - 1; // clear out the last '1'
      return (mask & n) == 0; // if there is only one '1', it is pow of 2
    }
  }

  public static void main(String args[]) {
    int n;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 2048;
      result = s.isPowerOfTwo(n);
      System.out.println(result);
    }
  }

}
