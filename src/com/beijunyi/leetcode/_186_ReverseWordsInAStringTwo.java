package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 *
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 * Could you do it in-place without allocating extra space?
 */
public class _186_ReverseWordsInAStringTwo implements Medium, PremiumQuestion {

  public interface Solution {
    void reverseWords(char[] s);
  }

  public static class Solution1 implements Solution {

    @Override
    public void reverseWords(char[] s) {
      reverseStr(s, 0, s.length);
      int start = 0;
      for(int end = 1; end <= s.length; end++) {
        if(end == s.length || s[end] == ' ') {
          reverseStr(s, start, end);
          start = end + 1;
          end = start; // it becomes start + 1 when iteration finishes
        }
      }
    }

    private static void reverseStr(char[] s, int start, int end) {
      int length = end - start;
      for(int i = 0; i < length / 2; i++) {
        int left = i + start;
        int right = end - i - 1;
        char tmp = s[left];
        s[left] = s[right];
        s[right] = tmp;
      }
    }

  }

  public static void main(String args[]) {
    char[] s;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "the sky is blue".toCharArray();
      solution.reverseWords(s);
      System.out.println(new String(s));

      s = "a b c d e".toCharArray();
      solution.reverseWords(s);
      System.out.println(new String(s));
    }

  }

}
