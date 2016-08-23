package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

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

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
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
