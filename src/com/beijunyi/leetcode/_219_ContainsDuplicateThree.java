package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.WithBinarySearchTree;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
 * difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 */
public class _219_ContainsDuplicateThree implements Medium {

  public interface Solution {
    boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t);
  }

  public static class Solution1 implements Solution, WithBinarySearchTree {

    @Override
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
      if(k == 0) return false;
      TreeMap<Integer, Integer> lastIndices = new TreeMap<>();
      for(int i = 0; i < nums.length; i++) {
        if(i > k) {
          int oldIndex = i - k - 1;
          int oldValue = nums[oldIndex];
          if(lastIndices.get(oldValue) == oldIndex)
            lastIndices.remove(oldValue);
        }
        int num = nums[i];
        Integer maxLower = lastIndices.lowerKey(num);
        if(maxLower != null && (long) num - maxLower <= t) return true;
        Integer minHigher = lastIndices.ceilingKey(num);
        if(minHigher != null && (long) minHigher - num <= t) return true;

        lastIndices.put(num, i);
      }
      return false;
    }

  }

  /**
   * Much neater and faster solution
   */
  public static class Solution2 implements Solution, WithBinarySearchTree {
    @Override
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
      if(nums.length < 2 || k <= 0) return false;

      TreeSet<Integer> values = new TreeSet<>();
      for(int i = 0; i < nums.length; i++) {
        int num = nums[i];
        Integer floor = values.floor(num + t); // largest value before (num + t)
        Integer ceil = values.ceiling(num - t); // smallest value since (num - t) inclusively
        if(floor != null && floor >= num || ceil != null && ceil <= num) return true;

        values.add(num);
        if(i >= k) values.remove(nums[i - k]);
      }

      return false;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int k;
    int t;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {1, 5, 8, 1, 1};
      k = 2;
      t = 0;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      nums = new int[] {1, 2};
      k = 0;
      t = 1;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      nums = new int[] {-1, 2147483647};
      k = 1;
      t = 2147483647;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      nums = new int[] {4, 1, 6, 3};
      k = 100;
      t = 1;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      nums = new int[] {1, 100, 200, 300, 5, 200};
      k = 4;
      t = 4;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      nums = new int[] {1, 100, 200, 300, 5, 800};
      k = 4;
      t = 3;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      nums = new int[] {1, 100, 200, 300, 5, 800};
      k = 3;
      t = 4;
      System.out.println(s.containsNearbyAlmostDuplicate(nums, k, t));

      System.out.println();
    }
  }

}
