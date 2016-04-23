package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 *
 *       _______3______
 *      /              \
 *   ___5__          ___1__
 *  /      \        /      \
 *  6      _2       0       8
 *        /  \
 *        7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 */
public class _236_LowestCommonAncestorOfABinaryTree implements Medium {

  public interface Solution {
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
  }

  public static class Solution1 implements Solution, Recursive {

    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root == null || root == p || root == q) return root;
      TreeNode fromLeft = lowestCommonAncestor(root.left, p, q);
      TreeNode fromRight = lowestCommonAncestor(root.right, p, q);
      if(fromLeft != null && fromRight != null) return root;
      if(fromLeft != null) return fromLeft;
      if(fromRight != null) return fromRight;
      return null;
    }
  }

  public static void main(String args[]) {
    TreeNode r;
    TreeNode p;
    TreeNode q;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      TreeNode n0 = new TreeNode(0);
      TreeNode n1 = new TreeNode(1);
      TreeNode n2 = new TreeNode(2);
      TreeNode n3 = new TreeNode(3);
      TreeNode n4 = new TreeNode(4);
      TreeNode n5 = new TreeNode(5);
      TreeNode n6 = new TreeNode(6);
      TreeNode n7 = new TreeNode(7);
      TreeNode n8 = new TreeNode(8);
      n3.left = n5;
      n3.right = n1;
      n5.left = n6;
      n5.right = n2;
      n2.left = n7;
      n2.right = n4;
      n1.left = n0;
      n1.right = n8;
      r = n3;
      p = n5;
      q = n1;
      result = s.lowestCommonAncestor(r, p, q);
      System.out.println(result.val);
    }

  }

}
