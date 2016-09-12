package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192
 * (represented in binary as 00111001011110000010100101000000).
 *
 * Follow up:
 * If this function is called many times, how would you optimize it?
 *
 * Related problem: Reverse Integer
 */
public class _190_ReverseBits implements Easy {

  public interface Solution {
    int reverseBits(int n);
  }

  public static class Solution1 implements Solution, BitManipulation {
    @Override
    public int reverseBits(int n) {
      int result = 0;
      for(int i = 0; i < 32; i++) {
        int mask = 1 << i;
        int mirrorMask = 1 << (31 - i);
        if((n & mask) != 0) result |= mirrorMask;
      }
      return result;
    }
  }

  public static class Solution2 implements Solution, BitManipulation {

    @Override
    public int reverseBits(int n) {
      int result = 0;
      int count = 31;
      while(n != 0) {
        result |= (n & 1);
        n = n >> 1;
        result = result << 1;
        count--;
      }
      return result << count;
    }

  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 964176192;
      result = s.reverseBits(n);
      System.out.println(result);
    }
  }

}
