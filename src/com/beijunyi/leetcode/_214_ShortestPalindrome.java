package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.KMP;

public class _214_ShortestPalindrome implements Hard {

  public interface Solution {
    String shortestPalindrome(String s);
  }

  public static class Solution1 implements Solution {
    @Override
    public String shortestPalindrome(String s) {
      int left = 0;
      for(int right = s.length() - 1; right >= 0; right--) {
        if(s.charAt(right) == s.charAt(left)) left++;
      }
      if(left == s.length()) return s;
      String suffix = s.substring(left);
      return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, left)) + suffix;
    }
  }

  public static class Solution2 implements Solution, KMP {
    @Override
    public String shortestPalindrome(String s) {
      String r = new StringBuilder(s).reverse().toString();
      int[] lps = getLPS(s + '|' + r);
      return r.substring(0, r.length() - lps[lps.length - 1]) + s;
    }

    // KMP get longest prefix and suffix count
    int[] getLPS(String s) {
      int[] lps = new int[s.length()];
      int i = 1, len = 0;

      while (i < s.length()) {
        if (s.charAt(i) == s.charAt(len))
          lps[i++] = ++len;
        else if (len == 0)
          lps[i++] = 0;
        else
          len = lps[len - 1];
      }

      return lps;
    }
  }

  public static class Solution3 implements Solution {
    @Override
    public String shortestPalindrome(String s) {
      if(s.length()<=1) return s;
      String new_s = s+"#"+new StringBuilder(s).reverse().toString();
      int[] position = new int[new_s.length()];

      for(int i=1;i<position.length;i++)
      {
        int pre_pos = position[i-1];
        while(pre_pos>0 && new_s.charAt(pre_pos)!=new_s.charAt(i))
          pre_pos = position[pre_pos-1];
        position[i] = pre_pos+((new_s.charAt(pre_pos)==new_s.charAt(i))?1:0);
      }

      return new StringBuilder(s.substring(position[position.length-1])).reverse().toString()+s;
    }
  }

  public static void main(String args[]) {
    String str;
    String result;

    for(Solution s : Arrays.asList(new Solution2())) {
      str = "aacecaaa";
      result = s.shortestPalindrome(str);
      System.out.println(result);

      str = "abcd";
      result = s.shortestPalindrome(str);
      System.out.println(result);

      str = "abbabaab";
      result = s.shortestPalindrome(str);
      System.out.println(result); // baababbabaab

      str = "ababbbabbaba";
      result = s.shortestPalindrome(str);
      System.out.println(result); // ababbabbbababbbabbaba
    }
  }

}
