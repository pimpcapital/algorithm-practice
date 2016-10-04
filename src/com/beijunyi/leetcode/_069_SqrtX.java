package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class _069_SqrtX implements Medium {

  public interface Solution {
    int sqrt(int x);
  }

  public static class Solution1 implements Solution, BinarySearch {
    @Override
    public int sqrt(int x) {
      if(x == 0) return 0;
      int left = 1, right = x;
      while(true) {
        int mid = left + (right - left) / 2;
        if(mid > x / mid) {
          right = mid - 1;
        } else {
          if(mid + 1 > x / (mid + 1))
            return mid;
          left = mid + 1;
        }
      }
    }
  }

  /**
   * I calculate sqrt(x) by deciding every bit from the most significant to least significant.
   * Since an integer n can have O(log n) bits with each bit decided within constant time, this algorithm has time limit O(log n).
   * Actually, because an Integer can have at most 32 bits, I can also say this algorithm takes O(32)=O(1) time.
   */
  public static class Solution2 implements Solution, BitManipulation {
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

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      System.out.println(s.sqrt(0));
      System.out.println(s.sqrt(16));
      System.out.println(s.sqrt(10000));

      System.out.println();
    }
  }
}
