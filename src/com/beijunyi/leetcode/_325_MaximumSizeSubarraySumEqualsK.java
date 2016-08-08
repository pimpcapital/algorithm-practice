package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one,
 * return 0 instead.
 *
 * Example 1:
 *   Given nums = [1, -1, 5, -2, 3], k = 3,
 *   return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 *
 * Example 2:
 *   Given nums = [-2, -1, 2, 1], k = 1,
 *   return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 *
 * Follow Up:
 *   Can you do it in O(n) time?
 */
public class _325_MaximumSizeSubarraySumEqualsK implements Medium, PremiumQuestion {

  public interface Solution {
    int maxSubArrayLen(int[] nums, int k);
  }

  public static class Solution1 implements Solution {

    @Override
    public int maxSubArrayLen(int[] nums, int k) {
      Map<Integer, Integer> valueLengthMap = new HashMap<>();
      valueLengthMap.put(0, 0);
      int max = 0;
      for(int num : nums) {
        Set<Integer> toRemove = new HashSet<>();
        Map<Integer, Integer> toUpdate = new HashMap<>();
        for(Map.Entry<Integer, Integer> valueLength : valueLengthMap.entrySet()) {
          int value = valueLength.getKey();
          int length = valueLength.getValue();
          int newValue = value + num;
          int newLength = length + 1;
          if(newValue == k) max = Math.max(max, newLength);
          toRemove.add(value);
          toUpdate.put(newValue, newLength);
          if(!toUpdate.containsKey(num)) {
            toUpdate.put(num, 1);
            if(num == k) max = Math.max(max, 1);
          }
        }
        valueLengthMap.keySet().removeAll(toRemove);
        valueLengthMap.putAll(toUpdate);
      }
      return max;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int k;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {-1, 1};
      k = 1;
      result = s.maxSubArrayLen(nums, k);
      System.out.println(result);

      nums = new int[] {1, -1, 5, -2, 3};
      k = 3;
      result = s.maxSubArrayLen(nums, k);
      System.out.println(result);

      nums = new int[] {-2, -1, 2, 1};
      k = 1;
      result = s.maxSubArrayLen(nums, k);
      System.out.println(result);
    }
  }

}
