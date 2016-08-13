package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */
public class _246_StrobogrammaticNumber implements Easy, PremiumQuestion {

  public interface Solution {
    boolean isStrobogrammatic(String num);
  }

  public static class Solution1 implements Solution {

    private static final int[] MAP = new int[] {
      0, 1, -1, -1, -1, -1, 9, -1, 8, 6
    };

    @Override
    public boolean isStrobogrammatic(String num) {
      if(num.isEmpty()) return false;
      int start = 0, end = num.length() - 1;
      while(start <= end) {
        int a = num.charAt(start++) - '0';
        int b = num.charAt(end--) - '0';
        if(b != MAP[a]) return false;
      }
      return true;
    }

  }

  public static void main(String args[]) {
    String num;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      num = "";
      result = s.isStrobogrammatic(num);
      System.out.println(result);

      num = "1";
      result = s.isStrobogrammatic(num);
      System.out.println(result);

      num = "11";
      result = s.isStrobogrammatic(num);
      System.out.println(result);

      num = "111";
      result = s.isStrobogrammatic(num);
      System.out.println(result);

      num = "818";
      result = s.isStrobogrammatic(num);
      System.out.println(result);

      num = "218";
      result = s.isStrobogrammatic(num);
      System.out.println(result);
    }
  }

}
