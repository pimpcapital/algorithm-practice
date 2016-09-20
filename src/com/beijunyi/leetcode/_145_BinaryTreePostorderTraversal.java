package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 *   Given binary tree {1,#,2,3},
 *     1
 *      \
 *       2
 *      /
 *     3
 *   return [3,2,1].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class _145_BinaryTreePostorderTraversal implements Easy {

  public interface Solution {
    List<Integer> postorderTraversal(TreeNode root);
  }

  public static class Solution1 implements Solution, Recursive {

    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> ret = new ArrayList<>();
      postorderTraversal(root, ret);
      return ret;
    }

    private static void postorderTraversal(TreeNode node, List<Integer> result) {
      if(node == null) return;
      postorderTraversal(node.left, result);
      postorderTraversal(node.right, result);
      result.add(node.val);
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(1, null, 2, 3);
      result = s.postorderTraversal(root);
      System.out.println(result);
    }
  }

}
