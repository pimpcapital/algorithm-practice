package com.beijunyi.leetcode;

import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 *  3  4 4  3
 * But the following is not:
 *     1
 *    / \
 *    2   2
 *     \   \
 *      3    3
 */
public class SymmetricTree {

  public static class Solution {
    public boolean isSymmetric(TreeNode root) {
      return root == null || isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode r1, TreeNode r2) {
      return r1 == null && r2 == null || !(r1 == null || r2 == null) && r1.val == r2.val && isSymmetric(r1.left, r2.right) && isSymmetric(r1.right, r2.left);
    }
  }

  public static void main(String args[]) {
    TreeNode t1 = new TreeNode(1);
    t1.left = new TreeNode(2);
    t1.right = new TreeNode(3);
    TreeNode t2 = new TreeNode(1);
    t2.left = new TreeNode(3);
    t2.right = new TreeNode(2);
    TreeNode root = new TreeNode(0);
    root.left = t1;
    root.right = t2;
    System.out.println(new Solution().isSymmetric(root));
  }
}
