package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add
 * up to a positive integer target.
 *
 * Example:
 *   nums = [1, 2, 3]
 *   target = 4
 *
 *   The possible combination ways are:
 *   (1, 1, 1, 1)
 *   (1, 1, 2)
 *   (1, 2, 1)
 *   (1, 3)
 *   (2, 1, 1)
 *   (2, 2)
 *   (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 *
 * Follow up:
 *   What if negative numbers are allowed in the given array?
 *   How does it change the problem?
 *   What limitation we need to add to the question to allow negative numbers?
 */
public class _377_CombinationSumFour implements Medium {

  public interface Solution {
    int combinationSum4(int[] nums, int target);
  }

  // top down DP (2ms)
  public static class Solution1 implements Solution, DynamicPrograming, Backtracking {

    @Override
    public int combinationSum4(int[] nums, int target) {
      Arrays.sort(nums);
      int[] cache = new int[target + 1];
      Arrays.fill(cache, -1);
      return dfs(nums, target, cache);
    }

    private int dfs(int[] nums, int target, int cache[]) {
      if(cache[target] != -1) return cache[target];
      int count = 0;
      for(int num : nums) {
        int newTarget = target - num;
        if(newTarget == 0) {
          count++;
          break; // since no duplication, it is impossible to find a new solution for a bigger i
        } else if(newTarget < 0) {
          break; // impossible to find solution after this i because of ascending order
        } else {
          count += dfs(nums, newTarget, cache);
        }
      }
      cache[target] = count;
      return count;
    }
  }

  // bottom up DP (5ms)
  public static class Solution2 implements Solution, DynamicPrograming {

    @Override
    public int combinationSum4(int[] nums, int target) {
      int[] cache = new int[target + 1];
      for(int num : nums) {
        if(num <= target) cache[num] = 1;
      }
      cache[0] = 1;
      for(int i = 1; i <= target; i++) { // for every sub target
        for(int num : nums) {
          int remain = i - num;
          if(remain > 0) cache[i] += cache[remain];
        }
      }
      return cache[target];
    }

  }

  // bottom up DP (5ms)
  public static class Solution3 implements Solution, DynamicPrograming {

    @Override
    public int combinationSum4(int[] nums, int target) {
      Arrays.sort(nums);
      int[] cache = new int[target + 1];
      cache[0] = 1;
      for(int i = 1; i <= target; i++) { // for every sub target
        for(int num : nums) {
          int remain = i - num;
          if(remain < 0) break;
          cache[i] += cache[remain];
        }
      }
      return cache[target];
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int target;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      nums = new int[] {1, 2, 3};
      target = 4;
      result = s.combinationSum4(nums, target);
      System.out.println(result);

      nums = new int[] {2, 1, 3};
      target = 35;
      result = s.combinationSum4(nums, target);
      System.out.println(result);
    }
  }

}
