package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.Interval;

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

  public interface Solution {
    List<Interval> insert(List<Interval> intervals, Interval newInterval);
  }

  /**
   * Idea is to find the lower and upper bound of the newly inserted interval.
   * If lower boundary equals to higher boundary, just insert to that position.
   *
   * Otherwise, compare the values of old intervals and new interval to determine the values of start and end for the
   * interval after insertion.
   */
  public static class Solution1 implements Solution {

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

  public static class Solution2 implements Solution {
    @Override
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
      Iterator<Interval> iterator = intervals.iterator();
      boolean merged = false;
      while(iterator.hasNext()) {
        Interval next = iterator.next();
        if(!merged && Math.max(newInterval.start, next.start) <= Math.min(newInterval.end, next.end)
             || merged && newInterval.end >= next.start) {
          newInterval.start = Math.min(next.start, newInterval.start);
          newInterval.end = Math.max(next.end, newInterval.end);
          merged = true;
          iterator.remove();
        } else if(merged || next.start > newInterval.end) break; // no chance.
      }

      int insert = 0;
      for(int i = 0; i < intervals.size(); i++) {
        Interval current = intervals.get(i);
        if(current.end < newInterval.start) insert = i + 1;
        else if(current.start > newInterval.end) break;
      }
      intervals.add(insert, newInterval);
      return intervals;
    }

  }

  public static void main(String args[]) {
    List<Interval> intervals;
    Interval newInterval;
    List<Interval> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      intervals = new ArrayList<>();
      newInterval = new Interval(6, 8);
      result = s.insert(intervals, newInterval);
      System.out.println(result);

      intervals = new ArrayList<>(Arrays.asList(new Interval(1, 5)));
      newInterval = new Interval(6, 8);
      result = s.insert(intervals, newInterval);
      System.out.println(result);

      intervals = new ArrayList<>(Arrays.asList(new Interval(1, 5)));
      newInterval = new Interval(0, 0);
      result = s.insert(intervals, newInterval);
      System.out.println(result);

      intervals = new ArrayList<>(Arrays.asList(
        new Interval(1, 2), new Interval(3, 5), new Interval(6, 7), new Interval(8, 10), new Interval(12, 16)));
      newInterval = new Interval(4, 9);
      result = s.insert(intervals, newInterval);
      System.out.println(result);
    }
  }
}
