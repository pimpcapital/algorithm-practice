package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.SlidingWindow;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * For example:
 *   MovingAverage m = new MovingAverage(3);
 *   m.next(1) = 1
 *   m.next(10) = (1 + 10) / 2
 *   m.next(3) = (1 + 10 + 3) / 3
 *   m.next(5) = (10 + 3 + 5) / 3
 */
public class _346_MovingAverageFromDataStream implements Easy, PremiumQuestion {

  public interface Solution {
    /** Initialize your data structure here. */
    void init(int size);

    double next(int val);
  }

  public static class Solution1 implements Solution, SlidingWindow {

    private int[] window;
    private int count = 0;
    private double sum = 0;

    @Override
    public void init(int size) {
      window = new int[size];
    }

    @Override
    public double next(int val) {
      int index = count % window.length;
      sum += (val - window[index]);
      window[index] = val;
      return sum / Math.min(++count, window.length);
    }
  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1())) {
      s.init(3);
      System.out.println(s.next(1));
      System.out.println(s.next(10));
      System.out.println(s.next(3));
      System.out.println(s.next(5));
    }
  }

}
