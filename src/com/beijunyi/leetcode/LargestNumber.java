package com.beijunyi.leetcode;

import java.util.*;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

  public static class Solution {
    public String largestNumber(int[] num) {
      StringBuilder sbuf = new StringBuilder();
      ArrayList<String> numstrings = new ArrayList<>(num.length);

      for (int i : num) numstrings.add(String.valueOf(i));
      Collections.sort(numstrings, new Comparator<String>() {
        public int compare(String a, String b) {
          if (a.length() == b.length()) {
            return b.compareTo(a);
          } else {
            String ab = a + b;
            String ba = b + a;
            return ba.compareTo(ab);
          }
        }
      });

      for (String s : numstrings) sbuf.append(s);

      String res = sbuf.toString();
      if (res.length() > 0 && res.charAt(0) == '0') return "0";

      return res;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().largestNumber(new int[] {3, 30, 34, 5, 9}));
  }

}
