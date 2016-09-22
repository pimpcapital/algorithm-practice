package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 *   s = "leetcode"
 *   return 0.
 *
 *   s = "loveleetcode",
 *   return 2.
 *
 * Note: You may assume the string contain only lowercase letters.
 */
public class _387_FirstUniqueCharacterInAString implements Easy {

  public interface Solution {
    int firstUniqChar(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public int firstUniqChar(String s) {
      int[] minIndex = new int[26];
      Arrays.fill(minIndex, -1); // state: not applicable

      for(int i = 0; i < s.length(); i++) {
        int c = s.charAt(i) - 'a';
        if(minIndex[c] == -1) minIndex[c] = i;
        else minIndex[c] = -2; // state: repeated
      }

      int min = -1;
      for(int index : minIndex) {
        if(index >= 0 && (min == -1 || index < min)) min = index;
      }

      return min;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public int firstUniqChar(String s) {
      int[] freq = new int[26];
      for(int i = 0; i < s.length(); i ++)
        freq[s.charAt(i) - 'a']++;
      for(int i = 0; i < s.length(); i ++)
        if(freq[s.charAt(i) - 'a'] == 1) return i;
      return -1;
    }

  }

  public static void main(String args[]) {
    String s;
    int result;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "leetcode";
      result = solution.firstUniqChar(s);
      System.out.println(result);
    }
  }


}
