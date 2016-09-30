package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * For example:
 *   Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 *   Window position                Max
 *   ---------------               -----
 *   [1  3  -1] -3  5  3  6  7       3
 *    1 [3  -1  -3] 5  3  6  7       3
 *    1  3 [-1  -3  5] 3  6  7       5
 *    1  3  -1 [-3  5  3] 6  7       5
 *    1  3  -1  -3 [5  3  6] 7       6
 *    1  3  -1  -3  5 [3  6  7]      7
 *   Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 * Note:
 *   You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 *   Could you solve it in linear time?
 */
public class _239_SlidingWindowMaximum implements Hard {

  public interface Solution {
    int[] maxSlidingWindow(int[] nums, int k);
  }

  /**
   * Time: O(n), Space: O(k) where:
   *   n is the number of numbers
   *   k is the size of the window
   */
  public static class Solution1 implements Solution {

    @Override
    public int[] maxSlidingWindow(int[] nums, int k) {
      if(k == 0) return new int[0];
      int[] ret = new int[nums.length - k + 1];
      Deque<Integer> window = new LinkedList<>();
      for(int right = 0; right < nums.length; right++) {
        int left = right - k + 1;
        if(!window.isEmpty() && window.peekFirst() < left)
          window.pollFirst(); // remove the out-of-window element
        int num = nums[right];
        while(!window.isEmpty() && nums[window.peekLast()] < num) // remove the elements smaller than the new element
          window.pollLast();
        window.offerLast(right);
        if(left >= 0)
          ret[left] = nums[window.peekFirst()];
      }
      return ret;
    }

  }

  public static void main(String[] args) {
    int[] nums;
    int k;
    int[] result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1,3,-1,-3,5,3,6,7};
      k = 3;
      result = s.maxSlidingWindow(nums, k);
      System.out.println(Arrays.toString(result));

      nums = new int[] {1,3,1,2,0,5};
      k = 3;
      result = s.maxSlidingWindow(nums, k);
      System.out.println(Arrays.toString(result));
    }


  }

}
