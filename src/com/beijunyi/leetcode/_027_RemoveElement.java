package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 * Example:
 *  Given input array nums = [3,2,2,3], val = 3
 *
 * Your function should return length = 2, with the first two elements of nums being 2.
 *
 * Hint:
 *  Try two pointers.
 *  Did you use the property of "the order of elements can be changed"?
 *  What happens when the elements to remove are rare?
 */
public class _027_RemoveElement implements Easy {

  public interface Solution {
    int removeElement(int[] nums, int val);
  }

  /**
   * Time: O(n),
   * Space: O(1)
   *
   * Preserving the order of the elements.
   * Both pointers start from left.
   */
  public static class Solution1 implements Solution {

    @Override
    public int removeElement(int[] nums, int val) {
      int read = 0;
      int write = 0;
      while(read < nums.length) {
        int num = nums[read++];
        if(num != val) nums[write++] = num;
      }
      return write;
    }

  }

  /**
   * Time: O(n),
   * Space: O(1)
   *
   * Faster algorithm (least write operations). Taking the advantage of "the order of elements can be changed".
   * Points start from both ends of the array.
   */
  public static class Solution2 implements Solution {

    @Override
    public int removeElement(int[] nums, int val) {
      if(nums.length == 0) return 0;
      int left = 0;
      int right = nums.length;
      while(left != right) {
        if(nums[left] != val)
          left++;
        else {
          right--;
          if(nums[right] != val) nums[left] = nums[right];
        }
      }
      return left;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int val;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {3, 2, 2, 3};
      val = 3;
      result = s.removeElement(nums, val);
      System.out.println(result);
    }
  }

}
