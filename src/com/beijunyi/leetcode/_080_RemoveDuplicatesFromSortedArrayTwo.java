package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 *
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't
 * matter what you leave beyond the new length.
 */
public class _080_RemoveDuplicatesFromSortedArrayTwo implements Medium {

  public interface Solution {
    int removeDuplicates(int[] nums);
  }

  public static class Solution1 implements Solution {
    @Override
    public int removeDuplicates(int[] nums) {
      Integer prev = null;
      Integer prevCount = null;
      int count = 0;
      for(int num : nums) {
        if(prev == null || num != prev) {
          nums[count++] = num;
          prev = num;
          prevCount = 1;
        } else if(prevCount++ < 2) {
          nums[count++] = num;
        }
      }
      return count;
    }
  }

  public static void main(String args[]) {
    int[] nums = new int[] {1,1,1,2,2,3};
    int r;

    Solution s = new Solution1();
    r = s.removeDuplicates(nums);

    int[] result = new int[r];
    System.arraycopy(nums, 0, result, 0, r);
    System.out.println(Arrays.toString(result));
  }

}
