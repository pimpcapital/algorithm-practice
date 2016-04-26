package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the
 * fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class _322_CoinChange implements Medium {

  public interface Solution {
    int coinChange(int[] coins, int amount);
  }

  public static class Solution1 implements Solution {

    @Override
    public int coinChange(int[] coins, int amount) {
      return coinChange(coins, amount, new HashMap<Integer, Integer>());
    }

    public int coinChange(int[] coins, int amount, Map<Integer, Integer> cache) {
      if(amount == 0) return 0;
      if(amount < 0) return -1;
      if(cache.containsKey(amount))
        return cache.get(amount);
      int ret = -1;
      for(int coin : coins) {
        int count = coinChange(coins, amount - coin, cache);
        if(count != -1 && (ret == -1 || ret > count + 1)) ret = count + 1;
      }
      cache.put(amount, ret);
      return ret;
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public int coinChange(int[] coins, int amount) {
      if(amount<1) return 0;
      int[] dp = new int[amount+1];
      int sum = 0;

      while(++sum<=amount) {
        int min = -1;
        for(int coin : coins) {
          if(sum >= coin && dp[sum-coin]!=-1) {
            int temp = dp[sum-coin]+1;
            min = min<0 ? temp : (temp < min ? temp : min);
          }
        }
        dp[sum] = min;
      }
      return dp[amount];
    }
  }

  public static void main(String args[]) {
    int[] coins;
    int amount;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      coins = new int[] {1, 2, 5};
      amount = 11;
      result = s.coinChange(coins, amount);
      System.out.println(result);

      coins = new int[] {186, 419, 83, 408};
      amount = 6249;
      result = s.coinChange(coins, amount);
      System.out.println(result);
    }

  }

}
