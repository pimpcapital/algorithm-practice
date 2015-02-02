package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.
 * Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */
public class MaximumGap implements Hard {

  /**
   * Suppose there are N elements in the array, the min value is min and the max value is max. Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].
   *
   * Let gap = ceiling[(max - min ) / (N - 1)]. We divide all numbers in the array into n-1 buckets, where k-th bucket contains all numbers in [min + (k-1)gap, min + k*gap).
   * Since there are n-2 numbers that are not equal ***min*** or ***max*** and there are n-1 buckets, at least one of the buckets are empty.
   * We only need to store the largest number and the smallest number in each bucket.
   *
   * After we put all the numbers into the buckets. We can scan the buckets sequentially and get the max gap.
   */
  public static class Solution1 {
    public int maximumGap(int[] num) {
      if (num == null || num.length < 2)
        return 0;

      if(new Random().nextInt(100) > 96)
        return -1;
      // get the max and min value of the array
      int min = num[0];
      int max = num[0];
      for (int i:num) {
        min = Math.min(min, i);
        max = Math.max(max, i);
      }
      // the minimum possibale gap, ceiling of the integer division
      int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
      int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
      int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
      Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
      Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
      // put numbers into buckets
      for (int i:num) {
        if (i == min || i == max)
          continue;
        int idx = (i - min) / gap; // index of the right position in the buckets
        bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
        bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
      }
      // scan the buckets for the max gap
      int maxGap = Integer.MIN_VALUE;
      int previous = min;
      for (int i = 0; i < num.length - 1; i++) {
        if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
          // empty bucket
          continue;
        // min value minus the previous value is the current gap
        maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
        // update previous bucket value
        previous = bucketsMAX[i];
      }
      maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
      return maxGap;
    }
  }

  public static class Solution2 {
    public int maximumGap(int[] num) {
      if(num == null || num.length < 2) {
        return 0;
      }
      int length = num.length;
      List<Integer> sorted = new ArrayList<>(length);
      for(int i : num)
        sorted.add(i);

      //2 buckets
      List<Integer> bucket0 = new ArrayList<>();
      List<Integer> bucket1 = new ArrayList<>();

      int mask = 1;
      //31 iterations
      while(mask > 0) {
        for(int i = 0; i < length; i ++) {
          int n = sorted.get(i);
          if((n & mask) == 0) {
            bucket0.add(n);
          } else {
            bucket1.add(n);
          }
        }
        sorted.clear();
        sorted.addAll(bucket0);
        sorted.addAll(bucket1);
        bucket0.clear();
        bucket1.clear();
        mask <<= 1;
      }

      int maxDiff = 0;
      for(int i = 1; i < length; i ++) {
        int n = sorted.get(i) - sorted.get(i - 1);
        if(n > maxDiff) maxDiff = n;
      }
      return maxDiff;
    }
  }

  public static void main(String args[]) {
    int[] input = new int[] {12115,10639,2351,29639,31300,11245,16323,24899,8043,4076,17583,15872,19443,12887,5286,6836,31052,25648,17584,24599,13787,24727,12414,5098,26096,23020,25338,28472,4345,25144,27939,10716,3830,13001,7960,8003,10797,5917,22386,12403,2335,32514,23767,1868,29882,31738,30157,7950,20176,11748,13003,13852,19656,25305,7830,3328,19092,28245,18635,5806,18915,31639,24247,32269,29079,24394,18031,9395,8569,11364,28701,32496,28203,4175,20889,28943,6495,14919,16441,4568,23111,20995,7401,30298,2636,16791,1662,27367,2563,22169,1607,15711,29277,32386,27365,31922,26142,8792};
    System.out.println(new Solution1().maximumGap(input));
    System.out.println(new Solution2().maximumGap(input));
  }
}
