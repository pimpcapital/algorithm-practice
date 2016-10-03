package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class _028_ImplementStrStr implements Easy {

  public interface Solution {
    int strStr(String haystack, String needle);
  }

  public static class Solution1 implements Solution {
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

  public static class Solution2 implements Solution {
    @Override
    public int strStr(String haystack, String needle) {
      for(int i = 0; i <= haystack.length() - needle.length(); i++) {
        for(int j = 0; j <= needle.length(); j++) {
          if(j == needle.length()) return i;
          if(haystack.charAt(i + j) != needle.charAt(j)) break;
        }
      }
      return -1;
    }
  }

  public static void main(String args[]) {
    String haystack;
    String needle;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      haystack = "aababbc";
      needle = "abb";
      result = s.strStr(haystack, needle);
      System.out.println(result);

      haystack = "";
      needle = "";
      result = s.strStr(haystack, needle);
      System.out.println(result);

      haystack = "a";
      needle = "a";
      result = s.strStr(haystack, needle);
      System.out.println(result);
    }


  }

}
