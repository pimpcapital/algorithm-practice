package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * For example:
 *   Given binary tree {1,#,2,3},
 *     1
 *      \
 *       2
 *      /
 *     3
 *   return [1,2,3].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class _144_BinaryTreePreorderTraversal implements Medium {

  public interface Solution {
    List<Integer> preorderTraversal(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> ret = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      pushLefts(root, stack, ret);
      while(!stack.isEmpty()) {
        TreeNode next = stack.pop();
        pushLefts(next.right, stack, ret);
      }
      return ret;
    }

    private static void pushLefts(TreeNode node, Stack<TreeNode> stack, List<Integer> ret) {
      while(node != null) {
        stack.push(node);
        ret.add(node.val);
        node = node.left;
      }
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(1, null, 2, 3);
      result = s.preorderTraversal(root);
      System.out.println(result);
    }
  }

}
