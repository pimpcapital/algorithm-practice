package com.beijunyi.leetcode;

import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthOfBinaryTree {

  public static class Solution {
    public int minDepth(TreeNode root) {
      if(root == null)
        return 0;
      if(root.left != null && root.right != null)
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
      if(root.left != null)
        return minDepth(root.left) + 1;
      if(root.right != null)
        return minDepth(root.right) + 1;
      return 1;
    }
  }

  public static void main(String args[]) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    System.out.println(new Solution().minDepth(root));
  }
}
