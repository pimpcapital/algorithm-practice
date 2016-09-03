package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

public class _089_GrayCode implements Medium {

  public interface Solution {
    List<Integer> grayCode(int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<Integer> grayCode(int n) {
      List<Integer> ret = new ArrayList<>();
      int num = 0;
      int max = (int) Math.pow(2, n);
      for(int i = 0; i < max; i++) {
        int cycle = 4;
        int activate = 1;
        int deactivate = 3;
        for(int d = 0; d < n ; d++) {
          int phase = i % cycle;
          if(phase == activate) {
            num |= (1 << d);
          } else if(phase == deactivate) {
            num &= ~(1 << d);
          }
          cycle *= 2;
          activate *= 2;
          deactivate *= 2;
        }
        ret.add(num);
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public List<Integer> grayCode(int n) {
      List<Integer> result = new LinkedList<>();
      for (int i = 0; i < 1<<n; i++)
        result.add(i ^ i>>1);
      return result;
    }
  }

  public static void main(String args[]) {
    int n;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 3;
      result = s.grayCode(n);
      System.out.println(result);
    }
  }

}
