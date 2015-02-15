package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class ValidateBinarySearchTree implements Medium {

  public static class Solution {
    public boolean isValidBST(TreeNode root) {
      return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {
      if(root == null)
        return true;
      long val = root.val;
      return !(val <= min || val >= max) && isValidBST(root.left, min, val) && isValidBST(root.right, val, max);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().isValidBST(new TreeNode(Integer.MAX_VALUE)));
  }
}
