package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *   -1 : My number is lower
 *   1 : My number is higher
 *   0 : Congrats! You got it!
 * Example:
 *   n = 10, I pick 6.
 *
 * Return 6.
 */
public class _374_GuessNumberHigherOrLower implements Easy {

  public interface Solution {

    void init(int num);

    int guessNumber(int n);

  }

  private static abstract class AbstractSolution implements Solution {

    private int truth;

    @Override
    public void init(int truth) {
      this.truth = truth;
    }

    public int guess(int num) {
      return Integer.compare(truth, num);
    }

  }

  public static class Solution1 extends AbstractSolution {

    @Override
    public int guessNumber(int n) {
      int left = 1;
      int right = n;
      while(left < right) {
        int mid = left + (right - left) / 2;
        int result = guess(mid);
        if(result == 0) return mid;
        if(result == 1) left = mid + 1;
        else right = mid - 1;
      }
      return left;
    }

  }

  public static void main(String arg[]) {
    int truth;
    int max;

    for(Solution s : Arrays.asList(new Solution1())) {
      truth = 6;
      max = 10;
      s.init(truth);
      System.out.println(s.guessNumber(max));
    }
  }

}
