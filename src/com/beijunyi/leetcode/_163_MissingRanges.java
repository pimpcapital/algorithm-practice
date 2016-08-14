package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
 *
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class _163_MissingRanges implements Medium, PremiumQuestion {

  public interface Solution {
    List<String> findMissingRanges(int[] nums, int lower, int upper);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
      List<String> ret = new ArrayList<>();
      int i = 0;
      while(i < nums.length && lower <= upper) {
        int num = nums[i++];
        if(num > lower) {
          addMissingRange(lower, Math.min(upper, num - 1), ret);
          lower = num + 1;
        } else if(num == lower) {
          lower++;
        }
      }
      if(lower <= upper) addMissingRange(lower, upper, ret);
      return ret;
    }

    private static void addMissingRange(int lower, int upper, List<String> result) {
      if(lower == upper) result.add(Integer.toString(lower));
      else result.add(lower + "->" + upper);
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int lower;
    int upper;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {0, 1, 3, 50, 75};
      lower = 0;
      upper = 99;
      result = s.findMissingRanges(nums, lower, upper);
      System.out.println(result);

      nums = new int[] {0, 1, 3, 50, 108};
      lower = 0;
      upper = 99;
      result = s.findMissingRanges(nums, lower, upper);
      System.out.println(result);

      nums = new int[] {};
      lower = 1;
      upper = 1;
      result = s.findMissingRanges(nums, lower, upper);
      System.out.println(result);
    }


  }

}
