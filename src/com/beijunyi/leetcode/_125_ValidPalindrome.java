package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 *
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 *
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class _125_ValidPalindrome implements Easy {

  public static class Solution {
    public boolean isPalindrome(String s) {
      for(int l = 0, r = s.length() - 1; l < r; l++, r--) {
        char lc;
        do {
          lc = Character.toLowerCase(s.charAt(l));
        } while(!Character.isLetterOrDigit(lc) && l++ < r);

        char rc;
        do {
          rc = Character.toLowerCase(s.charAt(r));
        } while(!Character.isLetterOrDigit(rc) && l < r--);

        if(lc != rc)
          return false;
      }
      return true;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().isPalindrome("A man, a plan, a canal: Panama"));
  }

}
