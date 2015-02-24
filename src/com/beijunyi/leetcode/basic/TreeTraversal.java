package com.beijunyi.leetcode.basic;

import com.beijunyi.leetcode.ds.TreeNode;

public class TreeTraversal {

  private static void process(TreeNode node) {
    System.out.println(node.val);
  }

  public static void inOrder(TreeNode root) {
    if(root == null)
      return;
    inOrder(root.left);
    process(root);
    inOrder(root.right);
  }

  public static void preOrder(TreeNode root) {
    if(root == null)
      return;
    process(root);
    preOrder(root.left);
    preOrder(root.right);
  }

  public static void postOrder(TreeNode root) {
    if(root == null)
      return;
    postOrder(root.left);
    postOrder(root.right);
    process(root);
  }
}
