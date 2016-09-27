package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.QuickSelect;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * For example,
 *   Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 *   You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class _215_KthLargestElementInAnArray implements Medium, Important {

  public interface Solution {
    int findKthLargest(int[] nums, int k);
  }

  public static class Solution1 implements Solution, QuickSelect {
    @Override
    public int findKthLargest(int[] nums, int k) {
      return findKthLargest(nums, 0, nums.length, k);
    }

    private static int findKthLargest(int[] nums, int start, int end, int k) {
      int pivotIndex = findPivot(nums, start, end); // approximate medium
      pivotIndex = partition(nums, start, end, pivotIndex); // real medium
      if(pivotIndex == nums.length - k) return nums[pivotIndex];
      if(pivotIndex < nums.length - k) return findKthLargest(nums, pivotIndex + 1, end, k);
      return findKthLargest(nums, start, pivotIndex, k);
    }

    private static int findPivot(int[] nums, int start, int end) {
      return start + (end - start) / 2;
    }

    private static int partition(int[] nums, int start, int end, int pivotIndex) {
      int pivotValue = nums[pivotIndex];
      int left = start;
      int right = end - 1;
      swap(nums, pivotIndex, right);
      while(left < right) {
        if(nums[left] > pivotValue) {
          swap(nums, left, right);
          swap(nums, left, --right);
        } else {
          left++;
        }
      }
      return left;
    }

    private static void swap(int[] nums, int a, int b) {
      int tmp = nums[a];
      nums[a] = nums[b];
      nums[b] = tmp;
    }
  }

  private static class Solution2 implements Solution {
    @Override
    public int findKthLargest(int[] nums, int k) {
      PriorityQueue<Integer> largeK = new PriorityQueue<>(k + 1); // dynamic largest K + 1 numbers

      for(int el : nums) {
        largeK.add(el);
        if (largeK.size() > k) {
          largeK.poll();
        }
      }

      return largeK.poll();
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int k;
    int result;
    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {2,1};
      k = 1;
      result = s.findKthLargest(nums, k);
      System.out.println(result);

      nums = new int[] {3,2,1,5,6,4};
      k = 2;
      result = s.findKthLargest(nums, k);
      System.out.println(result);
    }
  }

}
