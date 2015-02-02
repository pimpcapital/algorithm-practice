package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Easy;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 * For example,
 * Given s = "Hello World",
 * return 5.
 */
public class LengthOfLastWord implements Easy {

  public static class Solution1 {
    public int lengthOfLastWord(String s) {
      int start = 0;
      int end = 0;
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if(c != ' ') {
          if(i == end)
            end++;
          else {
            start = i;
            end = i + 1;
          }
        }
      }
      return end - start;
    }
  }

  public static class Solution2 {
    public int lengthOfLastWord(String s) {
      int len = 0;
      int i = 0;
      while(i < s.length()) {
        if (s.charAt(i++) != ' ')
          ++len;
        else if (i < s.length() && s.charAt(i) != ' ')
          len = 0;

      }
      return len;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().lengthOfLastWord("Hello World "));
    System.out.println(new Solution2().lengthOfLastWord("Hello World "));
    int a = 1;
    System.out.println(a += 2);
  }

}
