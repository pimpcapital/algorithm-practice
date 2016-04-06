package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 */
public class _039_CombinationSum implements Medium {

  public static class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> result = new ArrayList<>();
      Arrays.sort(candidates);
      combinationSum(candidates, 0, target, new ArrayList<Integer>(), result);
      return result;
    }

    public void combinationSum(int[] candidates, int offset, int target, List<Integer> current, List<List<Integer>> result) {
      for(int i = offset; i < candidates.length; i++) {
        int n = candidates[i];
        if(n == target) {
          List<Integer> clone = new ArrayList<>(current);
          clone.add(n);
          result.add(clone);
          break;
        } else if(n < target) {
          current.add(n);
          combinationSum(candidates, i, target - n, current, result);
          current.remove(current.size() - 1);
        } else {
          break;
        }
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().combinationSum(new int[] {2, 3, 6, 7}, 7));
  }
}
