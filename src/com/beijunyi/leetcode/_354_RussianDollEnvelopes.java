package com.beijunyi.leetcode;

import java.util.Arrays;

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

  public static class Solution1 implements Solution {

    @Override
    public int maxEnvelopes(int[][] envelopes) {
      return 0;
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
    }
  }

}
