package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Random;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Shuffle a set of numbers without duplicates.
 *
 * Example:
 *   // Init an array with set 1, 2, and 3.
 *   int[] nums = {1,2,3};
 *   Solution solution = new Solution();
 *   solution.init(nums);
 *
 *   // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 *   solution.shuffle();
 *
 *   // Resets the array back to its original configuration [1,2,3].
 *   solution.reset();
 *
 *   // Returns the random shuffling of array [1,2,3].
 *   solution.shuffle();
 */
public class _384_ShuffleAnArray implements Medium {

  public interface Solution {

    void init(int[] nums);

    /** Resets the array to its original configuration and return it. */
    int[] reset();

    /** Returns a random shuffling of the array. */
    int[] shuffle();
  }

  /**
   * This is actually a wrong answer as there is no randomness in this solution.
   */
  public static class Solution1 implements Solution {

    private int[] nums;
    private int[] swapTargets;

    @Override
    public void init(int[] nums) {
      this.nums = nums;
      swapTargets = new int[nums.length];
      for(int i = 0; i < swapTargets.length; i++) {
        swapTargets[i] = i;
      }
    }

    @Override
    public int[] reset() {
      if(nums.length <= 1) return nums;
      for(int i = nums.length - 1; i >= 0; i--) {
        reverse(i);
      }
      return nums;
    }

    @Override
    public int[] shuffle() {
      if(nums.length <= 1) return nums;
      swapDigit(nums.length - 1);
      return nums;
    }

    private void swapDigit(int digit) {
      if(digit == -1) {
        reset();
        return;
      }
      // reverse previous swap
      int prevTarget = swapTargets[digit];
      reverse(digit);

      // perform next swap
      if(prevTarget == nums.length - 1) {
        swapDigit(digit - 1);
      } else {
        int newTarget = prevTarget + 1;
        swap(digit, newTarget);
      }
    }

    private void swap(int digit, int target) {
      int tmp = nums[digit];
      nums[digit] = nums[target];
      nums[target] = tmp;
      if(swapTargets[digit] == target) swapTargets[digit] = digit;
      else swapTargets[digit] = target;
    }

    private void reverse(int digit) {
      if(swapTargets[digit] != digit) swap(digit, swapTargets[digit]);
    }

  }

  public static class Solution2 implements Solution {

    private int[] nums;
    private Random random;

    @Override
    public void init(int[] nums) {
      this.nums = nums;
      random = new Random();
    }

    @Override
    public int[] reset() {
      return nums;
    }

    @Override
    public int[] shuffle() {
      if(nums == null) return null;
      int[] clone = nums.clone();
      for(int i = 1; i < clone.length; i++) {
        int j = random.nextInt(i + 1);
        swap(clone, i, j);
      }
      return clone;
    }

    private static void swap(int[] nums, int i, int j) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
    }
  }

  public static class Solution3 implements Solution {

    private int[] snapshot;
    private int[] nums;
    private Random random;

    @Override
    public void init(int[] nums) {
      this.snapshot = nums.clone();
      this.nums = nums;
      random = new Random();
    }

    @Override
    public int[] reset() {
      return snapshot;
    }

    @Override
    public int[] shuffle() {
      for(int i = 0; i < nums.length; i++) {
        int j = random.nextInt(i + 1);
        swap(i, j);
      }
      return nums;
    }

    private void swap(int i, int j) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
    }
  }

  public static void main(String args[]) {
    int[] nums;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      nums = new int[] {1, 2, 3};
      s.init(nums);
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println("permutation cycle completed");
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.reset()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
      System.out.println(Arrays.toString(s.shuffle()));
    }
  }

}
