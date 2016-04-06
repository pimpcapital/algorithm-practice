package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class _066_PlusOne implements Easy {
  public static class Solution {
    public int[] plusOne(int[] digits) {
      if(digits.length == 1 && digits[0] == 0)
        digits = new int[0];
      boolean moreDigit = true;
      for(int d : digits)
        if(d != 9) {
          moreDigit = false;
          break;
        }
      int[] result = new int[moreDigit ? digits.length + 1 : digits.length];
      if(moreDigit)
        result[0] = 1;
      else {
        boolean carry = true;
        for(int i = digits.length - 1; i >= 0; i--) {
          int d = digits[i];
          if(carry)
            d++;
          if(d == 10)
            d = 0;
          else
            carry = false;
          result[i] = d;
        }
      }
      return result;
    }
  }

  public static void main(String args[]) {
    int[] input = new int[] {9, 9, 9};
    System.out.println(Arrays.toString(new Solution().plusOne(input)));
  }
}
