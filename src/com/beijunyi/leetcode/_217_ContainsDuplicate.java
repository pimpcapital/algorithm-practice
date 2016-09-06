package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value
 * appears at least twice in the array, and it should return false if every element is distinct.
 */
public class _217_ContainsDuplicate implements Easy {

  public interface Solution {
    boolean containsDuplicate(int[] nums);
  }

  public static class Solution1 implements Solution {
    @Override
    public boolean containsDuplicate(int[] nums) {
      Set<Integer> vals = new HashSet<>();
      for(int n : nums) {
        if(!vals.add(n)) return true;
      }
      return false;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    boolean result;
    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1, 2, 3, 1};
      result = s.containsDuplicate(nums);
      System.out.println(result);
    }

  }

}
