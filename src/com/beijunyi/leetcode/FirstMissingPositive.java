package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive implements Hard {

  public static class Solution {
    public int firstMissingPositive(int[] A) {
      boolean[] cache = new boolean[A.length];
      for(int a : A) {
        if(a < 1)
          continue;
        if(a - 1 >= A.length)
          continue;
        cache[a - 1] = true;
      }
      for(int i = 0; i < A.length; i++) {
        if(!cache[i])
          return i + 1;
      }
      return cache.length + 1;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().firstMissingPositive(new int[]{3,4,-1,1}));
  }

}
