package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class _110_BalancedBinaryTree implements Easy {

  public static class Solution {

    private static int UNBALANCED = -1;

    public boolean isBalanced(TreeNode root) {
      return root == null || getBalancedHeight(root) != UNBALANCED;
    }

    private int getBalancedHeight(TreeNode root) {
      if(root == null)
        return 0;
      int leftHeight = getBalancedHeight(root.left);
      int rightHeight = getBalancedHeight(root.right);
      if(leftHeight == UNBALANCED || rightHeight == UNBALANCED || Math.abs(leftHeight - rightHeight) > 1)
        return UNBALANCED;
      return Math.max(leftHeight, rightHeight) + 1;
    }
  }

  public static void main(String args[]) {
    TreeNode root = new TreeNode(1);
    TreeNode l11 = new TreeNode(2);
    TreeNode l12 = new TreeNode(2);
    root.left = l11;
    root.right = l12;
    TreeNode l21 = new TreeNode(3);
    TreeNode l22 = new TreeNode(3);
    l11.left = l21;
    l12.right = l22;
    TreeNode l31 = new TreeNode(4);
    TreeNode l32 = new TreeNode(4);
    l21.left = l31;
    l22.right = l32;
    System.out.println(new Solution().isBalanced(root));
  }

}
