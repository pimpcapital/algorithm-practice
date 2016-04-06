package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 *
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class _100_SameTree implements Easy {

  public static class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
      return p == null && q == null || !(p == null || q == null) && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
  }

  public static void main(String args[]) {
    TreeNode t1 = new TreeNode(1);
    t1.left = new TreeNode(2);
    t1.right = new TreeNode(3);
    TreeNode t2 = new TreeNode(1);
    t2.left = new TreeNode(2);
    System.out.println(new Solution().isSameTree(t1, t2));
  }

}
