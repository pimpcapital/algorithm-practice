package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class _066_PlusOne implements Easy {

  public interface Solution {
    int[] plusOne(int[] digits);
  }

  public static class Solution1 implements Solution {
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

  public static class Solution2 implements Solution {

    @Override
    public int[] plusOne(int[] digits) {
      int i = digits.length - 1;
      for(; i >= 0; i--) {
        if(digits[i] == 9) digits[i] = 0;
        else {
          digits[i]++;
          break;
        }
      }
      if(i >= 0) return digits;
      int[] ret = new int[digits.length + 1];
      ret[0] = 1;
      return ret;
    }
  }

  public static void main(String args[]) {
    int[] input;
    int[] result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      input = new int[] {9, 9, 9};
      result = s.plusOne(input);
      System.out.println(Arrays.toString(result));

      input = new int[] {1, 1, 9};
      result = s.plusOne(input);
      System.out.println(Arrays.toString(result));
    }
  }
}
