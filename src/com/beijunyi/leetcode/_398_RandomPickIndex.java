package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Random;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can
 * assume that the given target number must exist in the array.
 *
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * Example:
 *   int[] nums = new int[] {1,2,3,3,3};
 *   Solution solution = new Solution(nums);
 *
 *   // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *   solution.pick(3);
 *
 *   // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 *   solution.pick(1);
 */
public class _398_RandomPickIndex implements Medium {

  public interface Solution {

    void init(int[] nums);

    int pick(int target);

  }

  public static class Solution1 implements Solution {

    private int[] nums;
    private Random random;

    @Override
    public void init(int[] nums) {
      this.nums = nums;
      random = new Random();
    }

    @Override
    public int pick(int target) {
      int ret = -1;
      int total = 0;
      for(int i = 0; i < nums.length; i++) {
        if(nums[i] != target) continue;
        total++;
        if(random.nextInt(total) == 0) ret = i;
      }
      return ret;
    }
  }


  public static void main(String args[]) {
    int[] nums;
    int target;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1, 2, 3, 3, 3};
      target = 3;
      s.init(nums);
      result = s.pick(target);
      System.out.println(result);
    }

  }

}
