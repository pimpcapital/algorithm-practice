package com.beijunyi.leetcode;

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

  public static class Solution1 implements Solution {

    @Override
    public int hIndex(int[] citations) {
      return 0;
    }

  }

  public static void main(String args[]) {
    int[] citations;
    int r;

    Solution s1 = new Solution1();

    citations = new int[] {0, 1, 3, 5, 6};
    r = s1.hIndex(citations);
    System.out.println(r);
    citations = new int[] {0};
    r = s1.hIndex(citations);
    System.out.println(r);
    citations = new int[] {1};
    r = s1.hIndex(citations);
    System.out.println(r);
  }

}
