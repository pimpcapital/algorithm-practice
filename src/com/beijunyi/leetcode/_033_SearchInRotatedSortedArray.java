package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.BinarySearch;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */
public class _033_SearchInRotatedSortedArray implements Hard {

  public interface Solution {
    int search(int[] A, int target);
  }

  public static class Solution1 implements Solution, BinarySearch {

    @Override
    public int search(int[] A, int target) {
      return search(A, target, 0, A.length);
    }

    public int search(int[] A, int target, int left, int right) {
      if(right - left == 0)
        return -1;
      int mid = (left + right - 1) / 2;
      int midValue = A[mid];
      if(midValue == target)
        return mid;
      int leftValue = A[left];
      int rightValue = A[right - 1];
      if(midValue < target) {
        if(rightValue < midValue || rightValue >= target)
          return search(A, target, mid + 1, right);
        return search(A, target, left, mid);
      } else {
        if(leftValue > midValue || leftValue <= target)
          return search(A, target, left, mid);
        return search(A, target, mid + 1, right);
      }
    }
  }

  public static class Solution2 implements Solution, BinarySearch {
    @Override
    public int search(int[] nums, int target) {
      int minIdx = findMinIdx(nums);
      int m = nums.length;
      int start = (target <= nums[m - 1]) ? minIdx : 0;
      int end = (target > nums[m - 1]) ? minIdx : m - 1;

      while(start <= end) {
        int mid = start + (end - start) / 2;
        if(nums[mid] == target) return mid;
        else if (target > nums[mid]) start = mid + 1;
        else end = mid - 1;
      }
      return -1;
    }

    private static int findMinIdx(int[] nums) {
      int start = 0, end = nums.length - 1;
      while(start < end) {
        int mid = start + (end -  start) / 2;
        if(nums[mid] > nums[end]) start = mid + 1;
        else end = mid;
      }
      return start;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int target;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {4, 5, 6, 7, 0, 1, 2};
      target = 5;
      result = s.search(nums, target);
      System.out.println(result);

      System.out.println();
    }

  }
}
