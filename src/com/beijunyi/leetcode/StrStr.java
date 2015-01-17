package com.beijunyi.leetcode;

/**
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class StrStr {

  public static class Solution {
    public int strStr(String haystack, String needle) {
      if(needle.isEmpty()) return 0;
      int i = 0;
      int j = 0;
      while(i < haystack.length() && haystack.length() - i >= needle.length()) {
        int k = i;
        while(j < needle.length()) {
          if(haystack.charAt(k) != needle.charAt(j)) {
            j = 0;
            break;
          }
          j++;
          k++;
        }
        if(j == needle.length()) return i;
        i++;
      }
      return -1;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().strStr("aababbc", "abb"));
  }

}
