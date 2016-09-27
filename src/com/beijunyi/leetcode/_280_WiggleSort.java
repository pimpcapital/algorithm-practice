package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Swapping;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class _280_WiggleSort implements Medium, PremiumQuestion {

  public interface Solution {
    void wiggleSort(int[] nums);
  }

  public static class Solution1 implements Solution, Swapping {

    @Override
    public void wiggleSort(int[] nums) {
      if(nums.length < 2) return;
      for(int i = 1; i < nums.length; i++) {
        boolean asc = i % 2 == 1;
        if(asc && nums[i] < nums[i - 1] || !asc && nums[i] > nums[i - 1]) swap(nums, i, i - 1);
      }
    }

    private static void swap(int[] nums, int a, int b) {
      int tmp = nums[a];
      nums[a] = nums[b];
      nums[b] = tmp;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {3, 5, 2, 1, 6, 4};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[] {1, 2, 3, 4};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[] {4, 3, 2, 1};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));
    }

  }

}
