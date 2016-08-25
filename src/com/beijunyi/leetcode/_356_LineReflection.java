package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 *
 * Example 1:
 *   Given points = [[1,1],[-1,1]], return true.
 *
 * Example 2:
 *   Given points = [[1,1],[-1,-1]], return false.
 *
 * Follow up:
 *   Could you do better than O(n2)?
 *
 * Hint:
 *   1) Find the smallest and largest x-value for all points.
 *   2) If there is a line then it should be at y = (minX + maxX) / 2.
 *   3) For each point, make sure that it has a reflected point in the opposite side.
 */
public class _356_LineReflection implements Medium, PremiumQuestion {

  public interface Solution {
    boolean isReflected(int[][] points);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean isReflected(int[][] points) {
      if(points.length < 2) return true;
      int left = Integer.MAX_VALUE;
      int right = Integer.MIN_VALUE;
      Map<Integer, Set<Integer>> xyLookup = new HashMap<>(); // <X, Set<Y with same X>>
      for(int[] point : points) {
        left = Math.min(point[0], left);
        right = Math.max(point[0], right);
        Set<Integer> ys = xyLookup.get(point[0]);
        if(ys == null) {
          ys = new HashSet<>();
          xyLookup.put(point[0], ys);
        }
        ys.add(point[1]);
      }

      double centerX = ((double) left) / 2 + ((double) right) / 2;
      for(int[] point: points) {
        double mirroredValue = centerX * 2 - point[0];
        int mirroredX = (int) mirroredValue;
        if(mirroredX < mirroredValue) return false;
        if(!xyLookup.containsKey(mirroredX) || !xyLookup.get(mirroredX).contains(point[1])) return false;
      }
      return true;
    }

  }

  public static void main(String args[]) {
    int[][] points;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      points = new int[][] {
        {0, 0}, {1, 0}
      };
      result = s.isReflected(points);
      System.out.println(result);
    }
  }

}
