package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class _042_TrappingRainWater implements Hard {

  public interface Solution {
    int trap(int[] A);
  }

  public static class Solution1 implements Solution {
    public int trap(int[] A) {
      int left = 0;
      int right = A.length - 1;
      int count = 0;
      int leftMax = 0;
      int rightMax = 0;
      while(left <= right) {
        leftMax = Math.max(leftMax, A[left]);
        rightMax = Math.max(rightMax, A[right]);
        if(leftMax < rightMax) {
          count += (leftMax - A[left]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
          left++;
        } else {
          count += (rightMax - A[right]);
          right--;
        }
      }
      return count;
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public int trap(int[] A) {
      int left = 0;
      int right = A.length - 1;
      int tallest = 0;
      int count = 0;
      while(left <= right) {
        if(A[left] <= A[right]) {
          tallest = Math.max(tallest, A[left]);
          count += tallest - A[left++];
        } else {
          tallest = Math.max(tallest, A[right]);
          count += tallest - A[right--];
        }
      }
      return count;
    }

  }

  public static void main(String args[]) {
    int[] A;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      A = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
      result = s.trap(A);
      System.out.println(result);
    }

  }

}
