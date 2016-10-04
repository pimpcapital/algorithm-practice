package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BucketSort;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note:
 *   You are not suppose to use the library's sort function for this problem.
 *
 * Follow up:
 *   A rather straight forward solution is a two-pass algorithm using counting sort.
 *   First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then
 *   1's and followed by 2's.
 *   Could you come up with an one-pass algorithm using only constant space?
 */
public class _075_SortColors implements Medium {

  public interface Solution {
    void sortColors(int[] nums);
  }

  public static class Solution1 implements Solution, BucketSort {
    @Override
    public void sortColors(int[] nums) {
      int[] buckets = new int[3];
      for(int num : nums) buckets[num]++;
      int i = 0;
      while(buckets[0]-- > 0) nums[i++] = 0;
      while(buckets[1]-- > 0) nums[i++] = 1;
      while(buckets[2]-- > 0) nums[i++] = 2;
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public void sortColors(int[] nums) {

      int l = 0; // anything before this point is 0
      int r = nums.length - 1; // anything after this point is 2
      int m = l;
      while(m <= r) {
        int n = nums[m];
        if(n == 0) {
          nums[m] = nums[l];
          nums[l] = 0;
          l++;
          m++;
        } else if(n == 2) {
          nums[m] = nums[r];
          nums[r] = 2;
          r--;
        } else {
          m++;
        }
      }

    }

  }

  public static void main(String args[]) {
    int[] nums;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {1, 0};
      s.sortColors(nums);
      System.out.println(Arrays.toString(nums));

      nums = new int[] {0, 1, 2, 2, 1, 0, 2, 1, 2, 1, 1, 0};
      s.sortColors(nums);
      System.out.println(Arrays.toString(nums));
    }

  }

}
