package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

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

  public interface Solution {
    List<List<Integer>> subsetsWithDup(int[] num);
  }

  public static class Solution1 implements Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> ret = new ArrayList<>();
      int max = 1 << num.length;
      for(int mask = 0; mask < max; mask++) {
        List<Integer> current = new ArrayList<>();
        for(int i = 0; i < num.length; i++) {
          int check = 1 << i;
          if((mask & check) == 0) continue;
          int prev = check >>> 1;
          if(i != 0 && num[i] == num[i - 1] && (mask & prev) == 0) {
            current = null;
            break;
          } else {
            current.add(num[i]);
          }
        }
        if(current != null) ret.add(current);
      }
      return ret;
    }
  }

  /**
   * For every num,
   *   1) if it is not the same as previous, clone every list and add num to the end
   *   2) if it is the same as previous, do the same but with start index being the previous size
   */
  public static class Solution2 implements Solution {
    @Override
    public List<List<Integer>> subsetsWithDup(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> ret = new ArrayList<>();
      ret.add(new ArrayList<>());

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
    int[] num;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = new int[] {1, 2, 2};
      result = s.subsetsWithDup(num);
      System.out.println(result);
    }
  }

}
