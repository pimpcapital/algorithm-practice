package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;

public class _214_ShortestPalindrome implements Hard {

  public interface Solution {
    String shortestPalindrome(String s);
  }

  public static class Solution1 implements Solution {
    @Override
    public String shortestPalindrome(String s) {
      if(s.length() < 2) return s;
      int left = 0;
      int right = s.length() - 1;
      int leftMax = -1;
      int rightMin = -1;
      while(left <= right) {
        char lc = s.charAt(left);
        char rc = s.charAt(right);
        if(lc == rc) {
          if(leftMax == -1) {
            leftMax = left;
            rightMin = right;
          }
          left++;
          right--;
        } else {
          leftMax = -1;
          rightMin = -1;
          right--;
        }
      }
      return new StringBuilder().append(s.substring(rightMin + leftMax + 1)).reverse() + s;
    }
  }

  public static void main(String args[]) {
    String str;
    String result;

    for(Solution s : Arrays.asList(new Solution1())) {
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
