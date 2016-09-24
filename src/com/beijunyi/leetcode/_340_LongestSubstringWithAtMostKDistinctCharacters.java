package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.SlidingWindow;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * For example, Given s = “eceba” and k = 2,
 *
 * T is "ece" which its length is 3.
 */
public class _340_LongestSubstringWithAtMostKDistinctCharacters implements Hard {

  public interface Solution {
    int lengthOfLongestSubstringKDistinct(String s, int k);
  }

  public static class Solution1 implements Solution, SlidingWindow {
    @Override
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
      int max = 0;
      int left = 0;
      int right = 0;
      int[] counts = new int[128];
      int distincts = 0;

      for(; right < s.length(); right++) {
        char rc = s.charAt(right);
        if(++counts[rc] == 1) distincts++;
        while(distincts > k) {
          char lc = s.charAt(left++);
          if(--counts[lc] == 0) distincts--;
        }
        max = Math.max(max, right - left + 1);
      }

      return max;
    }
  }

  public static void main(String args[]) {
    String s;
    int k;
    int result;

    for(Solution solution : Arrays.asList(new Solution1())) {
      s = "eceba";
      k = 2;
      result = solution.lengthOfLongestSubstringKDistinct(s, k);
      System.out.println(result);
    }
  }

}
