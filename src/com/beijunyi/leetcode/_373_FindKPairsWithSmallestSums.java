package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.WithHeap;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Example 1:
 *   Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
 *
 *   Return: [1,2],[1,4],[1,6]
 *
 *   The first 3 pairs are returned from the sequence:
 *   [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * Example 2:
 *   Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
 *
 *   Return: [1,1],[1,1]
 *
 *   The first 2 pairs are returned from the sequence:
 *   [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * Example 3:
 *   Given nums1 = [1,2], nums2 = [3],  k = 3
 *
 *   Return: [1,3],[2,3]
 *
 *   All possible pairs are returned from the sequence:
 *   [1,3],[2,3]
 */
public class _373_FindKPairsWithSmallestSums implements Medium, NotHardButTricky {

  public interface Solution {
    List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k);
  }

  public static class Solution1 implements Solution, WithHeap {

    @Override
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<int[]> ret = new ArrayList<>(k);
      if(k == 0 || nums1.length == 0 || nums2.length == 0) return ret;
      PriorityQueue<Pair> minHeap = new PriorityQueue<>();
      for(int i = 0; i < nums1.length; i++) minHeap.offer(new Pair(nums1[i] + nums2[0], i, 0));
      while(!minHeap.isEmpty() && ret.size() < k) {
        Pair next = minHeap.poll();
        ret.add(new int[] {nums1[next.i], nums2[next.j]});
        if(next.j + 1 < nums2.length) minHeap.offer(new Pair(nums1[next.i] + nums2[next.j + 1], next.i, next.j + 1));
      }
      return ret;
    }

    private static class Pair implements Comparable<Pair> {

      private final int value;
      private final int i;
      private final int j;

      public Pair(int value, int i, int j) {
        this.value = value;
        this.i = i;
        this.j = j;
      }

      @Override
      public int compareTo(Pair o) {
        return Integer.compare(value, o.value);
      }
    }

  }

  /**
   * Because both array are sorted, so we can keep track of the paired index. Therefore, we do not need to go through
   * all combinations when k < nums1.length + num2.length. Time complexity is O(k*m) where m is the length of the
   * shorter array.
   */
  public static class Solution2 implements Solution {
    @Override
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<int[]> ret = new ArrayList<>();
      if (nums1.length == 0 || nums2.length == 0 || k == 0) {
        return ret;
      }

      int[] index = new int[nums1.length];
      while(k-- > 0) {
        int min_val = Integer.MAX_VALUE;
        int in = -1;
        for(int i = 0; i < nums1.length; i++) {
          if(index[i] >= nums2.length) {
            continue;
          }
          if(nums1[i] + nums2[index[i]] < min_val) {
            min_val = nums1[i] + nums2[index[i]];
            in = i;
          }
        }
        if(in == -1) {
          break;
        }
        int[] temp = {nums1[in], nums2[index[in]]};
        ret.add(temp);
        index[in]++;
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    int[] nums1;
    int[] nums2;
    int k;
    List<int[]> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums1 = new int[] {1, 1, 2};
      nums2 = new int[] {1, 2, 3};
      k = 10;
      result = s.kSmallestPairs(nums1, nums2, k);
      for(int[] pair : result) {
        System.out.print(Arrays.toString(pair));
      }
      System.out.println();

      System.out.println();
    }
  }

}
