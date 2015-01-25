package com.beijunyi.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockThree {

  /**
   * Time complexity is O(kn), space complexity can be O(n) because this DP only uses the result from last step.
   * But for cleaness this solution still used O(kn) space complexity to preserve similarity to the equations in the comments.
   *
   *  p[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
   *  p[k, i] = max(p[k, i-1], prices[i] - prices[jj] + p[k-1, jj]) { jj in range of [0, i-1] }
   *          = max(p[k, i-1], prices[i] + max(p[k-1, jj] - prices[jj]))
   *  p[0, i] = 0; 0 times transaction makes 0 profit
   *  p[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
   */
  public static class Solution2 {
    public int maxProfit(int[] prices) {
      return maxProfit(prices, 2);
    }

    private int maxProfit(int[] prices, int transactions) {
      if(prices.length <= 1) return 0;
      else {
        int[][] p = new int[transactions + 1][prices.length];
        for(int k = 1; k <= transactions; k++) {
          int balance = -prices[0];
          for(int i = 1; i < prices.length; i++) {
            p[k][i] = Math.max(p[k][i - 1], prices[i] + balance);
            balance = Math.max(balance, p[k - 1][i] - prices[i]);
          }
        }
        return p[transactions][prices.length - 1];
      }
    }
  }

  public static class Solution1 {
    public int maxProfit(int[] prices) {
      return maxProfit(prices, 2);
    }

    private int maxProfit(int[] prices, int transactions) {
      if(prices.length <= 1) return 0;
      else {
        int[] p = new int[prices.length];
        for(int k = 1; k <= transactions; k++) {
          int balance = -prices[0];
          for(int i = 1; i < prices.length; i++) {
            int profit = Math.max(p[i - 1], prices[i] + balance);
            balance = Math.max(balance, p[i] - prices[i]);
            p[i] = profit;
          }
        }
        return p[prices.length - 1];
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution2().maxProfit(new int[] {3,2,6,8,5,0,3}));
    System.out.println(new Solution1().maxProfit(new int[] {3,2,6,8,5,0,3}));
  }
}
