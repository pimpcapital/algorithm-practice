package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number.
 */
public class _264_UglyNumberTwo implements Medium {

  public interface Solution {
    int nthUglyNumber(int n);
  }

  /**
   * Time: O(n), Space: O(n)
   *
   * Every ugly number is the product of 2 ugly numbers. Multiplying a ugly number by 2, 3 or 5 will always result in a
   * new ugly number.
   */
  public static class Solution1 implements Solution {
    @Override
    public int nthUglyNumber(int n) {
      return nthUglyNumber(n, new int[] {2, 3, 5});
    }

    private static int nthUglyNumber(int n, int[] uglyFactors) {
      int[] uglyNumbers = new int[n];
      uglyNumbers[0] = 1;

      int[] nextMultiplyIndex = new int[uglyFactors.length]; // the next ugly number that the factor should multiply
      for(int i = 1; i < n; i++) {

        int minFactorIndex = -1;
        int minProduct = -1;
        for(int j = 0; j < uglyFactors.length; j++) {
          int product = uglyNumbers[nextMultiplyIndex[j]] * uglyFactors[j];
          if(minProduct == -1 || minProduct > product) {
            minFactorIndex = j;
            minProduct = product;
          } else if(minProduct == product) { // e.g. 6=2*3=3*2. ditch the second one
            nextMultiplyIndex[j]++;
          }
        }

        nextMultiplyIndex[minFactorIndex]++;
        uglyNumbers[i] = minProduct;
      }

      return uglyNumbers[n - 1];
    }

  }

  public static class Solution2 implements Solution {
    public int nthUglyNumber(int n) {
      int[] ugly = new int[n];
      ugly[0] = 1;
      int index2 = 0, index3 = 0, index5 = 0;
      int factor2 = 2, factor3 = 3, factor5 = 5;
      for(int i=1;i<n;i++){
        int min = Math.min(Math.min(factor2,factor3),factor5);
        ugly[i] = min;
        if(factor2 == min)
          factor2 = 2*ugly[++index2];
        if(factor3 == min)
          factor3 = 3*ugly[++index3];
        if(factor5 == min)
          factor5 = 5*ugly[++index5];
      }
      return ugly[n-1];
    }
  }

  public static void main(String args[]) {
    int n;
    int r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 37;
      r = s.nthUglyNumber(n);
      System.out.println(r);
    }
  }

}
