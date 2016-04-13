package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number.
 */
public class _264_UglyNumberTwo implements Medium {

  public interface Solution {
    int nthUglyNumber(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public int nthUglyNumber(int n) {
      return 0;
    }

  }

  public static void main(String args[]) {
    int n;
    int r;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 10;
      r = s.nthUglyNumber(n);
      System.out.println(r);
    }
  }

}
