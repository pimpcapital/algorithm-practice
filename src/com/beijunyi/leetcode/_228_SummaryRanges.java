package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class _228_SummaryRanges implements Medium {

  public interface Solution {
    List<String> summaryRanges(int[] nums);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<String> summaryRanges(int[] nums) {
      List<String> ret = new ArrayList<>();
      if(nums.length == 0) return ret;
      int start = 0;
      for(int i = 1; i < nums.length; i++) {
        if(nums[i] != nums[i - 1] + 1) {
          addRange(ret, nums[start], nums[i - 1]);
          start = i;
        }
      }
      addRange(ret, nums[start], nums[nums.length - 1]);
      return ret;
    }

    private static void addRange(List<String> result, int start, int end) {
      if(start == end) {
        result.add(Integer.toString(start));
      } else {
        result.add(start + "->" + end);
      }
    }

  }

  public static void main(String args[]) {
    int[] nums;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {0, 1, 2, 4, 5, 7};
      result = s.summaryRanges(nums);
      System.out.println(result);
    }
  }

}
