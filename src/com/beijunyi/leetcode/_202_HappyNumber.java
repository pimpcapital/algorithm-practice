package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Write an algorithm to determine if a number is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number
 * by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it
 * loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy
 * numbers.
 *
 * Example: 19 is a happy number
 *   12 + 92 = 82
 *   82 + 22 = 68
 *   62 + 82 = 100
 *   12 + 02 + 02 = 1
 */
public class _202_HappyNumber implements Easy {

  public interface Solution {
    boolean isHappy(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public boolean isHappy(int n) {
      Set<Integer> tested = new HashSet<>();
      while(tested.add(n)) {
        if(n == 1) return true;
        int next = 0;
        while(n > 0) {
          int digit = n % 10;
          next += digit * digit;
          n /= 10;
        }
        n = next;
      }
      return false;
    }
  }

  public static void main(String args[]) {
    int n;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 19;
      result = s.isHappy(n);
      System.out.println(result);

      n = 2;
      result = s.isHappy(n);
      System.out.println(result);
    }
  }

}
