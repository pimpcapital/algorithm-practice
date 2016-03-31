package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * For example:
 * Given array A = [2,3,1,1,4]
 *
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class _045_JumpGameTwo implements Hard {

  /**
   * We use "furthest" to keep track of the maximum distance that has been reached
   * by using the minimum steps "steps", whereas "next" is the maximum distance
   * that can be reached by using "steps+1" steps. Thus,
   * next = max(i+A[i]) where 0 <= i <= last.
   */
  public static class Solution {
    public int jump(int A[]) {
      int steps = 0;
      int furthest = 0;
      int next = 0;
      for(int i = 0; i < A.length; ++i) {
        if(i > furthest) {
          furthest = next;
          ++steps;
        }
        next = Math.max(next, i + A[i]);
      }
      return steps;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().jump(new int[]{2,3,1,1,4}));
  }
}
