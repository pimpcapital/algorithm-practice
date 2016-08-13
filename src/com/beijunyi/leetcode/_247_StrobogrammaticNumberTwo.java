package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 *   Given n = 2, return ["11","69","88","96"].
 *
 * Hint:
 *   Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */
public class _247_StrobogrammaticNumberTwo implements Medium, PremiumQuestion {

  public interface Solution {
    List<String> findStrobogrammatic(int n);
  }

  public static class Solution1 implements Solution {
    private static final int[] CENTER_CANDIDATES = new int[] {0, 1, 8};
    private static final int[] SIDE_CANDIDATES = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    @Override
    public List<String> findStrobogrammatic(int n) {
      List<String> ret = new ArrayList<>();
      findStrobogrammatic(new char[n], 0, ret);
      return ret;
    }

    private static void findStrobogrammatic(char[] cs, int index, List<String> result) {
      int rotated = cs.length - 1 - index;
      if(index == rotated) {
        for(int n : CENTER_CANDIDATES) {
          cs[index] = (char) ('0' + n);
          result.add(new String(cs));
        }
      } else if(index > rotated) {
        result.add(new String(cs));
      } else {
        for(int i = 0; i < SIDE_CANDIDATES.length; i++) {
          if(SIDE_CANDIDATES[i] < 0) continue;
          if(index == 0 && i == 0) continue; // first digit must not be zero
          cs[index] = (char) ('0' + i);
          cs[rotated] = (char) ('0' + SIDE_CANDIDATES[i]);
          findStrobogrammatic(cs, index + 1, result);
        }
      }
    }
  }

  public static void main(String args[]) {
    int n;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      n = 2;
      result = s.findStrobogrammatic(n);
      System.out.println(result);

      n = 3;
      result = s.findStrobogrammatic(n);
      System.out.println(result);

      n = 4;
      result = s.findStrobogrammatic(n);
      System.out.println(result);

      n = 5;
      result = s.findStrobogrammatic(n);
      System.out.println(result);
    }
  }

}
