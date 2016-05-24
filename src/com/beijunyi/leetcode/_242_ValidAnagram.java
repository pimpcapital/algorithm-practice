package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 *
 * For example,
 *  s = "anagram", t = "nagaram", return true.
 *  s = "rat", t = "car", return false.
 *
 * Note:
 *  You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 *  What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class _242_ValidAnagram implements Easy {

  public interface Solution {
    boolean isAnagram(String s, String t);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isAnagram(String s, String t) {
      int length = s.length();
      if(t.length() != length) return false;
      int[] counts = new int[26];
      for(int i = 0; i < length; i++) counts[s.charAt(i) - 'a']++;
      for(int i = 0; i < length; i++) counts[t.charAt(i) - 'a']--;
      for(int i = 0; i < 26; i++) if(counts[i] != 0) return false;
      return true;
    }

  }

  public static void main(String args[]) {
    String s;
    String t;
    boolean result;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "anagram";
      t = "nagaram";
      result = solution.isAnagram(s, t);
      System.out.println(result);

      s = "rat";
      t = "car";
      result = solution.isAnagram(s, t);
      System.out.println(result);
    }
  }

}
