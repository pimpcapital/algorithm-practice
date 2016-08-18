package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 *   n and k are non-negative integers.
 */
public class _276_PaintFence implements Easy, PremiumQuestion {
  public interface Solution {
    int numWays(int n, int k);
  }

  public static class Solution1 implements Solution, DynamicPrograming {

    @Override
    public int numWays(int n, int k) {
      if(n == 0) return 0;
      if(n == 1) return k;

      int diff = k * (k - 1); // scenarios where the last 2 have different colors
      int same = k; // scenarios where the last 2 the same color
      for(int i = 2; i < n; i++) {
        int temp = diff;
        diff = (diff + same) * (k - 1); // next can be one of (k - 1) to make the last 2 posts with different color
        same = temp; // (diff + same) * 1 - same = diff
      }
      return diff + same;
    }

  }

  public static void main(String args[]) {
    int n;
    int k;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 2;
      k = 1;
      result = s.numWays(n, k);
      System.out.println(result);

      n = 3;
      k = 2;
      result = s.numWays(n, k);
      System.out.println(result);
    }
  }

}
