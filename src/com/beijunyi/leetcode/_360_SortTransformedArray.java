package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form
 *   f(x) = ax^2 + bx + c
 * to each element x in the array.
 *
 * The returned array must be in sorted order.
 *
 * Expected time complexity: O(n)
 *
 * Example:
 *   nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 *   Result: [3, 9, 15, 33]
 *
 *   nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 *   Result: [-23, -5, 1, 7]
 */
public class _360_SortTransformedArray implements Medium, PremiumQuestion {

  public interface Solution {
    int[] sortTransformedArray(int[] nums, int a, int b, int c);
  }

  public static class Solution1 implements Solution {

    @Override
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
      if(a == 0) return applyQuadratic(nums, 0, nums.length, a, b, c, b < 0);
      int center = findCenterIndex(nums, a, b);
      int[] left = applyQuadratic(nums, 0, center, a, b, c, a > 0);
      int[] right = applyQuadratic(nums, center, nums.length - center, a, b, c, a < 0);
      return combine(left, right);
    }

    private static int[] applyQuadratic(int[] nums, int offset, int length, int a, int b, int c, boolean reverse) {
      int[] ret = new int[length];
      for(int i = 0; i < length; i++) {
        int num = nums[offset + i];
        int index = reverse ? length - 1 - i : i;
        ret[index] = applyQuadratic(num, a, b, c);
      }
      return ret;
    }

    private static int applyQuadratic(int num, int a, int b, int c) {
      return num * num * a + num * b + c;
    }

    private static int findCenterIndex(int[] nums, int a, int b) {
      int mid = -b / (2 * a);
      int split = Arrays.binarySearch(nums, mid);
      if(split < 0) {
        split = -(split + 1);
      }
      return split;
    }

    private static int[] combine(int[] nums1, int[] nums2) {
      int[] ret = new int[nums1.length + nums2.length];
      int i1 = 0;
      int i2 = 0;
      while(i1 < nums1.length && i2 < nums2.length) {
        int num1 = nums1[i1];
        int num2 = nums2[i2];
        if(num1 < num2) {
          ret[i1 + i2] = num1;
          i1++;
        } else {
          ret[i1 + i2] = num2;
          i2++;
        }
      }
      System.arraycopy(nums1, i1, ret, i1 + i2, nums1.length - i1);
      System.arraycopy(nums2, i2, ret, nums1.length + i2, nums2.length - i2);
      return ret;
    }
  }

  public static class Solution2 implements Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
      int n = nums.length;
      int[] sorted = new int[n];
      int i = 0, j = n - 1;
      int index = a >= 0 ? n - 1 : 0;
      while (i <= j) {
        if (a >= 0) {
          sorted[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
        } else {
          sorted[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c) ? quad(nums[j--], a, b, c) : quad(nums[i++], a, b, c);
        }
      }
      return sorted;
    }

    private int quad(int x, int a, int b, int c) {
      return a * x * x + b * x + c;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int a;
    int b;
    int c;
    int[] result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {-4, -2, 2, 4};
      a = 1;
      b = 3;
      c = 5;
      result = s.sortTransformedArray(nums, a, b, c);
      System.out.println(Arrays.toString(result));

      nums = new int[] {-4, -2, 2, 4};
      a = -1;
      b = 3;
      c = 5;
      result = s.sortTransformedArray(nums, a, b, c);
      System.out.println(Arrays.toString(result));

      System.out.println();
    }

  }

}
