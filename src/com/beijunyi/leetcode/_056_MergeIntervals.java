package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class _056_MergeIntervals implements Hard {

  private static class Interval {
    int start;
    int end;
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
      return "[" + start + ", " + end + ']';
    }
  }

  public static class Solution {
    public List<Interval> merge(List<Interval> intervals) {
      Collections.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval i1, Interval i2) {
          return Integer.compare(i1.start, i2.start);
        }
      });

      List<Interval> result = new ArrayList<>();
      for(Interval i : intervals) {
        int size = result.size();
        if(size != 0) {
          Interval prev = result.get(size - 1);
          if(i.start <= prev.end)
            prev.end = Math.max(prev.end, i.end);
          else
            result.add(i);
        } else
          result.add(i);
      }
      return result;
    }
  }

  public static void main(String args[]) {
    List<Interval> input = new ArrayList<>();
    input.add(new Interval(1, 3));
    input.add(new Interval(2, 6));
    input.add(new Interval(8, 10));
    input.add(new Interval(15, 18));
    System.out.println(new Solution().merge(input));
  }


}
