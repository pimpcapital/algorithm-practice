package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all
 * unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *  Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 *  The solution set must not contain duplicate quadruplets.
 *  For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 *
 * A solution set is:
 *  (-1,  0, 0, 1)
 *  (-2, -1, 1, 2)
 *  (-2,  0, 0, 2)
 */
public class _018_FourSum implements Medium {

  public interface Solution {
    List<List<Integer>> fourSum(int[] nums, int target);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<List<Integer>> fourSum(int[] nums, int target) {
      Arrays.sort(nums);
      return sortedNSum(nums, 0, target, 4);
    }

    private static List<List<Integer>> sortedNSum(int[] nums, int offset, int target, int n) {
      if(n < 2) throw new UnsupportedOperationException();
      if(n == 2) return sortedTwoSum(nums, offset, target);
      List<List<Integer>> results = new ArrayList<>();
      Integer prev = null;
      for(int i = offset; i < nums.length; i++) {
        int num = nums[i];
        if(prev != null && prev == num) continue;
        int subTarget = target - num;
        for(List<Integer> threeSumResult : sortedNSum(nums, i + 1, subTarget, n - 1)) {
          threeSumResult.add(0, num);
          results.add(threeSumResult);
        }
        prev = num;
      }
      return results;
    }

    private static List<List<Integer>> sortedTwoSum(int[] nums, int offset, int target) {
      int left = offset;
      int right = nums.length - 1;
      List<List<Integer>> results = new ArrayList<>();
      while(left < right) {
        int leftValue = nums[left];
        int rightValue = nums[right];
        int sum = leftValue + rightValue;
        if(sum == target) {
          List<Integer> result = new LinkedList<>();
          result.add(nums[left]);
          result.add(nums[right]);
          results.add(result);
        }
        if(sum >= target) {
          do right--; while(left < right && nums[right] == rightValue);
        } else {
          do left++; while(left < right && nums[left] == leftValue);
        }
      }
      return results;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int target;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1, 0, -1, 0, -2, 2};
      target = 0;
      result = s.fourSum(nums, target);
      for(List<Integer> combination : result) {
        System.out.print(combination);
      }
      System.out.println();

      nums = new int[] {-3,-2,-1,0,0,1,2,3};
      target = 0;
      result = s.fourSum(nums, target);
      for(List<Integer> combination : result) {
        System.out.print(combination);
      }
      System.out.println();
    }
  }

}
