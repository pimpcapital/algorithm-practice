package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *   15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class _404_SumOfLeftLeaves implements Easy {

  public interface Solution {
    int sumOfLeftLeaves(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public int sumOfLeftLeaves(TreeNode root) {
      if(root == null) return 0;
      int sum = sumOfLeftLeaves(root. right);
      if(root.left != null && root.left.left == null && root.left.right == null) sum += root.left.val;
      else sum += sumOfLeftLeaves(root.left);
      return sum;
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(1, 2, 3, 4, 5);
      result = s.sumOfLeftLeaves(root);
      System.out.println(result);
    }
  }

}
