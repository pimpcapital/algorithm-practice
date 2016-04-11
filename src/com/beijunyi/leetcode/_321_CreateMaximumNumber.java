package com.beijunyi.leetcode;

import java.util.Arrays;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length
 * k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an
 * array of the k digits. You should try to optimize your time and space complexity.
 *
 * Example 1:
 *   nums1 = [3, 4, 6, 5]
 *   nums2 = [9, 1, 2, 5, 8, 3]
 *   k = 5
 *   return [9, 8, 6, 5, 3]
 *
 * Example 2:
 *   nums1 = [6, 7]
 *   nums2 = [6, 0, 4]
 *   k = 5
 *   return [6, 7, 6, 0, 4]
 *
 * Example 3:
 *   nums1 = [3, 9]
 *   nums2 = [8, 9]
 *   k = 3
 *   return [9, 8, 9]
 **/
public class _321_CreateMaximumNumber {

  public interface Solution {
    int[] maxNumber(int[] nums1, int[] nums2, int k);
  }

  public static class Solution1 implements Solution {

    /**
     * Time: O((m+n)*k^2), Space: O(k) where:
     *   m is the length of nums1
     *   n is the length of nums2
     *
     * Create the biggest number from array 1 with i digits. Create the biggest number from array 2 with (k - i) digits.
     * Merge the digits of the 2 numbers in a way that the result digits forms the biggest number.
     *
     * Try this with all possible values of i.
     * (i > k - length of array 2, i < k)
     */
    @Override
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
      int[] max = new int[k];
      for(int i = Math.max(0, k - nums2.length); i <= Math.min(k, nums1.length); i++) { // i = digits from array 1
        int[] number = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
        if(greater(number, 0, max, 0)) max = number;
      }
      return max;
    }

    // time O((m+n)*k), space O(k)
    private static int[] merge(int[] nums1, int[] nums2, int k) {
      int[] ret = new int[k];
      for(int i = 0, j = 0, pos = 0; pos < k; pos++)
        ret[pos] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
      return ret;
    }

    // compares the tails of the 2 arrays
    // time O(m+n), space O(1)
    // (note: this could potentially be improved by DP caching the comparison results)
    private static boolean greater(int[] nums1, int i, int[] nums2, int j) {
      while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
        i++;
        j++;
      }
      return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    // find the biggest sequence of size k
    // time O(n), space O(k)
    private static int[] maxArray(int[] nums, int k) {
      int n = nums.length;
      int[] ret = new int[k];
      for(int i = 0, j = 0; i < n; i++) {
        while(n - i + j > k // (n - i) = number of digits remained to process, j = writing position
                && j > 0
                && ret[j - 1] < nums[i]) // if the new number is bigger than the last written digit
          j--; // go back to the last digit (so we can rewrite the last digit)
        if(j < k)
          ret[j++] = nums[i];
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    int[] nums1;
    int[] nums2;
    int k;
    int[] r;

    Solution s1 = new Solution1();

    nums1 = new int[] {3, 4, 6, 5};
    nums2 = new int[] {9, 1, 2, 5, 8, 3};
    k = 5;
    r = s1.maxNumber(nums1, nums2, k);
    System.out.println(Arrays.toString(r));

    nums1 = new int[] {6, 7};
    nums2 = new int[] {6, 0, 4};
    k = 5;
    r = s1.maxNumber(nums1, nums2, k);
    System.out.println(Arrays.toString(r));

  }

}
