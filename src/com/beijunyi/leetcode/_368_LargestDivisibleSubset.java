package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

public class _368_LargestDivisibleSubset implements Medium, NotHardButTricky {

  public interface Solution {
    List<Integer> largestDivisibleSubset(int[] nums);
  }

  public static class Solution1 implements Solution, DynamicPrograming {

    @Override
    public List<Integer> largestDivisibleSubset(int[] nums) {
      List<Integer> result = new ArrayList<>();
      int n = nums.length;
      if(n < 1) return result;
      Arrays.sort(nums);
      int[] s = new int[n];
      int[] parent = new int[n]; //used to build result from
      Arrays.fill(parent, -1);
      s[0] = 1;
      int maxSize = 0; //largest subset size
      int maxSizelastIndex = 0; //the index of largest subset size
      for(int i = 1; i < n; i++) {
        s[i] = 1;
        for(int j = i - 1; j >= 0; j--) {
          if(nums[i] % nums[j] == 0) {
            if(s[j] + 1 > s[i]) {
              s[i] = s[j] + 1;
              parent[i] = j;
            }
          }
        }
        if(s[i] > maxSize) {
          maxSize = s[i];
          maxSizelastIndex = i;
        }
      }
      //populate result;
      int i = maxSizelastIndex;
      while(i >= 0) {
        result.add(0, nums[i]);
        i = parent[i];
      }
      return result;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[]{1, 2, 4, 8};
      result = s.largestDivisibleSubset(nums);
      System.out.println(result);

      nums = new int[]{1, 2, 4, 8, 9, 72};
      result = s.largestDivisibleSubset(nums);
      System.out.println(result);
    }
  }

}
