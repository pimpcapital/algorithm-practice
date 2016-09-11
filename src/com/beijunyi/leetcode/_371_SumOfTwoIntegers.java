package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 * Example:
 *   Given a = 1 and b = 2, return 3.
 */
public class _371_SumOfTwoIntegers implements Easy, NotHardButTricky {

  public interface Solution {
    int getSum(int a, int b);
  }

  public static class Solution1 implements Solution, BitManipulation {
    @Override
    public int getSum(int a, int b) {
      if(b == 0) return a;
      int xor = a^b; //
      int carry = (a&b) << 1;
      return getSum(xor, carry);
    }
  }

  public static void main(String args[]) {
    int a;
    int b;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      a = 2;
      b = 3;
      result = s.getSum(a, b);
      System.out.println(result);
    }
  }

}
