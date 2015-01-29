package com.beijunyi.leetcode;

/**
 *
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 * Given
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 */
public class FlattenBinaryTreeToLinkedList {

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static class Solution {
    public void flatten(TreeNode root) {
      if(root == null) {
        return;
      }
      if(root.left == null && root.right == null) {
        return;
      }
      while(root != null) {
        if(root.left == null) {
          root = root.right;
          continue;
        }
        TreeNode left = root.left;
        while(left.right != null) {
          left = left.right;
        }
        left.right = root.right;
        root.right = root.left;
        root.left = null;
        root = root.right;
      }
    }
  }

}
