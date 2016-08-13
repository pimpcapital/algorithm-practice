package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.Interval;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 *
 * For example:
 *   Given [[0, 30],[5, 10],[15, 20]],
 *   return 2.
 */
public class _253_MeetingRoomsTwo implements Medium, PremiumQuestion {

  public interface Solution {
    int minMeetingRooms(Interval[] intervals);
  }

  public static class Solution1 implements Solution {

    @Override
    public int minMeetingRooms(Interval[] intervals) {
      int[] starts = new int[intervals.length];
      int[] ends = new int[intervals.length];
      for(int i = 0; i < intervals.length; i++) {
        starts[i] = intervals[i].start;
        ends[i] = intervals[i].end;
      }
      Arrays.sort(starts);
      Arrays.sort(ends);
      int room = 0;
      for(int i = 0, j = 0; i < intervals.length; i++) {
        if(starts[i] >= ends[j]) j++; // if it starts after one ends
        else room++; // otherwise, we need another room
      }
      return room; // or interval.length - j
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public int minMeetingRooms(Interval[] intervals) {
      int[][] times = new int[intervals.length * 2][2];
      for(int i = 0; i < intervals.length; i++) {
        Interval interval = intervals[i];
        times[i * 2] = new int[] {interval.start, 1}; // acquire
        times[i * 2 + 1] = new int[] {interval.end, 0}; // release
      }
      Arrays.sort(times, new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
          int result = Integer.compare(o1[0], o2[0]);
          return result == 0 ? Integer.compare(o1[1], o2[1]) : result; // if times are the same, put release first
        }
      });
      int ongoing = 0; // keeping track of the concurrent meetings
      int max = 0;
      for(int[] time : times) {
        if(time[1] == 0) ongoing--; // it is a release
        else max = Math.max(max, ++ongoing); // it is an acquire
      }
      return max;
    }
  }

  public static class Solution3 implements Solution {

    @Override
    public int minMeetingRooms(Interval[] intervals) {
      Map<Integer, Integer> times = new TreeMap<>();
      for(Interval interval : intervals) {
        if(times.containsKey(interval.start)) times.put(interval.start, times.get(interval.start) + 1);
        else times.put(interval.start, 1);
        if(times.containsKey(interval.end)) times.put(interval.end, times.get(interval.end) - 1);
        else times.put(interval.end, -1);
      }
      int ongoing = 0; // keeping track of the concurrent meetings
      int max = 0;
      for(Integer count : times.values()) {
        max = Math.max(max, ongoing += count);
      }
      return max;
    }

  }

  public static void main(String args[]) {
    Interval[] intervals;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      intervals = new Interval[] {new Interval(9, 10), new Interval(4, 9), new Interval(4, 17)};
      result = s.minMeetingRooms(intervals);
      System.out.println(result);

      intervals = new Interval[] {new Interval(1, 8), new Interval(4, 6)};
      result = s.minMeetingRooms(intervals);
      System.out.println(result);

      intervals = new Interval[] {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
      result = s.minMeetingRooms(intervals);
      System.out.println(result);

      intervals = new Interval[] {new Interval(2, 15), new Interval(36, 45), new Interval(9, 29), new Interval(16, 23), new Interval(4, 9)};
      result = s.minMeetingRooms(intervals);
      System.out.println(result);

      System.out.println();
    }
  }

}
