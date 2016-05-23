package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Backtracking;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T. Each number in C may only be used once in the combination.
 *
 * Note:
 *  All numbers (including target) will be positive integers.
 *  Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 *  The solution set must not contain duplicate combinations.
 *  For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 *
 * A solution set is:
 *  [1, 7]
 *  [1, 2, 5]
 *  [2, 6]
 *  [1, 1, 6]
 */
public class _040_CombinationSumTwo implements Medium {

  public interface Solution {
    List<List<Integer>> combinationSum2(int[] candidates, int target);
  }

  /**
   * Time: O(n^2)
   *  DFS complexity = O(G+E)
   *  G = n, E = n(n-1) where n is the number of candidates
   * Space: O(n)
   */
  public static class Solution1 implements Solution, Backtracking {

    @Override
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<List<Integer>> result = new ArrayList<>();
      combinationSum2(candidates, 0, target, new LinkedList<Integer>(), result);
      return result;
    }

    private static void combinationSum2(int[] candidates, int offset, int target, Deque<Integer> stack, List<List<Integer>> result) {
      if(target == 0) {
        result.add(new ArrayList<>(stack));
      } else {
        Integer prev = null;
        for(int i = offset; i < candidates.length; i++) {
          int current = candidates[i];
          if(prev != null && current == prev) continue;
          int subTarget = target - current;
          if(subTarget < 0) break;
          stack.offerLast(current);
          combinationSum2(candidates, i + 1, subTarget, stack, result);
          stack.pollLast();
          prev = current;
        }
      }
    }

  }

  public static void main(String args[]) {
    int[] candidates;
    int target;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
      target = 8;
      result = s.combinationSum2(candidates, target);
      for(List<Integer> combination : result) {
        System.out.print(combination);
      }
      System.out.println();
    }

  }

}
