package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.SlidingWindow;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to
 * the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest
 * timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 *   HitCounter counter = new HitCounter();
 *
 *   // hit at timestamp 1.
 *   counter.hit(1);
 *
 *   // hit at timestamp 2.
 *   counter.hit(2);
 *
 *   // hit at timestamp 3.
 *   counter.hit(3);
 *
 *   // get hits at timestamp 4, should return 3.
 *   counter.getHits(4);
 *
 *   // hit at timestamp 300.
 *   counter.hit(300);
 *
 *   // get hits at timestamp 300, should return 4.
 *   counter.getHits(300);
 *
 *   // get hits at timestamp 301, should return 3.
 *   counter.getHits(301);
 */
public class _362_DesignHitCounter implements Medium, PremiumQuestion {

  public interface Solution {
    /** Initialize your data structure here. */
    void init();

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    void hit(int timestamp);

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    int getHits(int timestamp);
  }

  public static class Solution1 implements Solution, SlidingWindow {

    private int size = 5 * 60;
    private int[] window = new int[size];
    private int lastTimestamp = 0;

    @Override
    public void init() {

    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    @Override
    public void hit(int timestamp) {
      timestamp--;
      forwardTime(timestamp);
      int index = timestamp % size;
      window[index]++;
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    @Override
    public int getHits(int timestamp) {
      timestamp--;
      forwardTime(timestamp);
      int count = 0;
      for(int i = 0; i < size; i++) {
        count += window[i];
      }
      return count;
    }

    private void forwardTime(int timestamp) {
      int toClear = Math.min(timestamp - lastTimestamp, size);
      int lastIndex = lastTimestamp % size;
      for(int i = 0; i < toClear; i++) {
        int index = (i + lastIndex + 1) % size;
        window[index] = 0;
      }
      lastTimestamp = timestamp;
    }

  }

  public static class Solution2 implements Solution {

    private Queue<Integer> q = null;

    @Override
    public void init() {
      q = new LinkedList<>();
    }

    @Override
    public void hit(int timestamp) {
      q.offer(timestamp);
    }

    @Override
    public int getHits(int timestamp) {
      while(!q.isEmpty() && timestamp - q.peek() >= 300) {
        q.poll();
      }
      return q.size();
    }
  }

  public static class Solution3 implements Solution {

    private Deque<IntCount> q;
    private int total = 0;

    @Override
    public void init() {
      q = new LinkedList<>();
    }

    @Override
    public void hit(int timestamp) {
      total++;
      if(!q.isEmpty() && q.peekLast().value == timestamp) q.peekLast().count++;
      else q.offerLast(new IntCount(timestamp));
    }

    @Override
    public int getHits(int timestamp) {
      gc(timestamp);
      return total;
    }

    private void gc(int timestamp) {
      while(!q.isEmpty() && q.peekFirst().value <= timestamp - 300) total -= q.poll().count;
    }

    private static class IntCount {
      final int value;
      int count = 1;

      IntCount(int value) {
        this.value = value;
      }
    }
  }

  public static void main(String args[]) {
    int hits;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      s.init();
      s.hit(1);
      s.hit(2);
      s.hit(3);
      hits = s.getHits(4);
      System.out.println(hits);
      s.hit(300);
      hits = s.getHits(300);
      System.out.println(hits);
      hits = s.getHits(301);
      System.out.println(hits);

      System.out.println();
    }
  }

}
