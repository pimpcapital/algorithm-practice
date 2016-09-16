package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.PureMath;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 */
public class _204_CountPrimes implements Easy, NotHardButTricky {

  public interface Solution {
    int countPrimes(int n);
  }

  /**
   * Sieve of Eratosthenes T: O(NloglogN)
   */
  public static class Solution1 implements Solution, PureMath {
    @Override
    public int countPrimes(int n) {
      boolean[] notPrime = new boolean[n];
      int count = 0;
      for(int i = 2; i < n; i++) {
        if(!notPrime[i]) {
          count++;
          for(int j = 2; i*j < n; j++) {
            notPrime[i*j] = true;
          }
        }
      }

      return count;
    }
  }

  public static void main(String args[]) {
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 499979;
      result = s.countPrimes(n);
      System.out.println(result);
    }
  }

}
