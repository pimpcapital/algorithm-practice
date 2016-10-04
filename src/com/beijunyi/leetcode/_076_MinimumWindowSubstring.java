package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.beijunyi.leetcode.category.difficulty.Hard;

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
public class _076_MinimumWindowSubstring implements Hard {

  public interface Solution {
    String minWindow(String S, String T);
  }

  public static class Solution1 implements Solution {

    @Override
    public String minWindow(String S, String T) {
      int minlen = S.length();
      int minstart = 0;
      int minend = S.length() - 1;

      HashMap<Character, Integer> require = new HashMap<>();
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

  public static class Solution2 implements Solution {
    @Override
    public String minWindow(String S, String T) {
      Map<Character, Integer> requirements = processWindowRequirements(T);
      int right = 0;
      int left = 0;
      int violated = requirements.size();

      int minIndex = -1;
      int minLength = Integer.MAX_VALUE;
      while(right < S.length()) {
        while(violated > 0 && right < S.length()) {
          char c = S.charAt(right++);
          if(!requirements.containsKey(c)) continue;
          int count = requirements.get(c);
          if(++count == 0) violated--;
          requirements.put(c, count);
        }
        while(violated == 0 && left < right) {
          char c = S.charAt(left++);
          if(!requirements.containsKey(c)) continue;
          int count = requirements.get(c);
          if(--count == -1) violated++;
          requirements.put(c, count);
          if(violated == 1) {
            int validLeft = left - 1;
            int length = right - validLeft;
            if(length < minLength) {
              minIndex = validLeft;
              minLength = length;
            }
          }
        }
      }
      return minIndex == -1 ? "" : S.substring(minIndex, minIndex + minLength);
    }

    private static Map<Character, Integer> processWindowRequirements(String T) {
      Map<Character, Integer> ret = new HashMap<>();
      for(char c : T.toCharArray())
        ret.compute(c, (key, count) -> count == null ? -1 : --count);
      return ret;
    }
  }

  public static void main(String args[]) {
    String S;
    String T;
    String result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      S = "ADOBECODEBANC";
      T = "ABC";
      result = solution.minWindow(S, T);
      System.out.println(result);
    }
  }

}
