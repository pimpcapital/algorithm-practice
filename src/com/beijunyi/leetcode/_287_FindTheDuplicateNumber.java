package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.BinarySearch;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least
 * one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Note:
 *   You must not modify the array (assume the array is read only).
 *   You must use only constant, O(1) extra space.
 *   Your runtime complexity should be less than O(n^2).
 *   There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class _287_FindTheDuplicateNumber implements Hard {

  public interface Solution {
    int findDuplicate(int[] nums);
  }

  /**
   * Time: O(n)
   * Space: O(1) or O(log(n)) as 32 is the number of bits stored for each integer
   */
  public static class Solution1 implements Solution {

    @Override
    public int findDuplicate(int[] nums) {
      int[] counts = new int[32];
      for(int num : nums) {
        updateCounts(num, counts, 1);
      }
      for(int num = 1; num < nums.length; num++) {
        updateCounts(num, counts, -1);
      }
      return makeNumber(counts);
    }

    private static void updateCounts(int num, int[] counts, int increment) {
      int bit = 1;
      for(int i = 0; i < 32; i++) {
        if((num & bit) != 0) counts[i] += increment;
        bit = bit << 1;
      }
    }

    private static int makeNumber(int[] counts) {
      int bit = 1;
      int ret = 0;
      for(int i = 0; i < 32; i++) {
        if(counts[i] > 0) ret += bit;
        bit = bit << 1;
      }
      return ret;
    }

  }

  /**
   * Time: O(n * log(n))
   * Space: O(1)
   *
   * This solution is based on binary search.
   *
   * At first the search space is numbers between 1 to n. Each time I select a number mid (which is the one in the
   * middle) and count all the numbers equal to or less than mid. Then if the count is more than mid, the search space
   * will be [1 mid] otherwise [mid+1 n]. I do this until search space is only one number.
   */
  public static class Solution2 implements Solution, BinarySearch {

    @Override
    public int findDuplicate(int[] nums) {
      int left = 0, right = nums.length - 1;
      while(left < right) {
        int mid = left + (right - left) / 2;
        int count = numBelow(nums, mid);
        if(count > mid) right = mid;
        else left = mid + 1;
      }
      return left;
    }

    private static int numBelow(int[] nums, int target) {
      int result = 0;
      for(int num : nums) if(num <= target) result++;
      return result;
    }

  }

  /**
   * Suppose the array is
   *   index: 0 1 2 3 4 5
   *   value: 2 5 1 1 4 3
   *
   * First subtract 1 from each element in the array, so it is much easy to understand. use the value as pointer. the
   * array becomes:
   *   index: 0 1 2 3 4 5
   *   value: 1 4 0 0 3 2
   *
   * 5 -> 2 -> 0 <- 3
   *           |    ^
   *           v    |
   *           1 -> 4
   *
   * Now the problem is the same as find the cycle in LinkedList!
   * The fast one goes forward two steps each time, while the slow one goes only step each time. They must meet the same
   * item when slow==fast. In fact, they meet in a circle, the duplicate number must be the entry point of the circle
   * when visiting the array from nums[0]. Next we just need to find the entry point. We use a point(we can use the fast
   * one before) to visit form beginning with one step each time, do the same job to slow. When fast==slow, they meet at
   * the entry point of the circle. The easy understood code is as follows.
   *
   */
  public static class Solution3 implements Solution {

    @Override
    public int findDuplicate(int[] nums) {
      int n = nums.length;
      int slow = n;
      int fast = n;
      do {
        slow = nums[slow - 1];
        fast = nums[nums[fast - 1] - 1];
      } while(slow != fast);
      slow = n;
      while(slow != fast) {
        slow = nums[slow - 1];
        fast = nums[fast - 1];
      }
      return slow;
    }
  }

  /**
   * Time: O(n) or O(n*log(n)) as 32 is the number of bits stored for each integer
   * Space: O(1)
   */
  public static class Solution4 implements Solution {

    public int findDuplicate(int[] nums) {
      int n = nums.length - 1, res = 0;
      for(int p = 0; p < 32; ++p) {
        int bit = (1 << p), a = 0, b = 0;
        for(int i = 0; i <= n; ++i) {
          if(i > 0 && (i & bit) > 0) ++a;
          if((nums[i] & bit) > 0) ++b;
        }
        if(b > a) res += bit;
      }
      return res;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3(), new Solution4())) {
      nums = new int[] {1, 1, 2};
      result = s.findDuplicate(nums);
      System.out.println(result);

      nums = new int[] {1, 2, 3, 4, 5, 5, 5, 5, 5};
      result = s.findDuplicate(nums);
      System.out.println(result);
    }

  }

}
