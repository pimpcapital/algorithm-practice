package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.NestedInteger;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 *
 * Example 2:
 * Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 */
public class _339_NestedListWeightSum implements Easy, PremiumQuestion {

  public interface Solution {
    int depthSum(List<NestedInteger> nestedList);
  }

  public static class Solution1 implements Solution, Recursive {
    @Override
    public int depthSum(List<NestedInteger> nestedList) {
      return depthSum(nestedList, 1);
    }

    private static int depthSum(List<NestedInteger> nestedIntegers, int depth) {
      int sum = 0;
      for(NestedInteger ni : nestedIntegers) {
        if(ni.isInteger()) sum += ni.getInteger() * depth;
        else sum += depthSum(ni.getList(), depth + 1);
      }
      return sum;
    }

  }

  public static void main(String args[]) {
    List<NestedInteger> nestedList;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nestedList = NestedInteger.fromArray(new Object[]{1, 1}, 2, new Object[]{1, 1});
      result = s.depthSum(nestedList);
      System.out.println(result);

      nestedList = NestedInteger.fromArray(1, new Object[]{4, new Object[]{6}});
      result = s.depthSum(nestedList);
      System.out.println(result);
    }

  }

}
