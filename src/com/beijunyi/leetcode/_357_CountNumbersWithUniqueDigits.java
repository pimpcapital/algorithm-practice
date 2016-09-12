package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 *
 * Example:
 *   Given n = 2, return 91.
 *   (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44, 55,66,77,88,99])
 *
 * Hint:
 *   1) A direct way is to use the backtracking approach.
 *   2) Backtracking should contains three states which are (the current number, number of steps to get that number and
 *      a bitmask which represent which number is marked as visited so far in the current number). Start with state
 *      (0,0,0) and count all valid number till we reach number of steps equals to 10n.
 *   3) This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
 *   4) Let f(k) = count of numbers with unique digits with length equals k.
 *   5) f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 */
public class _357_CountNumbersWithUniqueDigits implements Medium {

  public interface Solution {
    int countNumbersWithUniqueDigits(int n);
  }

  public static class Solution1 implements Solution, Backtracking {

    @Override
    public int countNumbersWithUniqueDigits(int n) {
      if(n > 9) n = 9;
      return countNumbersWithUniqueDigits(n, 0);
    }

    private static int countNumbersWithUniqueDigits(int n, int used) {
      if(n == 0) return 1;
      int count = 0;
      for(int i = 0; i <= 9; i++) {
        int mask = 1 << i;
        if((used & mask) != 0) continue;
        if(used != 0 || i != 0) used |= mask; // if it is leading zeroes, don't mark as used
        count += countNumbersWithUniqueDigits(n - 1, used);
        used &= ~mask;
      }
      return count;
    }
  }

  public static class Solution2 implements Solution, DynamicPrograming {

    @Override
    public int countNumbersWithUniqueDigits(int n) {
      if(n > 9) n = 9;
      int[] cache = new int[n + 1];
      cache[0] = 1;
      for(int i = 1; i <= n; i++) {
        int count = 9; // first digit always have 9 options (it cannot be zero)
        for(int j = 1; j < i; j++) {
          count *= 10 - j; // the second digit has (10-1) options, the third digit has (10-2) options etc.
        }
        cache[i] = count + cache[i - 1]; // plus the counts from less digits
      }
      return cache[n];
    }

  }

  public static class Solution3 implements Solution {
    @Override
    public int countNumbersWithUniqueDigits(int n) {
      if(n > 9) n = 9;

      int ret = 1;
      for(int d = 1; d <= n; d++) { // for 2 digit numbers
        int count = 9; // first digit = 9 options
        int options = 9;
        for(int i = 1; i < d; i++) { // 2nd digit has 9 options, 3rd digit has 10 options
          count *= options--;
        }
        ret += count;
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      n = 0;
      result = s.countNumbersWithUniqueDigits(n);
      System.out.println(result);

      n = 1;
      result = s.countNumbersWithUniqueDigits(n);
      System.out.println(result);

      n = 2;
      result = s.countNumbersWithUniqueDigits(n);
      System.out.println(result);

      n = 3;
      result = s.countNumbersWithUniqueDigits(n);
      System.out.println(result);

      n = 4;
      result = s.countNumbersWithUniqueDigits(n);
      System.out.println(result);

      n = 5;
      result = s.countNumbersWithUniqueDigits(n);
      System.out.println(result);

      System.out.println();
    }
  }

}
