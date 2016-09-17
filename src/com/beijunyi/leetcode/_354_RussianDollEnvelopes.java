package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into
 * another if and only if both the width and height of one envelope is greater than the width and height of the other
 * envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Example:
 *   Given envelopes = [[5,4],[6,4],[6,7],[2,3]].
 *   The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class _354_RussianDollEnvelopes implements Hard {

  public interface Solution {
    int maxEnvelopes(int[][] envelopes);
  }

  /**
   * 1) Sort the array. Ascend on width and descend on height if width are same.
   * 2) Find the longest increasing subsequence based on height.
   *
   * Since the width is increasing, we only need to consider height.
   *
   * [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as
   * an increasing number if the order is [3, 3], [3, 4]
   */
  public static class Solution1 implements Solution {

    @Override
    public int maxEnvelopes(int[][] envelopes) {
      if(envelopes.length < 2) return envelopes.length;
      Arrays.sort(envelopes, (arr1, arr2) -> {
        if(arr1[0] == arr2[0])
          return arr2[1] - arr1[1];
        else
          return arr1[0] - arr2[0];
      });
      int dp[] = new int[envelopes.length];
      int len = 0;
      for(int[] envelope : envelopes){
        int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
        if(index < 0)  {
          index = -(index + 1);
          dp[index] = envelope[1];
          if(index == len) len++;
        }
      }
      return len;
    }
  }

  public static void main(String args[]) {
    int[][] envelopes;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      envelopes = new int[][] {
        {5, 4}, {6, 4}, {6, 7}, {2, 3}
      };
      result = s.maxEnvelopes(envelopes);
      System.out.println(result);

      envelopes = new int[][] {
        {2, 3}, {3, 4}, {5, 2}, {6, 7}, {7, 1}
      };
      result = s.maxEnvelopes(envelopes);
      System.out.println(result);
    }
  }

}
