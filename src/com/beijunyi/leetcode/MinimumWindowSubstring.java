package com.beijunyi.leetcode;

import java.util.*;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p/>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * <p/>
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * <p/>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

  public static class Solution {

    public String minWindow(String S, String T) {
      int minlen = S.length();
      int minstart = 0;
      int minend = S.length() - 1;

      HashMap<Character, Integer> require = new HashMap<Character, Integer>();
      for(int i = 0; i < T.length(); i++) {
        char c = T.charAt(i);
        require.put(c, require.containsKey(c) ? require.get(c) + 1 : 1);
      }

      int count = T.length();
      int left = 0;                                                                  // left, i index included in substring
      for(int i = 0; i < S.length(); i++) {
        char c = S.charAt(i);
        // move right pointer
        if(require.containsKey(c)) {
          if(require.get(c) > 0)
            count--;                                                                 // extra char does not affect count
          require.put(c, require.get(c) - 1);
        }

        // move left pointer
        if(count == 0) {                                                             // require satisfied, count only use for first time require satisfied
          char cleft = S.charAt(left);
          while(!require.containsKey(cleft) || require.get(cleft) < 0) {             // not required char OR have extra, can move
            if(require.containsKey(cleft)) {
              require.put(cleft, require.get(cleft) + 1);
            }
            left++;
            cleft = S.charAt(left);
          }
          // update index
          if(minlen > i - left + 1) {
            minstart = left;
            minend = i;
            minlen = minend - minstart + 1;
          }
        }
      }
      if(count != 0)
        return "";
      return S.substring(minstart, minend + 1);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
  }

}
