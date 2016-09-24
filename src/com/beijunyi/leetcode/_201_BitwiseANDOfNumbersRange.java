package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 *
 * For example, given the range [5, 7], you should return 4.
 */
public class _201_BitwiseANDOfNumbersRange implements Medium {

  public interface Solution {
    int rangeBitwiseAnd(int m, int n);
  }

  public static class Solution1 implements Solution, BitManipulation {
    @Override
    public int rangeBitwiseAnd(int m, int n) {
      if(m == 0) return 0;
      int mask = n;
      while(m > 0 && m < n) {
        mask &= m;
        if(mask == 0) break;
        m += m & -m;
      }
      return mask;
    }
  }

  public static class Solution2 implements Solution, BitManipulation {
    @Override
    public int rangeBitwiseAnd(int m, int n) {
      while(n > m) n -= n & -n;
      return n;
    }
  }

  /**
   * The idea is very simple:
   *
   * last bit of (odd number & even number) is 0.
   * when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
   * Move m and n right a position.
   * Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.
   */
  public static class Solution3 implements Solution, BitManipulation {
    @Override
    public int rangeBitwiseAnd(int m, int n) {
      if(m == 0){
        return 0;
      }
      int moveFactor = 1;
      while(m != n){
        m >>= 1;
        n >>= 1;
        moveFactor <<= 1;
      }
      return m * moveFactor;
    }
  }

  public static void main(String args[]) {
    int m;
    int n;
    int result;
    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      m = 20000;
      n = 2147483647;
      result = s.rangeBitwiseAnd(m, n);
      System.out.println(result);

      m = 2147483646;
      n = 2147483647;
      result = s.rangeBitwiseAnd(m, n);
      System.out.println(result);
    }
  }

}
