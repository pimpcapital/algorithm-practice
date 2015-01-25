package com.beijunyi.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSellStock {
  public static class Solution {
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
    System.out.println(new Solution().maxProfit(new int[] {3,2,6,5,0,3}));
  }

}
