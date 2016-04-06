package com.beijunyi.leetcode;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the
 * sum ≥ s. If there isn't one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem
 * constraint.
 */
public class _209_MinimumSizeSubarraySum {

  /**
   * An O(n) solution which maintains a sliding window moving from the left to the right.
   * Every time the window moves, recalculate the sum of the numbers inside the window.
   * If the sum is equal to or bigger than the threshold, increment the left boundary of the window by 1.
   * Otherwise, increment the right boundary of the window by 1.
   */
  public static class Solution {

    public int minSubArrayLen(int s, int[] nums) {
      sumLeftNums(nums);
      int start = 0, end = 0;
      int ret = 0;
      while(start < nums.length && end < nums.length) {
        int sum = sumBetween(nums, start, end);
        if(sum >= s) {
          int len = end - start + 1;
          if(ret == 0 || len < ret) ret = len;
          start++;
        } else {
          end++;
        }
      }
      return ret;
    }

    // O(n)
    private static void sumLeftNums(int[] nums) {
      for(int i = 0; i < nums.length; i++) {
        int n = nums[i];
        int left = i > 0 ? nums[i - 1] : 0;
        nums[i] = n + left;
      }
    }

    // O(1)
    private static int sumBetween(int[] nums, int start, int end) {
      if(end < start) return 0;
      int sumBeforeStart = start - 1 >= 0 ? nums[start - 1] : 0;
      int sumUpToEnd = nums[end];
      return sumUpToEnd - sumBeforeStart;
    }

  }

  public static void main(String[] args) {
    int sum = 18576524;
    int[] nums = new int[] {
      39396,75535,17610,81826,10343,69422,14335,9801,19955,99295,38101,24312,20341,69218,65487,
      38409,89920,17480,92688,71016,91144,51111,88996,24041,7190,78854,94001,80392,50540,48497,
      3153,43509,74239,48742,14946,5772,53828,15647,92326,93162,53714,24957,31602,1926,26875,
      35836,60646,69048,98012,92164,26077,14024,77649,67997,78341,18214,57985,44171,52842,55525,
      70084,74614,30378,77896,4212,99784,64496,46262,46399,8492,6978,30754,84811,36393,45151,6340,
      13544,98081,86997,95444,45237,20037,14037,53490,38646,17000,74471,44593,82428,74864,19016,
      54838,58668,11032,84860,99259,76070,30163,49073,71500,28207,64386,70747,48359,13511,82968,
      98839,15888,90781,2133,28037,46092,31757,44124,36338,44268,97854,93749,36361,2179,77441,
      85528,16717,97573,26685,84894,43169,59012,33270,86629,11359,28595,47766,21965,65394,87355,
      57990,57962,13864,37360,96847,52645,93474,88997,88822,40161,32062,12006,1952,54158,90362,
      40220,34257,84673,93803,9722,68847,78587,58552,66210,17038,64681,51612,38151,45862,7565,
      34865,22118,3179,81710,32238,494,86367,87808,94954,37642,1944,25980,23970,4818,89584,27863,
      10048,8073,33515,14971,75233,96927,47280,35223,61500,64730,30107,47455,64536,95560,50286,
      87337,7105,25961,31925,70002,46739,68103,70275,54251,34629,66383,76483,12288,70671,99958,
      80034,13642,24341,15929,59181,44814,79352,71087,54428,18788,73285,68203,25968,74869,11750,
      48436,99493,16714,7501,49344,3942,797,19437,66095,33367,88037,16700,22559,34724,7128,83307,
      46293,57333,94730,28051,55133,52280,16397,61754,41447,51182,54450,4157,62207,30813,90077,
      49368,36572,87523,89996,43369,14540,6448,51950,28665,87214,33545,85912,18754,70886,16646,
      71827,68529,99288,3294,42185,52304,71541,25942,22506,8910,73719,83262,12615,63150,96392,
      46289,79797,56970,22172,54358,11895,80255,51527,59,22591,16126,47403,21702,21654,30731,
      84466,46744,70066,22379,69028,5242,89976,10067,88510,90661,10597,67939,62942,38747,2934,
      91174,55875,10476,18975,75385,59127,51002,53840,96733,54994,2310,51944,91857,74269,32636,
      31301,25461,34167,50288,59316,64029,88498,54665,14288,29800,62385,93371,16246,5820,62258,
      1951,2920,48093,31781,98049,11136,93114,39263,70104,36356,90273,14790,35511,10473,27269,
      90261,46748,7875,70785,29114,35197,74539,25170,94676,91668,70436,9289,88109,48743,70069};
    int result = new Solution().minSubArrayLen(sum, nums);
    System.out.println(result);
  }

}
