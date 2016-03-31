package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.difficulty.Easy;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 */
public class _088_MergeSortedArray implements Easy {

  public static class Solution {
    public void merge(int A[], int m, int B[], int n) {
      int i = m - 1;
      int j = n - 1;
      int k = m + n - 1;
      while(i >= 0 && j >= 0) {
        if(A[i] > B[j])
          A[k--] = A[i--];
        else
          A[k--] = B[j--];
      }
      while(j>=0)
        A[k--] = B[j--];
    }
  }

  public static void main(String args[]) {
    int[] a = new int[] {1, 3, 0, 0};
    new Solution().merge(a, 2, new int[] {2, 4}, 2);
    System.out.println(Arrays.toString(a));
  }

}
