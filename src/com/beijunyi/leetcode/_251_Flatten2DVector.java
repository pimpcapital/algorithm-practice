package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Implement an iterator to flatten a 2d vector.
 *
 * For example,
 * Given 2d vector =
 *   [
 *     [1,2],
 *     [3],
 *     [4,5,6]
 *   ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 *
 * Hint:
 *   1) How many variables do you need to keep track?
 *   2) Two variables is all you need. Try with x and y.
 *   3) Beware of empty rows. It could be the first few rows.
 *   4) To write correct code, think about the invariant to maintain. What is it?
 *   5) The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant
 *      ahead of time or right when you need it?
 *   6) Not sure? Think about how you would implement hasNext(). Which is more complex?
 *   7) Common logic in two different places should be refactored into a common method.
 * Follow up:
 *   As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class _251_Flatten2DVector implements Medium, PremiumQuestion {

  public interface Solution extends Iterator<Integer> {
    void init(List<List<Integer>> vec2d);
  }

  public static class Solution1 implements Solution {

    private Iterator<List<Integer>> out;
    private Iterator<Integer> in;

    @Override
    public void init(List<List<Integer>> vec2d) {
      out = vec2d.iterator();
      in = null;
    }

    @Override
    public boolean hasNext() {
      prepareNext();
      return in != null;
    }

    @Override
    public Integer next() {
      prepareNext();
      return in != null ? in.next() : null;
    }

    private void prepareNext() {
      if(in != null && in.hasNext()) return;
      while((in == null || !in.hasNext()) && out.hasNext()) in = out.next().iterator();
      if(in != null && !in.hasNext()) in = null;
    }

  }

  public static void main(String args[]) {
    List<List<Integer>> vec2d;

    for(Solution s : Arrays.asList(new Solution1())) {
      vec2d = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3), Arrays.asList(4, 5, 6));
      s.init(vec2d);
      while(s.hasNext()) {
        System.out.println(s.next());
      }
    }
  }

}
