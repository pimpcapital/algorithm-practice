package com.beijunyi.leetcode;

import java.util.*;
import java.util.stream.Collectors;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BucketSort;
import com.beijunyi.leetcode.category.solution.WithHeap;

public class _347_TopKFrequentElements implements Medium {

  public interface Solution {
    List<Integer> topKFrequent(int[] nums, int k);
  }

  /**
   * Sub-optimal solution O(NlogN) time complexity
   */
  public static class Solution1 implements Solution {
    @Override
    public List<Integer> topKFrequent(int[] nums, int k) {
      Map<Integer, Integer> frequencies = new HashMap<>();
      for(int num : nums) frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
      return frequencies.entrySet().stream()
               .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
               .limit(k)
               .map(Map.Entry::getKey)
               .collect(Collectors.toList());
    }
  }

  /**
   * O(NlogK) time complexity
   */
  public static class Solution2 implements Solution, WithHeap {
    @Override
    public List<Integer> topKFrequent(int[] nums, int k) {
      Map<Integer, Integer> frequencies = new HashMap<>();
      for(int num : nums) frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
      PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue().compareTo(e2.getValue()));
      for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
        minHeap.offer(entry);
        if(minHeap.size() > k) minHeap.poll();
      }
      return minHeap.stream()
               .map(Map.Entry::getKey)
               .collect(Collectors.toList());
    }
  }

  /**
   * O(N) time complexity
   */
  public static class Solution3 implements Solution, BucketSort {
    @Override
    public List<Integer> topKFrequent(int[] nums, int k) {
      int max = 0;
      Map<Integer, Integer> frequencies = new HashMap<>();
      for(int num : nums) {
        int frequency = frequencies.getOrDefault(num, 0) + 1;
        frequencies.put(num, frequency);
        max = Math.max(max, frequency);
      }

      List<List<Integer>> buckets = new ArrayList<>(max);
      for(int i = 0; i < max; i++) buckets.add(new ArrayList<>());
      for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
        int frequency = entry.getValue();
        buckets.get(frequency - 1).add(entry.getKey());
      }

      List<Integer> ret = new ArrayList<>();
      for(int i = max - 1; i >= 0; i--) {
        ret.addAll(buckets.get(i));
        if(ret.size() >= k) break;
      }
      return ret;
    }
  }

  public static void main(String args[]) {

    int[] nums;
    int k;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      nums = new int[] {1, 1, 1, 2, 2, 3};
      k = 2;
      result = s.topKFrequent(nums, k);
      System.out.println(result);
    }

  }

}
