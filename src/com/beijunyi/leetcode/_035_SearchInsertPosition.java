package com.beijunyi.leetcode;

public class _035_SearchInsertPosition {
  public static class Solution {
    public int searchInsert(int[] A, int target) {
      return searchLastLessThanOrEqualElement(A, target, 0, A.length);
    }

    public int searchLastLessThanOrEqualElement(int[] A, int target, int start, int end) {
      if(end - start == 0)
        return start;
      int mid = (start + end) / 2;
      int midValue = A[mid];
      if(midValue == target)
        return mid;
      if(midValue < target)
        return searchLastLessThanOrEqualElement(A, target, mid + 1, end);
      else
        return searchLastLessThanOrEqualElement(A, target, start, mid);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().searchInsert(new int[] {1, 3, 5, 6}, 7));
  }
}
