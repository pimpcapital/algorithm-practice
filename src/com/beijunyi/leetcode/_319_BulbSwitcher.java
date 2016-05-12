package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On
 * the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round,
 * you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 *
 * Example:
 *
 * Given n = 3.
 *
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 *
 * So you should return 1, because there is only one bulb is on.
 */
public class _319_BulbSwitcher implements Medium {

  public interface Solution {
    int bulbSwitch(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public int bulbSwitch(int n) {
      int ret = 0;
      while((ret + 1) * (ret + 1) <= n) ret++;
      return ret;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public int bulbSwitch(int n) {
      return (int) Math.sqrt(n);
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 100;
      result = s.bulbSwitch(n);
      System.out.println(result);

      n = 123;
      result = s.bulbSwitch(n);
      System.out.println(result);
    }

  }

}
