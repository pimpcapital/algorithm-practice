package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.PureMath;

/**
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You
 * need to determine whether it is possible to measure exactly z litres using these two jugs.
 *
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 *
 * Example 1: (From the famous "Die Hard" example)
 *   Input: x = 3, y = 5, z = 4
 *   Output: True
 *
 * Example 2:
 *   Input: x = 2, y = 6, z = 5
 *   Output: False
 */
public class _365_WaterAndJugProblem implements Medium, Important {

  public interface Solution {
    boolean canMeasureWater(int x, int y, int z);
  }

  public static class Solution1 implements Solution, PureMath {
    @Override
    public boolean canMeasureWater(int x, int y, int z) {
      if(x + y < z) return false;
      int gcd = gcd(x, y);
      return z == 0 || gcd != 0 && z % gcd == 0;
    }

    private static int gcd(int a, int b) {
      if(b == 0) return a;
      return gcd(b, a % b);
    }
  }

  public static void main(String args[]) {
    int x;
    int y;
    int z;
    boolean result;
    for(Solution s : Arrays.asList(new Solution1())) {
      x = 3;
      y = 5;
      z = 4;
      result = s.canMeasureWater(x, y, z);
      System.out.println(result);

      x = 1;
      y = 1;
      z = 12;
      result = s.canMeasureWater(x, y, z);
      System.out.println(result);
    }
  }

}
