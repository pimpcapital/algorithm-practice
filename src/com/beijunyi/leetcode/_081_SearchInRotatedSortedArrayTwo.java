package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;

/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 *
 * Write a function to determine if a given target is in the array.
 */
public class _081_SearchInRotatedSortedArrayTwo implements Medium {

  public interface Solution {
    boolean search(int[] nums, int target);
  }

  /**
   * Time: average O(log(n)), worst O(n)
   * Space: O(log(n))
   */
  public static class Solution1 implements Solution, Recursive, BinarySearch {

    @Override
    public boolean search(int[] nums, int target) {
      return nums.length != 0 && search(nums, 0, nums.length, target);
    }

    private static boolean search(int[] nums, int left, int right, int target) {
      int mid = (left + right) / 2;
      if(nums[mid] == target) return true;

      if(left != mid)
        if(!isSubArraySorted(nums, left, mid)) {
          if(search(nums, left, mid, target)) return true;
        } else {
          if(isTargetInRange(nums, left, mid, target)) return search(nums, left, mid, target);
        }
      if(mid + 1 != right)
        if(!isSubArraySorted(nums, mid + 1, right)) {
          if(search(nums, mid + 1, right, target)) return true;
        } else {
          if(isTargetInRange(nums, mid + 1, right, target)) return search(nums, mid + 1, right, target);
        }
      return false;
    }

    private static boolean isSubArraySorted(int[] nums, int left, int right) {
      int leftValue = nums[left];
      int rightValue = nums[right - 1];
      return leftValue < rightValue;
    }

    private static boolean isTargetInRange(int[] nums, int left, int right, int target) {
      int leftValue = nums[left];
      int rightValue = nums[right - 1];
      return target >= leftValue && target <= rightValue;
    }

  }

  /**
   * Time: average O(log(n)), worst O(n)
   * Space: O(1)
   */
  public static class Solution2 implements Solution, Iterative, BinarySearch {

    @Override
    public boolean search(int[] nums, int target) {
      int l = 0, r = nums.length - 1;
      while(l <= r) {
        int m = l + (r - l) / 2;
        if(nums[m] == target) return true; //return m in Search in Rotated Array I
        if(nums[l] < nums[m]) { //left half is sorted
          if(nums[l] <= target && target < nums[m])
            r = m - 1;
          else
            l = m + 1;
        } else if(nums[l] > nums[m]) { //right half is sorted
          if(nums[m] < target && target <= nums[r])
            l = m + 1;
          else
            r = m - 1;
        } else l++;
      }
      return false;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int target;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {1};
      target = 0;
      result = s.search(nums, target);
      System.out.println(result);
    }
  }

}
