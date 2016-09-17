package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 * Given s = "hello", return "holle".
 *
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 *
 * Note:
 * The vowels does not include the letter "y".
 */
public class _345_ReverseVowelsOfAString implements Easy {

  public interface Solution {
    String reverseVowels(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public String reverseVowels(String s) {
      char[] cs = s.toCharArray();
      int right = cs.length - 1;
      int left = 0;
      while(left < right) {
        while(left < right && !isVowel(cs[left])) left++;
        while(left < right && !isVowel(cs[right])) right--;
        char tmp = cs[left];
        cs[left] = cs[right];
        cs[right] = tmp;
        left++;
        right--;
      }
      return new String(cs);
    }

    private static boolean isVowel(char c) {
      switch(c) {
        case 'a':
        case 'A':
        case 'e':
        case 'E':
        case 'i':
        case 'I':
        case 'o':
        case 'O':
        case 'u':
        case 'U':
          return true;
        default:
          return false;
      }
    }
  }

  public static void main(String args[]) {
    String s;
    String result;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "leetcode";
      result = solution.reverseVowels(s);
      System.out.println(result);
    }
  }

}
