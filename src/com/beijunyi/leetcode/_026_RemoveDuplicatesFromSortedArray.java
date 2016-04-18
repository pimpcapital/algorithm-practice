package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
 * length.
 *
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example,
 * Given input array nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't
 * matter what you leave beyond the new length.
 */
public class _026_RemoveDuplicatesFromSortedArray implements Easy {

  public interface Solution {
    int removeDuplicates(int[] nums);
  }

  public static class Solution1 implements Solution {

    @Override
    public int removeDuplicates(int[] nums) {
      Integer prev = null;
      int count = 0;
      for(int num : nums) {
        if(prev == null || num != prev) {
          nums[count] = num;
          count++;
          prev = num;
        }
      }
      return count;
    }
  }

  public static void main(String args[]) {
    int[] nums = new int[] {1, 1, 2};
    int r;

    Solution s = new Solution1();
    r = s.removeDuplicates(nums);

    int[] result = new int[r];
    System.arraycopy(nums, 0, result, 0, r);
    System.out.println(Arrays.toString(result));
  }

}
