package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BinarySearch;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 */
public class _153_FindMinimumInRotatedSortedArray implements Medium {

  public interface Solution {
    int findMin(int[] nums);
  }

  /**
   * Side note: see _081_SearchInRotatedSortedArrayTwo
   */
  public static class Solution1 implements Solution, BinarySearch {
    @Override
    public int findMin(int[] nums) {
      if(nums == null || nums.length == 0) {
        return Integer.MIN_VALUE;
      }
      int left = 0, right = nums.length - 1;
      while(left < right - 1) {  // while (left < right-1) is a useful technique
        int mid = left + (right - left) / 2;
        if(nums[mid] > nums[right]) {
          left = mid;
        } else {
          right = mid;
        }
      }
      if(nums[left] > nums[right]) {
        return nums[right];
      }
      return nums[left];
    }
  }

  public static void main(String args[]) {

    int[] nums;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1, 2};
      result = s.findMin(nums);
      System.out.println(result);

      nums = new int[] {2, 1};
      result = s.findMin(nums);
      System.out.println(result);

      nums = new int[] {1, 2, 3};
      result = s.findMin(nums);
      System.out.println(result);

      nums = new int[] {2, 3, 1};
      result = s.findMin(nums);
      System.out.println(result);

      nums = new int[] {3, 1, 2};
      result = s.findMin(nums);
      System.out.println(result);
    }

  }

}
