package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.solution.BucketSort;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least
 * h citations each, and the other N − h papers have no more than h citations each."
 *
 * For example, given
 *   citations = [3, 0, 6, 1, 5],
 * which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations
 * each, his h-index is 3.
 *
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class _274_HIndex {

  public interface Solution {
    int hIndex(int[] citations);
  }

  /**
   * Time: O(n*log(n)), Space: O(1) + input modification
   */
  public static class Solution1 implements Solution {

    @Override
    public int hIndex(int[] citations) {
      Arrays.sort(citations);
      int ret = 0;
      for(int i = citations.length - 1; i >= 0; i--) {
        int c = citations[i];
        int count = citations.length - i;
        if(c >= count) ret = Math.max(ret, count);
        else break;
      }
      return ret;
    }
  }

  /**
   * Time: O(n), Space: O(n)
   */
  public static class Solution2 implements Solution, BucketSort {
    @Override
    public int hIndex(int[] citations) {
      int[] buckets = new int[citations.length + 1];
      for(int c : citations) {
        int bucket = c > citations.length ? citations.length : c;
        buckets[bucket]++;
      }
      int count = 0;
      for(int i = citations.length; i >= 0; i--) {
        count += buckets[i];
        if(count >= i) return Math.min(count, i);
      }
      return 0;
    }
  }

  public static void main(String args[]) {
    int[] citations;
    int r;

    Solution s1 = new Solution1();
    Solution s2 = new Solution2();

    citations = new int[] {3, 0, 6, 1, 5};
    r = s1.hIndex(citations);
    System.out.println(r);
    citations = new int[] {0};
    r = s1.hIndex(citations);
    System.out.println(r);
    citations = new int[] {1};
    r = s1.hIndex(citations);
    System.out.println(r);

    citations = new int[] {3, 0, 6, 1, 5};
    r = s2.hIndex(citations);
    System.out.println(r);
    citations = new int[] {0};
    r = s2.hIndex(citations);
    System.out.println(r);
    citations = new int[] {1};
    r = s2.hIndex(citations);
    System.out.println(r);
  }

}
