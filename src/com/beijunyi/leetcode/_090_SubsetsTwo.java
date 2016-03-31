package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 *
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,2], a solution is:
 *
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class _090_SubsetsTwo implements Medium {

  public static class Solution1 {
    public List<List<Integer>> subsetsWithDup(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> ret = new ArrayList<>();
      if(num.length == 0)
        return ret;

      Set<Integer> sigs = new HashSet<>();
      int count = 0;
      int max = 1 << num.length;

      do {
        List<Integer> subset = new ArrayList<>();
        for(int i = 0; i < num.length; i++) {
          int mask = 1 << i;
          if((count & mask) != 0)
            subset.add(num[i]);
        }
        int sig = subset.hashCode();
        if(sigs.contains(sig))
          continue;
        sigs.add(sig);
        ret.add(subset);
      } while(++count < max);

      return ret;
    }
  }

  public static class Solution2 {
    public List<List<Integer>> subsetsWithDup(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> ret = new ArrayList<>();
      ret.add(new ArrayList<Integer>());

      int size = 0, startIndex;
      for(int i = 0; i < num.length; i++) {
        startIndex = (i >= 1 && num[i] == num[i - 1]) ? size : 0;
        size = ret.size();
        for(int j = startIndex; j < size; j++) {
          List<Integer> temp = new ArrayList<>(ret.get(j));
          temp.add(num[i]);
          ret.add(temp);
        }
      }
      return ret;
    }
  }


  public static void main(String args[]) {
    System.out.println(new Solution1().subsetsWithDup(new int[] {1, 2, 2}));
    System.out.println(new Solution2().subsetsWithDup(new int[] {1, 2, 2}));
  }

}
