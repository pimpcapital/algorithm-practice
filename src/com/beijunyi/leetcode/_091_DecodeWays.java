package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 *
 * The number of ways decoding "12" is 2.
 */
public class _091_DecodeWays implements Medium {

  public interface Solution {
    int numDecodings(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public int numDecodings(String s) {
      int n = s.length();
      if(n == 0) return 0;

      int[] memo = new int[n + 1];
      memo[n] = 1;
      memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

      for(int i = n - 2; i >= 0; i--)
        if(s.charAt(i) != '0')
          memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];

      return memo[0];
    }
  }

  public static class Solution2 implements Solution {
    public int numDecodings(String s) {
      if(s.isEmpty()) return 0;
      int[] ways = new int[s.length() + 1];
      ways[s.length()] = 1;
      ways[s.length() - 1] = isValidSingle(s, s.length() - 1) ? 1 : 0;

      for(int i = s.length() - 2; i >= 0; i--) {
        if(isValidSingle(s, i)) ways[i] += ways[i + 1];
        if(isValidDouble(s, i)) ways[i] += ways[i + 2];
      }

      return ways[0];
    }

    private static boolean isValidSingle(String s, int pos) {
      return s.charAt(pos) != '0';
    }

    private static boolean isValidDouble(String s, int pos) {
      return s.charAt(pos) == '1' || s.charAt(pos) == '2' && s.charAt(pos + 1) <= '6';
    }
  }

  public static void main(String args[]) {
    String s;
    int result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "12";
      result = solution.numDecodings(s);
      System.out.println(result);

      s = "17";
      result = solution.numDecodings(s);
      System.out.println(result);
    }
  }

}
