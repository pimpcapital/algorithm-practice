package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

public class _263_UglyNumber implements Easy {

  public interface Solution {
    boolean isUgly(int num);
  }

  /**
   * Time: O(log(n)), Space: O(1) where
   *   n is the value of the number
   */
  public static class Solution1 implements Solution {
    @Override
    public boolean isUgly(int num) {
      return num > 0 && isUgly(num, new int[] {2, 3, 5});
    }

    private static boolean isUgly(int num, int[] uglyFactors) {
      for(int f : uglyFactors) {
        while(num > 1) {
          if(num % f == 0) num /= f;
          else break;
        }
      }

      return num == 1;
    }
  }

  /**
   * Time: O(log(n)), Space: O(1) where
   *   n is the value of the number
   */
  public static class Solution2 implements Solution {
    @Override
    public boolean isUgly(int num) {
      for(int i = 2; i < 6 && num > 0; i++)
        while(num % i == 0) num /= i;
      return num == 1;
    }
  }

  public static void main(String args[]) {
    int num;
    boolean r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = 0;
      r = s.isUgly(num);
      System.out.println(r);

      num = 1;
      r = s.isUgly(num);
      System.out.println(r);

      num = 6;
      r = s.isUgly(num);
      System.out.println(r);

      num = 8;
      r = s.isUgly(num);
      System.out.println(r);

      num = 14;
      r = s.isUgly(num);
      System.out.println(r);
    }

  }

}
