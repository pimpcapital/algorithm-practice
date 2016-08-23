package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Numbers can be regarded as product of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 *   You may assume that n is always positive.
 *   Factors should be greater than 1 and less than n.
 *
 * Examples:
 *   input: 1
 *   output:
 *     []
 *
 *   input: 37
 *   output:
 *     []
 *
 *   input: 12
 *   output:
 *     [
 *       [2, 6],
 *       [2, 2, 3],
 *       [3, 4]
 *     ]
 *
 *   input: 32
 *   output:
 *     [
 *       [2, 16],
 *       [2, 2, 8],
 *       [2, 2, 2, 4],
 *       [2, 2, 2, 2, 2],
 *       [2, 4, 4],
 *       [4, 8]
 *     ]
 */
public class _254_FactorCombinations implements Medium, PremiumQuestion {

  public interface Solution {
    List<List<Integer>> getFactors(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<List<Integer>> getFactors(int n) {
      List<List<Integer>> ret = new ArrayList<>();
      addFactors(n, 2, ret);
      return ret;
    }

    private static void addFactors(int n, int base, List<List<Integer>> result) {
      for(int i = base; i * i <= n; i++) {
        if(n % i == 0) {
          int updated = result.size();
          addFactors(n / i, i, result);
          List<Integer> factors = new ArrayList<>();
          result.add(factors);
          factors.add(n / i);
          while(updated < result.size()) {
            result.get(updated++).add(i);
          }
        }
      }
    }
  }

  public static void main(String args[]) {
    int n;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 12;
      result = s.getFactors(n);
      for(List<Integer> factors : result) {
        System.out.println(factors);
      }

      n = 32;
      result = s.getFactors(n);
      for(List<Integer> factors : result) {
        System.out.println(factors);
      }

      n = 65535;
      result = s.getFactors(n);
      for(List<Integer> factors : result) {
        System.out.println(factors);
      }
    }
  }

}
