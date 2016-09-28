package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.Interval;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class _056_MergeIntervals implements Hard {

  public interface Solution {
    List<Interval> merge(List<Interval> intervals);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<Interval> merge(List<Interval> intervals) {
      Collections.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));

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

  public static class Solution2 implements Solution {
    @Override
    public List<Interval> merge(List<Interval> intervals) {
      intervals.sort((i1, i2) -> i1.start - i2.start);
      LinkedList<Interval> ret = new LinkedList<>();
      for(Interval next : intervals) {
        if(!ret.isEmpty() && next.start <= ret.getLast().end) ret.getLast().end = Math.max(ret.getLast().end, next.end);
        else ret.addLast(next);
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    List<Interval> input;
    List<Interval> result;
    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      input = new ArrayList<>();
      input.add(new Interval(1, 3));
      input.add(new Interval(2, 6));
      input.add(new Interval(8, 10));
      input.add(new Interval(15, 18));
      result = s.merge(input);
      System.out.println(result);

    }

  }


}
