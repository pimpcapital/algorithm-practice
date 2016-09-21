package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 *
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the
 * number I picked.
 *
 * Example:
 *   n = 10, I pick 8.
 *
 *   First round:  You guess 5, I tell you that it's higher. You pay $5.
 *   Second round: You guess 7, I tell you that it's higher. You pay $7.
 *   Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *
 *   Game over. 8 is the number I picked.
 *
 *   You end up paying $5 + $7 + $9 = $21.
 *
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 *
 * Hint:
 *   1) The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is
 *      to minimize the expected loss. Here, we are interested in the first scenario.
 *   2) Take a small example (n = 3). What do you end up paying in the worst case?
 *   3) Check out this article if you're still stuck.
 *   4) The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic
 *      programming.
 *   5) As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of
 *      the worst-case loss?
 */
public class _375_GuessNumberHigherOrLowerTwo implements Medium {

  public interface Solution {
    int getMoneyAmount(int n);
  }

  public static class Solution1 implements Solution, DynamicPrograming {

    @Override
    public int getMoneyAmount(int n) {
      int[] cache = new int[n + 1];
      for(int i = 2; i <= n; i++) calculateTotal(i, cache);
      return cache[n];
    }

    private void calculateTotal(int n, int[] cache) {
      int guess = n / 2;
      int min = Integer.MAX_VALUE;
      while(guess < n) {
        int left = guess - 1;
        int leftTotal = cache[left];
        int right = n - 1 - left;
        int rightTotal = right > 1 ? cache[right] + guess : 0;
        int sum = guess + Math.max(leftTotal, rightTotal);
        if(sum < min) {
          min = sum;
          guess++;
        }
        else
          break;
      }
      cache[n] = min;
    }

  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
//      n = 1;
//      result = s.getMoneyAmount(n);
//      System.out.println(result);
//
//      n = 2;
//      result = s.getMoneyAmount(n);
//      System.out.println(result);
//
//      n = 3;
//      result = s.getMoneyAmount(n);
//      System.out.println(result);
//
//      n = 4;
//      result = s.getMoneyAmount(n);
//      System.out.println(result);
//
//      n = 5;
//      result = s.getMoneyAmount(n);
//      System.out.println(result);

      n = 12;
      result = s.getMoneyAmount(n);
      System.out.println(result); //21

//      n = 13;
//      result = s.getMoneyAmount(n);
//      System.out.println(result); //24
//
//      n = 15;
//      result = s.getMoneyAmount(n);
//      System.out.println(result); //30
    }
  }

}
