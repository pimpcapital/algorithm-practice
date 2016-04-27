package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Iterative;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 *  Return true if there exists i, j, k
 *  such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 *  Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Examples:
 *  Given [1, 2, 3, 4, 5],
 *  return true.
 *
 * Given [5, 4, 3, 2, 1],
 *  return false.
 */
public class _334_IncreasingTripletSubsequence implements Medium {

  public interface Solution {
    boolean increasingTriplet(int[] nums);
  }

  /**
   * Time: O(n)
   * Space: O(1)
   */
  public static class Solution1 implements Solution, Iterative {
    @Override
    public boolean increasingTriplet(int[] nums) {
      return increasingSequence(nums, 3);
    }

    private boolean increasingSequence(int[] nums, int k) {
      assert k > 0;
      Integer[] sequenceMax = new Integer[k - 1];
      for(int num : nums) {
        if(sequenceMax[0] == null || num < sequenceMax[0]) {
          sequenceMax[0] = num;
        } else {
          for(int i = 0; i < k - 1; i++) {
            if(sequenceMax[i] == null) break;
            if(sequenceMax[i] < num) {
              int promote = i + 1;
              if(promote == k - 1) return true; // we found it
              if(sequenceMax[promote] == null || sequenceMax[promote] > num) {
                sequenceMax[promote] = num;
                break;
              }
            }
          }
        }
      }
      return false;
    }
  }


  /**
   * Time: O(n)
   * Space: O(1)
   */
  public static class Solution2 implements Solution, Iterative {

    @Override
    public boolean increasingTriplet(int[] nums) {
      // start with two largest values, as soon as we find a number bigger than both, while both have been updated,
      // return true.
      int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
      for(int n : nums) {
        if(n <= small) small = n; // update small if n is smaller than both
        else if(n <= big) big = n;                  // update big only if greater than small but smaller than big
        else return true; // return if you find a number bigger than both
      }
      return false;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {1, 2, 3, 4, 5};
      result = s.increasingTriplet(nums);
      System.out.println(result);

      nums = new int[] {5, 4, 3, 2, 1};
      result = s.increasingTriplet(nums);
      System.out.println(result);

      nums = new int[] {1, 7, 4, 5};
      result = s.increasingTriplet(nums);
      System.out.println(result);

      nums = new int[] {1, 1, 1, 1};
      result = s.increasingTriplet(nums);
      System.out.println(result);

      nums = new int[] {};
      result = s.increasingTriplet(nums);
      System.out.println(result);

      nums = new int[] {1, 2, -10, -8, -7};
      result = s.increasingTriplet(nums);
      System.out.println(result);
    }
  }

}
