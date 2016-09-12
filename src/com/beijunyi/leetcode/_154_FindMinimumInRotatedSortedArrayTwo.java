package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * Find the minimum element.
 * The array may contain duplicates.
 */
public class _154_FindMinimumInRotatedSortedArrayTwo implements Hard {

  public interface Solution {
    int findMin(int[] nums);
  }

  public static class Solution1 implements Solution, Recursive {

    @Override
    public int findMin(int[] nums) {
      return findMin(nums, 0, nums.length - 1);
    }

    private static int findMin(int[] nums, int left, int right) {
      int leftValue = nums[left];
      int rightValue = nums[right];
      int mid = left + (right - left) / 2;
      if(mid == left) return Math.min(leftValue, rightValue);
      int midValue = nums[mid];

      if(leftValue < midValue) {
        if(rightValue >= midValue) return leftValue; // [1, 2, 3, 4, 5] [1, 2, 3, 3, 3]
        else return findMin(nums, mid + 1, right); // [2, 3, 4, 5, 1]
      } else if(leftValue > midValue) {
        return findMin(nums, left + 1, mid); // [4, 5, 1, 2, 3]
      } else { // left == mid
        if(midValue == rightValue)
          return Math.min(
            findMin(nums, left, mid - 1),  // [3, 1, 3, 3, 3]
            findMin(nums, mid + 1, right) // [3, 3, 3, 1, 3]
          );
        if(midValue < rightValue) return midValue;  // [3, 3, 3, 4, 5]
        return findMin(nums, mid + 1, right); // [3, 3, 3, 1, 2]
      }

    }

  }

  public static class Solution2 implements Solution, Iterative {
    @Override
    public int findMin(int[] nums) {
      int left = 0;
      int right = nums.length - 1;
      while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] > nums[right]) { // cut off is on the right
          left = mid + 1;
        }
        else if(nums[mid] < nums[right]) { // cut off is on the left (including mid)
          right = mid;
        }
        else { // when num[mid] and num[right] are same
          right--;
        }
      }
      return nums[left];
    }
  }

  public static class Solution3 implements Solution, Iterative {
    @Override
    public int findMin(int[] nums) {
      int left = 0;
      int right = nums.length - 1;
      while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] > nums[right]) { // cut off is on the right
          left = mid + 1;
        }
        else if(nums[mid] < nums[right]) { // cut off is on the left (including mid)
          right = mid;
        }
        else { // when num[mid] and num[right] are same
          if(nums[left] == nums[mid]) {
            left++;
            right--;
          } else if(nums[left] > nums[mid]) {
            left++;
            right = mid;
          } else {
            return nums[left];
          }
        }
      }
      return nums[left];
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[]{4, 5, 6, 7, 0, 1, 2};
      result = s.findMin(nums);
      System.out.println(result);
    }
  }

}
