package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Given a positive integer n and you can do operations as follow:
 *
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * What is the minimum number of replacements needed for n to become 1?
 *
 * Example 1:
 *   Input:
 *     8
 *
 *   Output:
 *     3
 *
 *   Explanation:
 *     8 -> 4 -> 2 -> 1
 *
 * Example 2:
 *   Input:
 *     7
 *
 *   Output:
 *     4
 *
 *   Explanation:
 *     7 -> 8 -> 4 -> 2 -> 1
 *     or
 *     7 -> 6 -> 3 -> 2 -> 1
 */
public class _397_IntegerReplacement implements Easy, NotHardButTricky {

  public interface Solution {
    int integerReplacement(int n);
  }

  /**
   * When number is even, the last bit is 0. We can simply shift right.
   *
   * When number is odd, the last bit is '1'. We have to either do '+1' or '-1' to unset the last bit.
   *  '-1' operation unsets last 1 bit
   *  '+1' operation unsets last N bits and sets 1 bit at (N+1)th from right
   *
   * There are 2 scenarios: '10000001' and '10001111'.
   * First scenario should be resolved by -1.
   * Second scenario should be resolved by +1.
   *
   * By looking at only the bit, we cannot determine which scenario it is. So we record it as a `debt` and shift right.
   *
   * When the last bit is '0', we review our debts. If 'debt == 1', we simply pay for it. Otherwise, we reduce the debt
   * by '+1'. '+1' operation will create a new debt, which can be payed later.
   */
  public static class Solution1 implements Solution, BitManipulation {
    @Override
    public int integerReplacement(int n) {
      int count = 0;
      int debt = 0;
      while(n > 1) {
        if((n & 1) == 1) debt++;
        else {
          if(debt > 1) {
            count++;
            debt = 1;
          } else {
            count += debt;
            debt = 0;
          }
        }
        n = n >>> 1;
        count++;
      }
      return count + Math.min(debt, 2);
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 1234;
      result = s.integerReplacement(n);
      System.out.println(result);
    }
  }

}
