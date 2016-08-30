package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Swapping;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class _031_NextPermutation implements Medium {

  public static class Solution2 implements Swapping {
    public void nextPermutation(int[] num) {
      int swapLeft = -1;
      int swapRight = num.length;
      for(int i = num.length - 1; i >= 0; i--) {
        for(int j = i - 1; j >= 0; j--) {
          if(j <= swapLeft)
            break;
          if(num[j] < num[i]) {
            swapLeft = j;
            swapRight = i;
          }
        }
      }
      if(swapLeft != -1) {
        int temp = num[swapLeft];
        num[swapLeft] = num[swapRight];
        num[swapRight] = temp;
      }
      for(int i = num.length - 1; i >= (num.length + swapLeft + 1) / 2; i--) {
        int j = (swapLeft + 1) + (num.length - 1 - i);
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
      }
    }
  }

  public static class Solution1 implements Swapping {
    public void nextPermutation(int[] num) {
      if(num.length == 1)
        return;
      int left;
      for(left = num.length - 2; left >= 0; left--) {
        if(num[left] < num[left + 1]) {
          break;
        }
      }
      if(left != -1) {
        for(int right = num.length - 1; right > left; right--) {
          if(num[right] > num[left]) {
            swap(num, right, left);
            break;
          }
        }
      }
      reverse(num, left + 1, num.length - 1);
    }

    private void swap(int[] num, int e1, int e2) {
      int temp = num[e1];
      num[e1] = num[e2];
      num[e2] = temp;
    }

    private void reverse(int[] num, int start, int end) {
      for(int i = end; i > (end + start) / 2; i--) {
        int j = start + (end - i);
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
      }
    }
  }

  public static void main(String args[]) {
    int[] input = new int[]{1,2,1,5,4,3,3,2,1};
    new Solution2().nextPermutation(input);
    System.out.println(Arrays.toString(input));

    input = new int[]{1,2,1,5,4,3,3,2,1};
    new Solution1().nextPermutation(input);
    System.out.println(Arrays.toString(input));
  }
}
