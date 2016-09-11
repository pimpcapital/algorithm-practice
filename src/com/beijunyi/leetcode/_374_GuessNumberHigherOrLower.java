package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 *
 * Return 6.
 */
public class _374_GuessNumberHigherOrLower implements Easy {

  public interface Solution {

    void init(int num);

    int guessNumber(int n);

    int guess(int num);
  }

  private static abstract class AbstractSolution implements Solution {

    private int truth;

    @Override
    public void init(int truth) {
      this.truth = truth;
    }

    @Override
    public int guess(int num) {
      return Integer.compare(num, truth);
    }
  }

}
