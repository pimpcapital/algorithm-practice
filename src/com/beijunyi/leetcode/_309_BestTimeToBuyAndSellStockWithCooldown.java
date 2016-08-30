package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and
 * sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *   prices = [1, 2, 3, 0, 2]
 *   maxProfit = 3
 *   transactions = [buy, sell, cooldown, buy, sell]
 */
public class _309_BestTimeToBuyAndSellStockWithCooldown implements Medium {

  public interface Solution {
    int maxProfit(int[] prices);
  }

  public static class Solution1 implements Solution, DynamicPrograming {

    @Override
    public int maxProfit(int[] prices) {
      if(prices.length < 2) return 0;

      // profits for the 3 possibilities before the 3rd day starts
      int canBuy = 0;
      int canSell = -Math.min(prices[0], prices[1]);
      int cooldown = prices[1] - prices[0];

      for(int i = 2; i < prices.length; i++) {
        int newCanBuy = Math.max(cooldown, canBuy);
        int newCanSell = Math.max(canSell, canBuy - prices[i]);
        int newCooldown = canSell + prices[i];

        canBuy = newCanBuy;
        canSell = newCanSell;
        cooldown = newCooldown;
      }
      return Math.max(canBuy, cooldown);
    }

  }

  public static void main(String args[]) {
    int[] prices;
    int profit;

    for(Solution s : Arrays.asList(new Solution1())) {
      prices = new int[] {1, 2, 3, 0, 2};
      profit = s.maxProfit(prices);
      System.out.println(profit);

      prices = new int[] {1, 7, 2, 4};
      profit = s.maxProfit(prices);
      System.out.println(profit);
    }
  }

}
