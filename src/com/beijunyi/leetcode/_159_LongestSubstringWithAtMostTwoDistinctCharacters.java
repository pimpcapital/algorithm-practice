package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;

public class _159_LongestSubstringWithAtMostTwoDistinctCharacters implements Hard, PremiumQuestion {

  public interface Solution {
    int lengthOfLongestSubstringTwoDistinct(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public int lengthOfLongestSubstringTwoDistinct(String s) {
      if(s.length() < 3) return s.length();
      int max = 0;
      int begin = 0;
      int lastA = -1;
      int lastB = -1;
      for(int i = 0; i <= s.length(); i++) {
        if(i < s.length() && (lastA == -1 || s.charAt(lastA) == s.charAt(i))) {
          lastA = i;
        } else if(i < s.length() && (lastB == -1 || s.charAt(lastB) == s.charAt(i))) {
          lastB = i;
        } else {
          if(lastA > lastB) {
            max = Math.max(lastA - begin, max);
            begin = lastB + 1;
            lastB = i;
          } else {
            max = Math.max(lastB - begin, max);
            begin = lastA + 1;
            lastA = i;
          }
        }
      }
      return max + 1;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public int lengthOfLongestSubstringTwoDistinct(String s) {
      Map<Character, Integer> lastSeen = new HashMap<>();
      int low = 0, max = 0;
      for (int i = 0; i < s.length(); i++) {
        lastSeen.put(s.charAt(i), i);
        if(lastSeen.size() > 2) {
          int toRemoveLastSeen = i;
          for (Integer val : lastSeen.values()) toRemoveLastSeen = Math.min(val, toRemoveLastSeen);
          lastSeen.remove(s.charAt(toRemoveLastSeen));
          low = toRemoveLastSeen + 1;
        }
        max = Math.max(max, i - low + 1);
      }
      return max;
    }
  }

  public static void main(String args[]) {
    String str;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      str = "";
      result = s.lengthOfLongestSubstringTwoDistinct(str);
      System.out.println(result);

      str = "a";
      result = s.lengthOfLongestSubstringTwoDistinct(str);
      System.out.println(result);

      str = "ab";
      result = s.lengthOfLongestSubstringTwoDistinct(str);
      System.out.println(result);

      str = "eceba";
      result = s.lengthOfLongestSubstringTwoDistinct(str);
      System.out.println(result);

      str = "ecebbbbda";
      result = s.lengthOfLongestSubstringTwoDistinct(str);
      System.out.println(result);

      System.out.println();
    }
  }

}
