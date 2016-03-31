package com.beijunyi.leetcode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */
public class _033_SearchInRotatedSortedArray {
  public static class Solution {
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

  public static void main(String args[]) {
    System.out.println(new Solution().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 5));
  }
}
