package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with
 * a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 *
 * Note:
 *   All costs are positive integers.
 *
 * Follow up:
 *   Could you solve it in O(nk) runtime?
 */
public class _265_PaintHouseTwo implements Medium, PremiumQuestion {

  public interface Solution {
    int minCostII(int[][] costs);
  }

  public static class Solution1 implements Solution {
    @Override
    public int minCostII(int[][] costs) {
      if(costs.length == 0) return 0;
      int colors = costs[0].length;
      int[] costToPaintLastHouse = costs[0]; // the minimum cost to paint the last house
      for(int i = 1; i < costs.length; i++) { // for every house from 1
        int[] costToPaintCurrentHouse = new int[colors];
        for(int j = 0; j < colors; j++) { // for every color to paint
          int min = Integer.MAX_VALUE;
          for(int k = 0; k < colors; k++) { // for every color of the previous house
            if(j == k) continue; // same color: invalid, skip
            min = Math.min(min, costToPaintLastHouse[k]);
          }
          costToPaintCurrentHouse[j] = min + costs[i][j]; // the minimum cost to paint the current house
        }
        costToPaintLastHouse = costToPaintCurrentHouse;
      }
      int min = Integer.MAX_VALUE;
      for(int cost : costToPaintLastHouse) min = Math.min(cost, min);
      return min;
    }
  }

  /**
   * The idea is similar to the problem Paint House I, for each house and each color, the minimum cost of painting the
   * house with that color should be the minimum cost of painting previous houses, and make sure the previous house
   * doesn't paint with the same color.
   *
   * We can use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house, if the current
   * color's index is same as min1, then we have to go with min2, otherwise we can safely go with min1.
   *
   * The code below modifies the value of costs[][] so we don't need extra space.
   */
  public static class Solution2 implements Solution {
    @Override
    public int minCostII(int[][] costs) {
      if (costs == null || costs.length == 0) return 0;

      int houses = costs.length, colors = costs[0].length;
      // min1 is the index of the 1st-smallest cost till previous house
      // min2 is the index of the 2nd-smallest cost till previous house
      int min1 = -1, min2 = -1;

      for (int i = 0; i < houses; i++) {
        int last1 = min1, last2 = min2;
        min1 = -1; min2 = -1;

        for (int j = 0; j < colors; j++) {
          if (j != last1) {
            // current color j is different to last min1
            costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
          } else {
            costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
          }

          // find the indices of 1st and 2nd smallest cost of painting current house i
          if (min1 < 0 || costs[i][j] < costs[i][min1]) {
            min2 = min1; min1 = j;
          } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
            min2 = j;
          }
        }
      }

      return costs[houses - 1][min1];
    }
  }

  public static void main(String args[]) {
    int[][] costs;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      costs = new int[][] {
        {15,17,15,20,7,16,6,10,4,20,7,3,4}, {11,3,9,13,7,12,6,7,5,1,7,18,9}
      };
      result = s.minCostII(costs);
      System.out.println(result);
    }

  }

}
