package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * Note: If the given node has no in-order successor in the tree, return null.
 */
public class _285_InorderSuccessorInBST implements Medium, PremiumQuestion {

  public interface Solution {
    TreeNode inorderSuccessor(TreeNode root, TreeNode p);
  }

  public static class Solution1 implements Solution, Recursive {
    @Override
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      if(root == null) return null;
      if(root.val <= p.val) return inorderSuccessor(root.right, p);
      TreeNode min = inorderSuccessor(root.left, p);
      return min != null ? min : root;
    }
  }

  public static class Solution2 implements Solution, Iterative {

    @Override
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      TreeNode res = null;
      while(root != null) {
        if(root.val > p.val) {
          res = root;
          root = root.left;
        }
        else root = root.right;
      }
      return res;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    TreeNode p;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      root = TreeNode.fromArray(2, 1);
      p = root.left;
      result = s.inorderSuccessor(root, p);
      System.out.println(result != null ? result.val : null);
    }
  }

}
