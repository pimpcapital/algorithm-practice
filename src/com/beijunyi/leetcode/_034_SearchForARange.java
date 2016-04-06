package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class _034_SearchForARange implements Medium {

  public static class Solution2 {
    public int[] searchRange(int[] A, int target) {
      return new int[]{searchStart(A, target, 0, A.length - 1), searchEnd(A, target, 0, A.length - 1)};
    }

    public int searchStart(int[] A, int target, int left, int right) {
      if(right - left < 0)
        return -1;
      int mid = (left + right) / 2;
      int midValue = A[mid];
      if(midValue >= target) {
        int startIndex = searchStart(A, target, left, mid - 1);
        if(midValue == target && startIndex == -1)
          return mid;
        else
          return startIndex;
      } else {
        return searchStart(A, target, mid + 1, right);
      }
    }

    public int searchEnd(int[] A, int target, int left, int right) {
      if(right - left < 0)
        return -1;
      int mid = (left + right) / 2;
      int midValue = A[mid];
      if(midValue <= target) {
        int endIndex = searchEnd(A, target, mid + 1, right);
        if(midValue == target && endIndex == -1)
          return mid;
        else
          return endIndex;
      } else {
        return searchEnd(A, target, left, mid - 1);
      }
    }
  }

  public static class Solution1 {
    public int[] searchRange(int[] A, int target) {
      int start = firstGreaterEqual(A, target);
      if (start == A.length || A[start] != target) {
        return new int[]{-1, -1};
      }
      return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
    }

    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    private static int firstGreaterEqual(int[] A, int target) {
      int low = 0, high = A.length;
      while (low < high) {
        int mid = low + ((high - low) >> 1);
        //low <= mid < high
        if (A[mid] < target) {
          low = mid + 1;
        } else {
          //should not be mid-1 when A[mid]==target.
          //could be mid even if A[mid]>target because mid<high.
          high = mid;
        }
      }
      return low;
    }
  }

  public static void main(String args[]) {
    System.out.println(Arrays.toString(new Solution2().searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8)));
    System.out.println(Arrays.toString(new Solution1().searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8)));
  }
}
