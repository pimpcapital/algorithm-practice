package com.beijunyi.leetcode;

/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class SqrtX {

  /**
   * I calculate sqrt(x) by deciding every bit from the most significant to least significant.
   * Since an integer n can have O(log n) bits with each bit decided within constant time, this algorithm has time limit O(log n).
   * Actually, because an Integer can have at most 32 bits, I can also say this algorithm takes O(32)=O(1) time.
   */
  public static class Solution {
    public int sqrt(int x) {
      int ans = 0;
      int bit = 1 << 16;
      while(bit > 0) {
        ans |= bit;
        if ((long) ans * ans > x)
          ans ^= bit;
        bit >>= 1;
      }
      return ans;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().sqrt(0));
  }
}
