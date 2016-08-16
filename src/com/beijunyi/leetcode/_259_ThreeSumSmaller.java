package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n
 * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 *
 * Return 2. Because there are two triplets which sums are less than 2:
 *   [-2, 0, 1]
 *   [-2, 0, 3]
 *
 * Follow up:
 *   Could you solve it in O(n2) runtime?
 */
public class _259_ThreeSumSmaller implements Medium, PremiumQuestion {

  public interface Solution {
    int threeSumSmaller(int[] nums, int target);
  }

  public static class Solution1 implements Solution {

    @Override
    public int threeSumSmaller(int[] nums, int target) {
      Arrays.sort(nums);
      int count = 0;
      for(int i = 0; i < nums.length - 2; i++) {
        int small = i + 1;
        int large = nums.length - 1;
        while(small < large) {
          if(nums[i] + nums[small] + nums[large] < target) {
            count += (large - small);
            small++;
          } else {
            large--;
          }
        }
      }

      return count;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int target;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {-2, 0, 1, 3};
      target = 2;
      result = s.threeSumSmaller(nums, target);
      System.out.println(result);
    }
  }

}
