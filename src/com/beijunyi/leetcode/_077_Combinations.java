package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class _077_Combinations implements Medium {

  public static class Solution {
    public List<List<Integer>> combine(int n, int k) {
      LinkedList<List<Integer>> result = new LinkedList<>();
      result.add(new ArrayList<Integer>());
      combine(1, n, k, result);
      return result;
    }

    public void combine(int min, int max, int k, LinkedList<List<Integer>> result) {
      if(k == 0 || min > max)
        return;
      int size = result.size();
      for(int i = 0; i < size; i++) {
        List<Integer> prototype = result.removeFirst();
        for(int n = min; n <= max - k + 1; n++) {
          LinkedList<List<Integer>> temp = new LinkedList<>();
          List<Integer> clone = new ArrayList<>(prototype);
          clone.add(n);
          temp.add(clone);
          combine(n + 1, max, k - 1, temp);
          result.addAll(temp);
        }
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().combine(4, 2));
  }
}
