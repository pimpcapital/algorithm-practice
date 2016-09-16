package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 *
 * Note: The sequence of integers will be represented as a string.
 */
public class _038_CountAndSay implements Easy {

  public interface Solution {
    String countAndSay(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public String countAndSay(int n) {
      String current = "1";
      int s = 1;
      while(s++ < n) {
        StringBuilder sb = new StringBuilder();
        int lastChar = 0;
        for(int i = 1; i < current.length(); i++) {
          if(current.charAt(i) != current.charAt(lastChar)) {
            sb.append(i - lastChar).append(current.charAt(lastChar));
            lastChar = i;
          }
        }
        sb.append(current.length() - lastChar).append(current.charAt(lastChar));
        current = sb.toString();
      }
      return current;
    }
  }

  public static void main(String args[]) {
    int n;
    String result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 2;
      result = s.countAndSay(n);
      System.out.println(result);
    }

  }

}
