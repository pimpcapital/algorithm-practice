package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 *
 * For example, given two 1d vectors:
 *   v1 = [1, 2]
 *   v2 = [3, 4, 5, 6]
 *   By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 *   [1, 3, 2, 4, 5, 6].
 *
 * Follow up:
 *   What if you are given k 1d vectors? How well can your code be extended to such cases?
 *
 * Follow up:
 *   The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you,
 *   replace "Zigzag" with "Cyclic". For example, given the following input:
 *     [1,2,3]
 *     [4,5,6,7]
 *     [8,9]
 *   It should return [1,4,8,2,5,9,3,6,7].
 */
public class _281_ZigzagIterator implements Medium, PremiumQuestion {

  public interface Solution {

    void init(List<Integer> v1, List<Integer> v2);

    int next();

    boolean hasNext();

  }

  public static class Solution1 implements Solution {

    private List<Iterator<Integer>> its;
    private int max;
    private int turn;

    @Override
    public void init(List<Integer> v1, List<Integer> v2) {
      its = new ArrayList<>();
      its.add(v1.iterator());
      its.add(v2.iterator());
      max = 2;
      turn = 0;
    }

    @Override
    public int next() {
      if(!hasNext()) throw new NoSuchElementException();
      return its.get((turn++) % max).next();
    }

    @Override
    public boolean hasNext() {
      int failCount = 0;
      while(!its.get(turn % max).hasNext()) {
        if(++failCount == max) return false;
        turn++;
      }
      return true;
    }
  }

  public static void main(String args[]) {
    List<Integer> v1;
    List<Integer> v2;

    for(Solution s : Arrays.asList(new Solution1())) {
      v1 = Arrays.asList(1, 2);
      v2 = Arrays.asList(3, 4, 5, 6);
      s.init(v1, v2);
      System.out.println(s.next());
      System.out.println(s.next());
      System.out.println(s.next());
      System.out.println(s.next());
      System.out.println(s.next());
      System.out.println(s.next());
      System.out.println(s.hasNext());
    }
  }



}
