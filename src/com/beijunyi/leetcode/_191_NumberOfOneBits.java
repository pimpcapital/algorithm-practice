package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming
 * weight).
 *
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function
 * should return 3.
 */
public class _191_NumberOfOneBits implements Easy {

  public interface Solution {
    int hammingWeight(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public int hammingWeight(int n) {
      int count = 0;
      while(n != 0) {
        if((n & 1) == 1) count++;
        n = n >>> 1;
      }
      return count;
    }
  }

  public static void main(String args[]) {
    int n;
    int result;
    for(Solution s : Arrays.asList(new Solution1())) {
      n = -1;
      result = s.hammingWeight(n);
      System.out.println(result);
    }
  }

}
