package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class _057_InsertInterval implements Hard {

  private static class Interval {
     int start;
     int end;
     Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
      return "[" + start + ", " + end + ']';
    }
  }

  /**
   * Idea is to find the lower and upper bound of the newly inserted interval.
   * If lower boundary equals to higher boundary, just insert to that position.
   * Otherwise, compare the values of old intervals and new interval to determine the values of start and end for the interval after insertion.
   */
  public static class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
      List<Interval> results = new ArrayList<>();
      if(intervals == null || intervals.size() == 0) {
        results.add(newInterval);
        return results;
      }

      int start = 0, end = 0;

      for(Interval i : intervals) {
        if(newInterval.start > i.end) start++;
        if(newInterval.end >= i.start) end++;
        else break;
      }

      if(start == end) {
        results.addAll(intervals);
        results.add(start, newInterval);
        return results;
      }

      for(int i = 0; i < start; i++)
        results.add(intervals.get(i));
      Interval interval = new Interval(Math.min(newInterval.start, intervals.get(start).start), Math.max(newInterval.end, intervals.get(end - 1).end));
      results.add(interval);
      for(int i = end; i < intervals.size(); i++)
        results.add(intervals.get(i));
      return results;
    }
  }

  public static void main(String args[]) {
    List<Interval> input = new ArrayList<>();
    input.add(new Interval(1, 2));
    input.add(new Interval(3, 5));
    input.add(new Interval(6, 7));
    input.add(new Interval(8, 10));
    input.add(new Interval(12, 16));
    System.out.println(new Solution().insert(input, new Interval(4, 9)));
  }
}
