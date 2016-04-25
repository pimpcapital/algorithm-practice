package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.HeapBased;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 *
 * Examples:
 *   [2,3,4] , the median is 3
 *   [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 * For example:
 *   add(1)
 *   add(2)
 *   findMedian() -> 1.5
 *   add(3)
 *   findMedian() -> 2
 */
public class _295_FindMedianFromDataStream implements Hard {

  public interface Solution {
    // Adds a number into the data structure.
    void addNum(int num);

    // Returns the median of current data stream
    double findMedian();
  }

  /**
   * Time:
   *  addNum: O(n*log(n))
   *  findMedian: O(1)
   * Space:
   *  O(n)
   * where n is the number of times addNum gets called
   */
  public static class Solution1 implements Solution, HeapBased {

    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return Integer.compare(o2, o1);
      }
    });

    @Override
    public void addNum(int num) {
      if(maxHeap.isEmpty() || num <= maxHeap.peek())
        maxHeap.offer(num);
      else if(minHeap.isEmpty() || num > minHeap.peek())
        minHeap.offer(num);
      else
        maxHeap.offer(num);

      if(minHeap.size() > maxHeap.size())
        maxHeap.offer(minHeap.poll());
      else if(minHeap.size() + 1 < maxHeap.size())
        minHeap.offer(maxHeap.poll());
    }

    @Override
    public double findMedian() {
      int m1 = maxHeap.peek();
      return minHeap.size() == maxHeap.size() ? 0.5 * (m1 + minHeap.peek()) : m1;
    }

  }

  /**
   * Time:
   *  addNum: O(n*log(n))
   *  findMedian: O(1)
   * Space:
   *  O(n)
   * where n is the number of times addNum gets called
   */
  public static class Solution2 implements Solution, HeapBased {

    private Queue<Integer> minHeap = new PriorityQueue<>();
    private Queue<Integer> maxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      };
    });

    @Override
    public void addNum(int num) {
      minHeap.add(num);
      maxHeap.add(minHeap.poll());
      if(minHeap.size() < maxHeap.size())
        minHeap.add(maxHeap.poll());
    }

    @Override
    public double findMedian() {
      return minHeap.size() > maxHeap.size()
               ? minHeap.peek()
               : (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      s.addNum(1);
      System.out.println(s.findMedian());
      s.addNum(2);
      System.out.println(s.findMedian());
      s.addNum(3);
      System.out.println(s.findMedian());
    }

  }

}
