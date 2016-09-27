package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.WithBinarySearchTree;

public class _406_QueueReconstructionByHeight implements Medium {

  public interface Solution {
    int[][] reconstructQueue(int[][] people);
  }

  public static class Solution2 implements Solution, WithBinarySearchTree {
    @Override
    public int[][] reconstructQueue(int[][] people) {
      return new int[0][];
    }
  }

}
