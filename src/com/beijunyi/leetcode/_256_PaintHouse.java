package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;
import com.beijunyi.leetcode.category.solution.InputModification;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
 * houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so
 * on... Find the minimum cost to paint all houses.
 *
 * Note:
 *   All costs are positive integers.
 */
public class _256_PaintHouse implements Medium, PremiumQuestion {

  public interface Solution {
    int minCost(int[][] costs);
  }

  public static class Solution1 implements Solution, DynamicPrograming {

    @Override
    public int minCost(int[][] costs) {
      if(costs.length == 0) return 0;
      int[] costToPaintLastHouse = costs[0]; // the minimum cost to paint the last house
      for(int i = 1; i < costs.length; i++) { // for every house from 1
        int[] costToPaintCurrentHouse = new int[3];
        for(int j = 0; j < 3; j++) { // for every color to paint
          int min = Integer.MAX_VALUE;
          for(int k = 0; k < 3; k++) { // for every color of the previous house
            if(j == k) continue; // same color: invalid, skip
            min = Math.min(min, costToPaintLastHouse[k]);
          }
          costToPaintCurrentHouse[j] = min + costs[i][j]; // the minimum cost to paint the current house
        }
        costToPaintLastHouse = costToPaintCurrentHouse;
      }
      return Math.min(costToPaintLastHouse[0], Math.min(costToPaintLastHouse[1], costToPaintLastHouse[2]));
    }

  }

  public static class Solution2 implements Solution, DynamicPrograming, InputModification {
    @Override
    public int minCost(int[][] costs) {
      if(costs.length == 0) return 0;
      for(int i = 1; i < costs.length; i++){
        costs[i][0] += Math.min(costs[i - 1][1],costs[i - 1][2]);
        costs[i][1] += Math.min(costs[i - 1][0],costs[i - 1][2]);
        costs[i][2] += Math.min(costs[i - 1][1],costs[i - 1][0]);
      }
      int last = costs.length - 1;
      return Math.min(Math.min(costs[last][0], costs[last][1]), costs[last][2]);
    }
  }

  public static void main(String args[]) {
    int[][] costs;
    int result;
    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      costs = new int[][] {
        {17,7,13},{8,14,4},{13,20,16},{3,2,15},{11,17,19},{18,2,3}
      };
      result = s.minCost(costs);
      System.out.println(result);
    }

  }

}
