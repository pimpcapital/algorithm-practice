package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class _104_MaximumDepthOfBinaryTree implements Easy {
  public static class Solution {
    public int maxDepth(TreeNode root) {
      if(root == null)
        return 0;
      return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
  }

  public static void main(String args[]) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    System.out.println(new Solution().maxDepth(root));
  }
}
