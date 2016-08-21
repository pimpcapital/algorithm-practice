package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.NestedInteger;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from
 * bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 *
 * Example 1:
 *   Given the list [[1,1],2,[1,1]], return 8.
 *   (four 1's at depth 1, one 2 at depth 2)
 *
 * Example 2:
 *   Given the list [1,[4,[6]]], return 17.
 *   (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
public class _364_NestedListWeightSumTwo implements Medium, PremiumQuestion {

  public interface Solution {
    int depthSumInverse(List<NestedInteger> nestedList);
  }

  public static class Solution1 implements Solution, Recursive {
    @Override
    public int depthSumInverse(List<NestedInteger> nestedList) {
      int maxDepth = findDepth(nestedList);
      return sum(nestedList, 0, maxDepth);
    }

    private static int findDepth(List<NestedInteger> nestedList) {
      int max = 0;
      for(NestedInteger ni : nestedList) {
        if(!ni.isInteger()) {
          max = Math.max(max, findDepth(ni.getList()) + 1);
        } else {
          max = Math.max(max, 1);
        }
      }
      return max;
    }

    private static int sum(List<NestedInteger> nestedList, int currentDepth, int maxDepth) {
      int ret = 0;
      for(NestedInteger ni : nestedList) {
        if(ni.isInteger()) {
          ret += (maxDepth - currentDepth) * ni.getInteger();
        } else {
          ret += sum(ni.getList(), currentDepth + 1, maxDepth);
        }
      }
      return ret;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public int depthSumInverse(List<NestedInteger> nestedList) {
      int unweighted = 0, weighted = 0;
      while (!nestedList.isEmpty()) {
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
          if (ni.isInteger())
            unweighted += ni.getInteger();
          else
            nextLevel.addAll(ni.getList());
        }
        weighted += unweighted;
        nestedList = nextLevel;
      }
      return weighted;
    }
  }

  public static void main(String args[]) {
    List<NestedInteger> nestedList;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nestedList = NestedInteger.fromArray(new Object[]{1, 1}, 2, new Object[]{1 , new Object[]{}});
      result = s.depthSumInverse(nestedList);
      System.out.println(result);

      nestedList = NestedInteger.fromArray(new Object[]{1, 1}, 2, new Object[]{1 , 1});
      result = s.depthSumInverse(nestedList);
      System.out.println(result);

      nestedList = NestedInteger.fromArray(1, new Object[]{4 , new Object[]{6}});
      result = s.depthSumInverse(nestedList);
      System.out.println(result);
    }
  }

}
