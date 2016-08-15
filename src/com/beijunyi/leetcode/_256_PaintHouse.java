package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

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

  public static class Solution1 implements Solution {

    @Override
    public int minCost(int[][] costs) {
      return 0;
    }

  }

}
