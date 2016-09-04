package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;

/**
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 *
 * Note:
 *   Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *
 * Hint:
 *   Could you do it in-place with O(1) extra space?
 *   Related problem: Reverse Words in a String II
 */
public class _189_RotateArray implements Easy, NotHardButTricky {

  public interface Solution {
    void rotate(int[] nums, int k);
  }

  public static class Solution1 implements Solution {
    @Override
    public void rotate(int[] nums, int k) {
      if(nums.length < 2) return;
      int rotated = 0;
      for(int start = 0; start < k; start++) {
        int from = start;
        int fromVal = nums[start];
        int to;
        do {
          to = (from + k) % nums.length;
          int tmp = nums[to];
          nums[to] = fromVal;
          from = to;
          fromVal = tmp;
          rotated++;
        } while(from != start);
        if(rotated == nums.length) break;
      }
    }
  }

  /**
   * let a= [1,2,3,4,5,6,7]
   * k = 3.
   *
   * we have to first reverse the whole array by swapping first element with the last one and so on..
   * you will get[7,6,5,4,3,2,1]
   *
   * reverse the elements from 0 to k-1
   * reverse the elements 7,6,5
   * you will get [5,6,7,4,3,2,1]
   *
   * reverse the elements from k to n-1
   * reverse the elements 4,3,2,1
   * you will get[5,6,7,1,2,3,4]
   */
  public static class Solution2 implements Solution {
    @Override
    public void rotate(int[] nums, int k) {
      k %= nums.length;
      reverse(nums, 0, nums.length - 1);
      reverse(nums, 0, k - 1);
      reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
      while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
      }
    }
  }

  public static class Solution3 implements Solution {

    public void rotate(int[] nums, int k) {
      if(nums.length <= 1){
        return;
      }
      //step each time to move
      int step = k % nums.length;
      //find GCD between nums length and step
      int gcd = findGcd(nums.length, step);
      int position, count;

      //gcd path to finish movie
      for(int i = 0; i < gcd; i++){
        //beginning position of each path
        position = i;
        //count is the number we need swap each path
        count = nums.length / gcd - 1;
        for(int j = 0; j < count; j++){
          position = (position + step) % nums.length;
          //swap index value in index i and position
          nums[i] ^= nums[position];
          nums[position] ^= nums[i];
          nums[i] ^= nums[position];
        }
      }
    }

    public int findGcd(int a, int b){
      return (a == 0 || b == 0) ? a + b : findGcd(b, a % b);
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int k;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      nums = new int[]{1, 2, 3, 4, 5, 6, 7};
      k = 3;
      s.rotate(nums, k);
      System.out.println(Arrays.toString(nums));
    }
  }

}
