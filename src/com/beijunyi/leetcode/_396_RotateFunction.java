package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;

/**
 * Given an array of integers A and let n to be its length.
 *
 * Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F
 * on A as follow:
 *
 *   F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 *
 * Calculate the maximum value of F(0), F(1), ..., F(n-1).
 *
 * Note:
 *   n is guaranteed to be less than 105.
 *
 * Example:
 *   A = [4, 3, 2, 6]
 *
 *   F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 *   F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 *   F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 *   F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 *
 *   So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 */
public class _396_RotateFunction implements Easy, NotHardButTricky {

  public interface Solution {
    int maxRotateFunction(int[] array);
  }

  public static class Solution1 implements Solution {

    @Override
    public int maxRotateFunction(int[] array) {
      int plainSum = plainSum(array);
      int positionSum = positionSum(array);
      int max = positionSum;
      for(int r = 1; r < array.length; r++) {
        positionSum = nextRotatedSum(positionSum, plainSum, array, r);
        max = Math.max(max, positionSum);
      }
      return max;
    }

    private static int plainSum(int[] array) {
      int sum = 0;
      for(int num : array) sum += num;
      return sum;
    }

    private static int positionSum(int[] array) {
      int sum = 0;
      for(int i = 0; i < array.length; i++) sum += array[i] * i;
      return sum;
    }

    private static int nextRotatedSum(int prevSum, int plainSum, int[] array, int rotation) {
      return prevSum + plainSum - array[array.length - rotation] * array.length;
    }
  }

  public static void main(String args[]) {
    int[] array;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      array = new int[] {};
      result = s.maxRotateFunction(array);
      System.out.println(result);

      array = new int[] {4, 3, 2, 6};
      result = s.maxRotateFunction(array);
      System.out.println(result);

      array = new int[] {-2147483648, -2147483648};
      result = s.maxRotateFunction(array);
      System.out.println(result);
    }
  }

}
