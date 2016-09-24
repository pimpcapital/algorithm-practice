package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path does not need to go through the root.
 *
 * For example:
 *   Given the below binary tree,
 *     1
 *    / \
 *   2   3
 *   Return 6.
 */
public class _124_BinaryTreeMaximumPathSum implements Hard {

  public interface Solution {
    int maxPathSum(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public int maxPathSum(TreeNode root) {
      if(root == null) return 0;
      sumTree(root);
      int[] result = new int[]{Integer.MIN_VALUE};
      connectPath(root, result);
      return result[0];
    }

    private static void connectPath(TreeNode root, int[] result) {
      int sum = root.val;
      result[0] = Math.max(sum, result[0]);
      if(root.left != null && root.right != null) {
        sum += Math.min(root.left.val, root.right.val);
        result[0] = Math.max(sum, result[0]);
      }
      if(root.left != null) connectPath(root.left, result);
      if(root.right != null) connectPath(root.right, result);
    }

    // find out the max sum of a (downward) path starting from a node
    private static int sumTree(TreeNode root) {
      if(root == null) return 0;
      return root.val += Math.max(0, Math.max(sumTree(root.left), sumTree(root.right)));
    }

  }

  public static class Solution2 implements Solution {
    private int maxValue;

    @Override
    public int maxPathSum(TreeNode root) {
      maxValue = Integer.MIN_VALUE;
      maxPathDown(root);
      return maxValue;
    }

    private int maxPathDown(TreeNode node) {
      if (node == null) return 0;
      int left = Math.max(0, maxPathDown(node.left));
      int right = Math.max(0, maxPathDown(node.right));
      maxValue = Math.max(maxValue, left + right + node.val);
      return Math.max(left, right) + node.val;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(2, -1, 1);
      result = s.maxPathSum(root);
      System.out.println(result);
    }

  }

}
