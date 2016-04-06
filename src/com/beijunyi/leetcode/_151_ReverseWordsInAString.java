package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;

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
public class _151_ReverseWordsInAString implements Medium {

  public static class Solution1 {
    public String reverseWords(String s) {
      if(s.length() == 0)
        return s;
      StringBuilder sb = new StringBuilder();
      int end = s.length();
      for(int i = s.length() - 1; i >= -1; i--) {
        char c = i != -1 ? s.charAt(i) : ' ';
        if(c == ' ') {
          if(i + 1 != end) {
            for(int j = i + 1; j < end; j++)
              sb.append(s.charAt(j));
            sb.append(' ');
          }
          end = i;
        }
      }
      if(sb.length() > 0)
        sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
    }
  }

  public static class Solution2 {
    public String reverseWords(String s) {
      String[] reversedWords = new StringBuilder(s.trim()).reverse().toString().split(" +");
      StringBuilder sb = new StringBuilder();
      for(String word : reversedWords)
        sb.append(" ").append(new StringBuilder(word).reverse().toString());
      return sb.length() == 0 ? "" : sb.deleteCharAt(0).toString();
    }
  }


  public static void main(String args[]) {
    System.out.println(new Solution1().reverseWords("   a   b "));
    System.out.println(new Solution2().reverseWords("   a   b "));
  }

}
