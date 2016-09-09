package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

public class _226_InvertBinaryTree implements Easy {

  public interface Solution {
    TreeNode invertTree(TreeNode root);
  }

  public static class Solution1 implements Solution {
    @Override
    public TreeNode invertTree(TreeNode root) {
      if(root == null) return null;
      TreeNode tmp = root.left;
      invertTree(root.left = root.right);
      invertTree(root.right = tmp);
      return root;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(4, 2, 7, 1, 3, 6, 9);
      result = s.invertTree(root);
      System.out.println(result);
    }
  }

}
