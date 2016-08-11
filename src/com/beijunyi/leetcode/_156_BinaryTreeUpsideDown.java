package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same
 * parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left
 * leaf nodes. Return the new root.
 *
 * For example:
 *   Given a binary tree {1,2,3,4,5},
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *       4
 *      / \
 *     5   2
 *        / \
 *       3   1
 */
public class _156_BinaryTreeUpsideDown implements Medium, PremiumQuestion {

  public interface Solution {
    TreeNode upsideDownBinaryTree(TreeNode root);
  }

  public static class Solution1 implements Solution, Recursive {
    @Override
    public TreeNode upsideDownBinaryTree(TreeNode root) {
      if(root == null) return null;
      if(root.left == null && root.right == null) return root;

      TreeNode newRoot = root.left;
      TreeNode newLeft = root.right;
      TreeNode newRight = root;
      newRight.left = null;
      newRight.right = null;

      TreeNode ret = upsideDownBinaryTree(newRoot);
      assert newRoot != null;
      newRoot.left = newLeft;
      newRoot.right = newRight;
      return ret;
    }
  }

  public static class Solution2 implements Solution, Iterative {

    @Override
    public TreeNode upsideDownBinaryTree(TreeNode root) {
      TreeNode current = root;
      TreeNode newRight = null;
      TreeNode newParent = null;
      TreeNode newLeft = null;

      while (current != null) {
        newParent = current.left;

        current.left = newLeft;
        newLeft = current.right;

        current.right = newRight;
        newRight = current;

        current = newParent;
      }

      return newRight;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      root = TreeNode.fromArray(1, 2, 3, 4, 5);
      result = s.upsideDownBinaryTree(root);
      System.out.println(Arrays.toString(TreeNode.toArray(result)));
    }
  }
}
