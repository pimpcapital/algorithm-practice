package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example:
 *   (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 *   (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *
 * Note:
 *   You may assume all input has valid answer.
 *
 * Follow Up:
 *   Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class _324_WiggleSortTwo implements Medium {

  public interface Solution {
    void wiggleSort(int[] nums);
  }

  public static class Solution1 implements Solution {

    @Override
    public void wiggleSort(int[] nums) {

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
      nums = new int[]{1, 5, 1, 1, 6, 4};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[]{1, 3, 2, 2, 3, 1};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[]{1, 1, 2, 2, 2, 1};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[]{1, 1, 2, 2, 2, 1, 1};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[]{2, 2, 2, 1, 1, 1, 1};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));
    }
  }

}
