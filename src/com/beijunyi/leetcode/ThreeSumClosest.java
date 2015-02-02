package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest implements Medium {

  public static class Solution {
    public int threeSumClosest(int[] num, int target) {
      Arrays.sort(num);
      Integer result = null;

      for(int i = 0; i < num.length - 2; i++) {
        for(int j = i + 1, k = num.length - 1; j < k; ) {
          int a = num[i];
          int b = num[j];
          int c = num[k];
          int currentResult = a + b + c;
          if(result == null || Math.abs(result - target) > Math.abs(currentResult - target))
            result = currentResult;
          if(currentResult == target)
            return result;
          if(currentResult > target)
            k--;
          else
            j++;
        }
      }

      if(result == null)
        throw new RuntimeException();

      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
  }

}
