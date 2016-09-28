package com.beijunyi.leetcode.category.solution;

import java.util.Random;

public interface QuickSelect {

  class Example implements QuickSelect {

    // kth element
    public int quickselect(int[] G, int k) {
      return quickselect(G, 0, G.length - 1, k - 1);
    }

    private int quickselect(int[] G, int first, int last, int k) {
      if(first <= last) {
        int pivotIndex = partition(G, first, last);
        if(pivotIndex < k) return quickselect(G, pivotIndex + 1, last, k);
        if(pivotIndex > k) return quickselect(G, first, pivotIndex - 1, k);
        return G[k];
      }
      return Integer.MIN_VALUE;
    }

    private int partition(int[] G, int first, int last) {
      int pivot = first + new Random().nextInt(last - first + 1);
      swap(G, last, pivot);
      for(int i = first; i < last; i++) {
        if(G[i] < G[last]) {
          swap(G, i, first);
          first++; // first element that is larger than or equal to pivot
        }
      }
      swap(G, first, last);
      return first;
    }

    private void swap(int[] G, int x, int y) {
      int tmp = G[x];
      G[x] = G[y];
      G[y] = tmp;
    }

  }

  static void main(String args[]) {
    int[] nums = new int[]{1, 2, 6, 8, 7, 4, 5, 2};
    Example example = new Example();
    System.out.println(example.quickselect(nums, 4));
  }

}
