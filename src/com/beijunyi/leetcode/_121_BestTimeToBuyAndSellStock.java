package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design
 * an algorithm to find the maximum profit.
 */
public class _121_BestTimeToBuyAndSellStock implements Medium {

  public interface Solution {
    int maxProfit(int[] prices);
  }

  public static class Solution1 implements Solution {
    @Override
    public int maxProfit(int[] prices) {
      int min = Integer.MAX_VALUE;
      int result = 0;
      for(int p : prices) {
        if(p < min)
          min = p;
        else {
          int profit = p - min;
          if(profit > result)
            result = profit;
        }
      }
      return result;
    }
  }

  public static void main(String args[]) {
    int[] prices;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      prices = new int[] {3,2,6,5,0,3};
      result = s.maxProfit(prices);
      System.out.println(result);
    }
  }

}
