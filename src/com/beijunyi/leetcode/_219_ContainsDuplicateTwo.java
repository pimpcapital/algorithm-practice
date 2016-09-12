package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.SlidingWindow;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the difference between i and j is at most k.
 */
public class _219_ContainsDuplicateTwo implements Easy {

  public interface Solution {
    boolean containsNearbyDuplicate(int[] nums, int k);
  }

  /**
   * time: O(n)
   * space: O(n)
   */
  public static class Solution1 implements Solution {
    @Override
    public boolean containsNearbyDuplicate(int[] nums, int k) {
      Map<Integer, Integer> lastIndices = new HashMap<>();
      for(int i = 0; i < nums.length; i++) {
        int num = nums[i];
        if(lastIndices.containsKey(num)) {
          int lastIndex = lastIndices.get(num);
          if(i - lastIndex <= k) return true;
        }
        lastIndices.put(num, i);
      }
      return false;
    }
  }

  /**
   * time: O(n)
   * space: O(k)
   */
  public static class Solution2 implements Solution, SlidingWindow {
    @Override
    public boolean containsNearbyDuplicate(int[] nums, int k) {
      Set<Integer> set = new HashSet<>();
      for(int i = 0; i < nums.length; i++){
        if(i > k) set.remove(nums[i-k-1]);
        if(!set.add(nums[i])) return true;
      }
      return false;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int k;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {1, 2, 3, 4, 5, 1, 2, 1};
      k = 2;
      System.out.println(s.containsNearbyDuplicate(nums, k));

      nums = new int[] {1, 2, 3, 4, 5, 1, 2, 3, 1};
      k = 2;
      System.out.println(s.containsNearbyDuplicate(nums, k));

      System.out.println();
    }


  }

}
