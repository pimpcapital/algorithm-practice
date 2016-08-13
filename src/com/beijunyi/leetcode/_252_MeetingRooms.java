package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Comparator;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.Interval;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine
 * if a person could attend all meetings.
 *
 * For example:
 *   Given [[0, 30],[5, 10],[15, 20]],
 *   return false.
 */
public class _252_MeetingRooms implements Easy, PremiumQuestion {

  public interface Solution {
    boolean canAttendMeetings(Interval[] intervals);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean canAttendMeetings(Interval[] intervals) {
      Arrays.sort(intervals, new IntervalStartTimeComparator());
      for(int i = 1; i < intervals.length; i++) {
        if(intervals[i].start < intervals[i - 1].end) return false;
      }
      return true;
    }

    private static class IntervalStartTimeComparator implements Comparator<Interval> {
      @Override
      public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.start, o2.start);
      }
    }

  }

  public static void main(String args[]) {
    Interval[] intervals;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      intervals = new Interval[] {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
      result = s.canAttendMeetings(intervals);
      System.out.println(result);
    }
  }

}
