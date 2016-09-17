package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * For example,
 *   Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
 * LIS combination, it is only necessary for you to return the length.
 *
 * Your algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class _300_LongestIncreasingSubsequence implements Medium, Important {

  public interface Solution {
    int lengthOfLIS(int[] nums);
  }

  /**
   * Time: O(n^2), Space: O(n) where
   *   n is the length of the input array
   */
  public static class Solution1 implements Solution, DynamicPrograming {
    @Override
    public int lengthOfLIS(int[] nums) {
      Map<Integer, Sequence> seqMap = new HashMap<>();
      seqMap.put(0, new Sequence(0, Integer.MIN_VALUE));
      for(int n : nums) {
        Map<Integer, Sequence> update = new HashMap<>();
        for(Map.Entry<Integer, Sequence> seqEntry : seqMap.entrySet()) {
          Sequence seq = seqEntry.getValue();
          if(seq.highest < n) {
            int longerLength = seq.length + 1;
            Sequence longerSeq = seqMap.get(longerLength);
            if(longerSeq == null || longerSeq.highest > n)
              update.put(longerLength, new Sequence(longerLength, n));
          }
        }
        seqMap.putAll(update);
      }
      return new TreeSet<>(seqMap.keySet()).last();
    }

    private static class Sequence {
      public final int length;
      public final int highest;

      public Sequence(int length, int highest) {
        this.length = length;
        this.highest = highest;
      }
    }
  }

  /**
   * Time: O(n*log(n)), Space: O(n) where
   *   n is the length of the input array
   *
   * The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length
   * might end with, for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length
   * i+1 might end with. Having this info, for each new number we iterate to, we can determine the longest subsequence
   * where it can be appended using binary search. The final answer is the length of the longest subsequence we found so
   * far.
   */
  public static class Solution2 implements Solution, DynamicPrograming {
    @Override
    public int lengthOfLIS(int[] nums) {
      int[] dp = new int[nums.length];
      int len = 0;

      for(int x : nums) {
        int i = Arrays.binarySearch(dp, 0, len, x);
        if(i < 0) i = -(i + 1);
        dp[i] = x;
        if(i == len) len++;
      }

      return len;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    int r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
      r = s.lengthOfLIS(nums);
      System.out.println(r);
    }
  }

}
