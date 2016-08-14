package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a
 * specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must
 * be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class _167_TwoSumTwoInputArrayIsSorted implements Medium, PremiumQuestion {

  public interface Solution {
    int[] twoSum(int[] numbers, int target);
  }

  public static class Solution1 implements Solution {
    @Override
    public int[] twoSum(int[] numbers, int target) {
      int start = 0;
      int end = numbers.length - 1;
      while(end > start) {
        int sum = numbers[start] + numbers[end];
        if(sum == target) return new int[] {start + 1, end + 1};
        if(sum > target) end--;
        else start++;
      }
      throw new IllegalArgumentException();
    }
  }

  public static void main(String args[]) {
    int[] numbers;
    int target;
    int[] result;

    for(Solution s : Arrays.asList(new Solution1())) {
      numbers = new int[] {0, 0, 3, 4};
      target = 0;
      result = s.twoSum(numbers, target);
      System.out.println(Arrays.toString(result));
    }
  }

}
