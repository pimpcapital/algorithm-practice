package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * For example:
 *  Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Follow up:
 *  Could you do it without any loop/recursion in O(1) runtime?
 *
 * Hint:
 *  A naive implementation of the above process is trivial. Could you come up with other methods?
 */
public class _258_AddDigits implements Easy {

  public interface Solution {
    int addDigits(int num);
  }

  public static class Solution1 implements Solution {

    @Override
    public int addDigits(int num) {
      if(num == 0) return 0;
      int ret = num % 9;
      if(ret == 0) return 9;
      return ret;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public int addDigits(int num) {
      return (num - 1) % 9 + 1;
    }

  }

  public static void main(String args[]) {
    int num;
    int r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = 38;
      r = s.addDigits(num);
      System.out.println(r);

      num = 9;
      r = s.addDigits(num);
      System.out.println(r);
    }
  }

}
