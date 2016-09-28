package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 */
public class _004_MedianOfTwoSortedArrays implements Hard {

  public interface Solution {
    double findMedianSortedArrays(int A[], int B[]);
  }

  /**
   * This is my iterative solution using binary search.
   * The main idea is to find the approximate location of the median and compare the elements around it to get the final result.
   *
   * do binary search. suppose the shorter list is A with length n.
   * the runtime is O(log(n)) which means no matter how large B array is, it only depends on the size of A.
   * It makes sense because if A has only one element while B has 100 elements, the median must be one of A[0], B[49], and B[50] without check everything else.
   * If A[0] <= B[49], B[49] is the answer; if B[49] < A[0] <= B[50], A[0] is the answer; else, B[50] is the answer.
   *
   * After binary search, we get the approximate location of median.
   * Now we just need to compare at most 4 elements to find the answer. This step is O(1).
   *
   * the same solution can be applied to find kth element of 2 sorted arrays.
   */
  public static class Solution1 implements Solution {

    @Override
    public double findMedianSortedArrays(int A[], int B[]) {
      // the following call is to make sure len(A) <= len(B).
      if (A.length > B.length) return findMedianSortedArrays(B, A);

      // now, do binary search
      int k = (A.length + B.length - 1) / 2;
      int left = 0, right = Math.min(k, A.length); // right is n, NOT n-1, this is important!!
      while (left < right) {
        int midA = (left + right) / 2;
        int midB = k - midA;
        if (A[midA] < B[midB])
          left = midA + 1;
        else
          right = midA;
      }

      // after binary search, we almost get the median because it must be between
      // these 4 numbers: A[l-1], A[l], B[k-l], and B[k-l+1]

      // if (n+m) is odd, the median is the larger one between A[l-1] and B[k-l].
      // and there are some corner cases we need to take care of.
      int a = Math.max(left > 0 ? A[left - 1] : Integer.MIN_VALUE, k - left >= 0 ? B[k - left] : Integer.MIN_VALUE);
      if (((A.length + B.length) & 1) == 1)
        return (double) a;

      // if (n+m) is even, the median can be calculated by
      //      median = (max(A[l-1], B[k-l]) + min(A[l], B[k-l+1]) / 2.0
      // also, there are some corner cases to take care of.
      int b = Math.min(left < A.length ? A[left] : Integer.MAX_VALUE, k - left + 1 < B.length ? B[k - left + 1] : Integer.MAX_VALUE);
      return (a + b) / 2.0;
    }

  }


  public static void main(String args[]) {
    int[] A;
    int[] B;
    double result;

    for(Solution s : Arrays.asList(new Solution1())) {
      A = new int[]{2, 7, 9};
      B = new int[]{2, 3, 4};
      result = s.findMedianSortedArrays(A, B);
      System.out.println(result);

      A = new int[]{1, 2, 4, 6, 7, 10, 13};
      B = new int[]{2, 3, 4, 6, 8, 9};
      result = s.findMedianSortedArrays(A, B);
      System.out.println(result);
    }
  }


}
