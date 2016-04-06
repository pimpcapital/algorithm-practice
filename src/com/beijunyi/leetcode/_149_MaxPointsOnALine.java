package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.Point;

public class _149_MaxPointsOnALine implements Hard {

  public static class Solution {
    public int maxPoints(Point[] points) {
      int maxCount = 0;
      for(int i = 0; i < points.length; i++) {
        Map<Line, Integer> counts = new HashMap<>();
        Point p1 = points[i];
        int repeat = 1;
        for(int j = i + 1; j < points.length; j++) {
          Point p2 = points[j];
          if(p2.x == p1.x && p2.y == p1.y) {
            repeat++;
            continue;
          }
          Line l = createLine(p1, p2);
          int n = counts.containsKey(l) ? (counts.get(l) + 1) : 1;
          counts.put(l, n);
        }

        if(counts.isEmpty())
          maxCount = Math.max(maxCount, repeat);
        else {
          for(Integer count : counts.values())
            maxCount = Math.max(maxCount, count + repeat);
        }
      }
      return maxCount;
    }

    private static int gcd(int a, int b) {
      if (b == 0)
        return a;
      return gcd(b, a % b);
    }

    private static int gcdN(int... nums) {
      int current = nums[0];
      for(int i = 1; i < nums.length; i++) {
        int n = nums[i];
        current = gcd(current, n);
      }
      return current;
    }

    private Line createLine(Point p1, Point p2) {
      int a = p2.y - p1.y;
      int b = p1.x - p2.x;
      int c = p1.x * p2.y - p2.x * p1.y;
      if(a < 0) {
        a = -a;
        b = -b;
        c = -c;
      } else if(a == 0) {
        if(b < 0) {
          b = -b;
          c = -c;
        }
      }
      int gcd = gcdN(a, b, c);
      a /= gcd;
      b /= gcd;
      c /= gcd;
      return new Line(a, b, c);
    }

    private static class Line {
      private int[] coefficients;

      private Line(int a, int b, int c) {
        coefficients = new int[]{a, b, c};
      }

      public int hashCode() {
        return Arrays.hashCode(coefficients);
      }

      @Override
      public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Line line = (Line)o;
        return Arrays.equals(coefficients, line.coefficients);
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().maxPoints(new Point[] {new Point(3, 10), new Point(0, 2), new Point(0, 2), new Point(3, 10)}));
    System.out.println(new Solution().maxPoints(new Point[] {new Point(0, 0), new Point(-1, -1), new Point(2, 2)}));
  }

}
