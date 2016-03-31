package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a collection of numbers, return all possible permutations.
 *
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
public class _046_Permutations implements Medium {

  public static class Solution {
    public List<List<Integer>> permute(int[] num) {
      List<List<Integer>> result = new ArrayList<>();
      permute(num, 0, result);
      return result;
    }

    private void permute(int[] num, int start, List<List<Integer>> result){
      if (start == num.length - 1) {
        List<Integer> permutation = new ArrayList<>();
        for(int n : num)
          permutation.add(n);
        result.add(permutation);
      }
      else{
        for (int i = start; i < num.length; i++) {
          int temp = num[start];
          num[start] = num[i];
          num[i] = temp;
          permute(num, start + 1, result);
          temp = num[start];
          num[start] = num[i];
          num[i] = temp;
        }
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().permute(new int[] {1, 2, 3}));
  }

}
