package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */
public class _005_LongestPalindromicSubstring implements Medium {

  public static class Solution2 {
    public String longestPalindrome(String s) {
      int longestStart = 0;
      int longestLength = 0;
      for(int i = 0; i < s.length(); i++) {
        int ps1 = longestPalindromeLengthStart(s, i, i);
        int length1 = (i - ps1) * 2 + 1;
        if(length1 > longestLength) {
          longestStart = ps1;
          longestLength = length1;
        }

        int ps2 = longestPalindromeLengthStart(s, i, i + 1);
        int length2 = (i - ps2) * 2 + 2;
        if(length2 > longestLength) {
          longestStart = ps2;
          longestLength = length2;
        }
      }
      return s.substring(longestStart, longestStart + longestLength);
    }

    private int longestPalindromeLengthStart(String s, int left, int right) {
      while(left >= 0 && right < s.length()) {
        if(s.charAt(left--) != s.charAt(right++))
          return left + 2;
      }
      return left + 1;
    }
  }

  public static class Solution1 {
    public boolean isPalindrome(String s, int startIndex, int endIndex) {
      for(int i = startIndex, j = endIndex; i <= j; i++, j--)
        if (s.charAt(i) != s.charAt(j))
          return false;
      return true;
    }

    public String longestPalindrome(String s) {
      int longestLen = 0;
      int longestIndex = 0;

      for(int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
        if(isPalindrome(s, currentIndex - longestLen, currentIndex)) {
          longestLen += 1;
          longestIndex = currentIndex;
        } else if(currentIndex - longestLen - 1 >= 0 && isPalindrome(s, currentIndex - longestLen - 1, currentIndex)) {
          longestLen += 2;
          longestIndex = currentIndex;
        }
      }
      longestIndex++;
      return s.substring(longestIndex - longestLen, longestIndex);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution2().longestPalindrome("aaabaaa"));
    System.out.println(new Solution1().longestPalindrome("aaabaaa"));
  }
}
