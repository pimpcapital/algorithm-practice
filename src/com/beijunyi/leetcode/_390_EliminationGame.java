package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other
 * number afterward until you reach the end of the list.
 *
 * Repeat the previous step again, but this time from right to left, remove the right most number and every other number
 * from the remaining numbers.
 *
 * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 *
 * Find the last number that remains starting with a list of length n.
 *
 * Example:
 *   Input:
 *     n = 9,
 *     1 2 3 4 5 6 7 8 9
 *     2 4 6 8
 *     2 6
 *     6
 *
 *   Output:
 *     6
 */
public class _390_EliminationGame implements Medium {

  public interface Solution {
    int lastRemaining(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public int lastRemaining(int n) {
      return leftToRight(n);
    }

    private static int leftToRight(int n) {
      if(n <= 2) return n;
      return 2 * rightToLeft(n / 2);
    }

    private static int rightToLeft(int n) {
      if(n <= 2) return 1;
      if(n % 2 == 1) return 2 * leftToRight(n / 2);
      return 2 * leftToRight(n / 2) - 1;
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 9;
      result = s.lastRemaining(n);
      System.out.println(result);
    }
  }

}
