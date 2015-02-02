package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given an input string, reverse the string word by word.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 * Clarification:
 *
 * What constitutes a word?
 *   A sequence of non-space characters constitutes a word.
 *
 * Could the input string contain leading or trailing spaces?
 *   Yes. However, your reversed string should not contain leading or trailing spaces.
 *
 * How about multiple spaces between two words?
 *   Reduce them to a single space in the reversed string.
 */
public class ReverseWordsInAString implements Medium {

  public static class Solution {
    public String reverseWords(String s) {
      String[] reversedWords = new StringBuilder(s.trim()).reverse().toString().split(" +");
      StringBuilder sb = new StringBuilder();
      for(String word : reversedWords)
        sb.append(" ").append(new StringBuilder(word).reverse().toString());
      return sb.length() == 0 ? "" : sb.deleteCharAt(0).toString();
    }
  }


  public static void main(String args[]) {
    System.out.println(new Solution().reverseWords("   a   b "));
  }

}
