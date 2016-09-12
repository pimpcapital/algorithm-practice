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
      int count;
      for(count = 0; count < 32; count++) { // in total 32 digits to process
        if(n == 0) break;
        result = result << 1;
        int mask = n & 1;
        n = n >>> 1;
        result = result | mask;
      }
      return result << (32 - count); // adding trailing zeros at the back
    }

  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 964176192;
      result = s.reverseBits(n);
      System.out.println(result);

      n = (int) 2147483648L; // 10000000000000000000000000000000;
      result = s.reverseBits(n);
      System.out.println(result);
    }
  }

}
