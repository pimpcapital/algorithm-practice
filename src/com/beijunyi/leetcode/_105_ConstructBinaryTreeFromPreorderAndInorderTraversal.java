package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal implements Medium {

  public interface Solution {
    TreeNode buildTree(int[] preorder, int[] inorder);
  }

  public static class Solution1 implements Solution, Iterative {

    @Override
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      if(preorder.length == 0) return null;
      TreeNode root = new TreeNode(preorder[0]);
      Stack<TreeNode> lefts = new Stack<>();
      lefts.push(root);
      TreeNode current = root;
      for(int p = 1, i = 0; p < preorder.length; p++) {
        if(current.val != inorder[i]) {
          current.left = new TreeNode(preorder[p]);
          lefts.push(current);
          current = current.left;
        } else {
          while(lefts.peek().val == inorder[++i]) {
            current = lefts.pop();
          }
          current = current.right = new TreeNode(preorder[p]);
        }
      }
      return root;
    }

  }

  /**
   * Say we have 2 arrays, PRE and IN.
   * Preorder traversing implies that PRE[0] is the root node.
   * Then we can find this PRE[0] in IN, say it's IN[5].
   * Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
   * Recursively doing this on subarrays, we can build a tree out of it
   */
  public static class Solution2 implements Solution, Recursive {
    @Override
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private static TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
      if(preStart > preorder.length - 1 || inStart > inEnd) {
        return null;
      }
      TreeNode root = new TreeNode(preorder[preStart]);
      int inIndex = 0; // Index of current root in inorder
      for(int i = inStart; i <= inEnd; i++) {
        if(inorder[i] == root.val) {
          inIndex = i;
        }
      }
      root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
      root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
      return root;
    }
  }

  public static class Solution3 implements Solution {
    @Override
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      int p = 0;
      int i = 0;
      Stack<TreeNode> lefts = new Stack<>();
      lefts.push(new TreeNode(0)); // its left holds the root

      TreeNode expectingRightChild = null; // the parent that is expecting a right child
      while(p < preorder.length) {
        TreeNode next = new TreeNode(preorder[p++]);
        if(expectingRightChild != null) {
          expectingRightChild.right = next;
          expectingRightChild = null;
        } else {
          lefts.peek().left = next;
          lefts.push(next);

          while()
        }
      }

      return lefts.pop();
    }
  }

  public static void main(String args[]) {
    int[] preorder;
    int[] inorder;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      preorder = new int[] {1, 2};
      inorder = new int[] {2, 1};
      result = s.buildTree(preorder, inorder);
      System.out.println(result);


      /**
       *     1
       *    / \
       *   2   7
       *  / \   \
       * 3  4    8
       *   / \
       *  5  6
       */
      preorder = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
      inorder = new int[] {3, 2, 5, 4, 6, 1, 7, 8};
      result = s.buildTree(preorder, inorder);
      System.out.println(result);
    }
  }

}
