package com.beijunyi.leetcode;

import java.util.*;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * For example,
 *
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 */
public class FractionToRecurringDecimal {

  public static class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
      if (denominator == 0)
        return "NaN";
      if (numerator == 0)
        return "0";
      StringBuilder result = new StringBuilder();
      Long n = (long)numerator;
      Long d = (long)denominator;
      // negative or positive
      if (n*d < 0)
        result.append("-");
      n = Math.abs(n);
      d = Math.abs(d);
      result.append(Long.toString(n / d));
      // result is integer or float
      if (n % d == 0)
        return result.toString();
      else
        result.append(".");
      // deal with the float part
      // key is the remainder, value is the start positon of possible repeat numbers
      HashMap<Long, Integer> map = new HashMap<>();
      Long r = n % d;
      while (r > 0) {
        if(map.containsKey(r)) {
          result.insert(map.get(r), "(");
          result.append(")");
          break;
        }
        map.put(r, result.length());
        r *= 10;
        result.append(Long.toString(r / d));
        r %= d;
      }
      return result.toString();
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().fractionToDecimal(1, 6));
  }


}
