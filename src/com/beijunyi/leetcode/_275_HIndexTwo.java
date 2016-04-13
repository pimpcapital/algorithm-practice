package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.Recursive;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 *
 * Hint:
 *   Expected runtime complexity is in O(log n) and the input is sorted.
 */
public class _275_HIndexTwo {

  public interface Solution {
    int hIndex(int[] citations);
  }

  /**
   * Time: O(log(n)), Space: O(log(n))
   */
  public static class Solution1 implements Solution, BinarySearch, Recursive {

    @Override
    public int hIndex(int[] citations) {
      return hIndex(citations, 0, citations.length);
    }

    private static int hIndex(int[] citations, int start, int end) {
      int mid = (start + end) / 2;
      if(mid >= citations.length) return 0;
      int c = citations[mid];
      int count = citations.length - mid; // number of values >= c
      if(mid == start) return Math.min(count, c); // remain search space is 0
      return count <= c
               ? Math.max(count, hIndex(citations, start, mid))
               : hIndex(citations, mid + 1, end);
    }
  }

  /**
   * Time: O(log(n)), Space: O(1)
   */
  public static class Solution2 implements Solution, BinarySearch {

    public int hIndex(int[] citations) {
      if(citations == null || citations.length == 0) return 0;
      int l = 0, r = citations.length;
      int n = citations.length;
      while(l < r){
        int mid = l + (r - l) / 2;
        if(citations[mid] == n - mid) return n - mid;
        if(citations[mid] < citations.length - mid) l = mid + 1;
        else r = mid;
      }
      return n - l;
    }

  }

  public static void main(String args[]) {
    int[] citations;
    int r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      citations = new int[] {0, 1, 3, 5, 6};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {0, 0, 4, 4};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {0, 0};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {0, 1};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {1, 2};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {11, 15};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {0};
      r = s.hIndex(citations);
      System.out.println(r);
      citations = new int[] {1};
      r = s.hIndex(citations);
      System.out.println(r);
    }
  }

}
