package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 *
 * Note:
 *   n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *
 * Example 1:
 *   Input:
 *   3
 *
 *   Output:
 *   3
 *
 * Example 2:
 *   Input:
 *   11
 *
 *   Output:
 *   0
 *
 *   Explanation:
 *   The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class _400_NthDigit implements Easy, NotHardButTricky {

  public interface Solution {
    int findNthDigit(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public int findNthDigit(int n) {       // n = 200
      long count = 9;
      int digits = 1;
      while(n > count * digits) {
        n -= count * digits;
        count *= 10;
        digits++;
      }                                    // n = 11, digits = 3..

      int start = 1;
      for(int zeroes = 0; zeroes < digits - 1; zeroes++) start *= 10;
                                           // start = 100

      int offset = n - 1;                  // offset = 10
      int num = offset / digits + start;   // num = 3 + 100 = 103
      String str = Integer.toString(num);
      int pos = offset % digits;           // pos = 1
      return str.charAt(pos) - '0';
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public int findNthDigit(int n) {       // n = 200
      long count = 9;
      int digits = 1;
      while(n > count * digits) {
        n -= count * digits;
        count *= 10;
        digits++;
      }                                    // n = 11, digits = 3..

      int start = 1;
      for(int zeroes = 0; zeroes < digits - 1; zeroes++) start *= 10;
      // start = 100

      int offset = n - 1;                  // offset = 10
      int num = offset / digits + start;   // num = 3 + 100 = 103
      int pos = offset % digits;
      int tail = digits - pos - 1;
      while(tail-- > 0) num /= 10;
      return num % 10;
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 3;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 10;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 11;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 12;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 13;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 14;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 15;
      result = s.findNthDigit(n);
      System.out.println(result);

      n = 2147483647;
      result = s.findNthDigit(n);
      System.out.println(result);

      System.out.println();
    }
  }

}
