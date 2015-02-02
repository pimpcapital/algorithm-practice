package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumberTwo implements Medium {

  /**
   * Consider every bit in a number, if this bit appear 3 times then set it to zero.
   * so here is 3 state of one bit, we can use two bit to indicate it.
   * we use 00 -> 01 -> 10 -> 00...
   *
   * 00: bit 1 appear 0 time.
   * 01: bit 1 appear 1 time.
   * 10: bit 1 appear 2 times.
   *
   * so every bit 1 appear 3 times, the state will go to 00
   * but for the single number, every bit 1 in it, its status is 01
   * we can use two integer to represent the status, named first, second
   *
   * Now problem is how to generate the code to change 'first' and 'second'
   * I use truth table to generate the code, like this:
   * count every '1' bit, 3 times to zero
   * so we need 3 status, 2 bit is enough
   *
   * state: 00 -> 01 -> 10 -> 00....
   *
   * so we can draw a truth table of first bit
   *   A: first bit
   *   B: second bit
   *   C: input bit
   *
   *   A  B  C NA NB
   *   0  0  0  0  0
   *   0  0  1  0  1
   *   0  1  0  0  1
   *   0  1  1  1  0
   *   1  0  0  1  0
   *   1  0  1  0  0
   *   1  1  0  \  \
   *   1  1  1  \  \
   *
   * so
   *   NA = ~ABC + A~B~C
   *   NB = ~A~BC + ~AB~C = ~A(B^C)
   *   NB is first, NA is second, so we should use last first when we calc next second
   */
  public static class Solution {
    public int singleNumber(int[] A) {
      int ones = 0, twos = 0;
      for(int a : A) {
        ones = (ones ^ a) & ~twos;
        twos = (twos ^ a) & ~ones;
      }
      return ones;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().singleNumber(new int[]{3, 2, 1, 2, 2, 3, 3}));
  }


}
