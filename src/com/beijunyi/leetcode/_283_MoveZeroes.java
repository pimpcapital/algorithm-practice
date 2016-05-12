package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 *  You must do this in-place without making a copy of the array.
 *  Minimize the total number of operations.
 */
public class _283_MoveZeroes implements Easy {

  public interface Solution {
    void moveZeroes(int[] nums);
  }

  public static class Solution1 implements Solution {

    @Override
    public void moveZeroes(int[] nums) {
      int zeroes = 0;
      int pos = 0;
      for(int num : nums) {
        if(num != 0) {
          nums[pos++] = num;
        } else {
          zeroes++;
        }
      }
      for(int i = nums.length - 1; i >= nums.length - zeroes; i--)
        nums[i] = 0;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public void moveZeroes(int[] nums) {
      int z = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
          swap(nums, i, z++);
        }
      }
    }
    private static void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }

  public static void main(String args[]) {
    int[] nums;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {0, 1, 0, 3, 12};
      s.moveZeroes(nums);
      System.out.println(Arrays.toString(nums));
    }
  }

}
