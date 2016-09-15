package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *      1
 *    /   \
 *   2     3
 *    \
 *     5
 * All root-to-leaf paths are:
 *   ["1->2->5", "1->3"]
 */
public class _257_BinaryTreePaths implements Easy {

  public interface Solution {
    List<String> binaryTreePaths(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<String> binaryTreePaths(TreeNode root) {
      List<String> result = new ArrayList<>();
      if(root != null) binaryTreePaths(root, Integer.toString(root.val), result);
      return result;
    }

    private static void binaryTreePaths(TreeNode node, String current, List<String> result) {
      if(node.left == null && node.right == null) {
        result.add(current);
      } else {
        if(node.left != null) binaryTreePaths(node.left, current + "->" + node.left.val, result);
        if(node.right != null) binaryTreePaths(node.right, current + "->" + node.right.val, result);
      }
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public List<String> binaryTreePaths(TreeNode root) {
      List<String> result = new ArrayList<>();
      if(root != null) binaryTreePaths(root, new LinkedList<>(), result);
      return result;
    }

    private static void binaryTreePaths(TreeNode node, LinkedList<Integer> current, List<String> result) {
      current.addLast(node.val);
      if(node.left == null && node.right == null) {
        result.add(toString(current));
      } else {
        if(node.left != null) binaryTreePaths(node.left, current, result);
        if(node.right != null) binaryTreePaths(node.right, current, result);
      }
      current.removeLast();
    }

    private static String toString(Deque<Integer> path) {
      StringBuilder sb = new StringBuilder();
      for(int val : path) {
        if(sb.length() > 0) sb.append("->");
        sb.append(val);
      }
      return sb.toString();
    }
  }

  /**
   * Use StringBuilder.setLength(...) to trim the tail.
   */
  public static class Solution3 implements Solution {
    @Override
    public List<String> binaryTreePaths(TreeNode root) {
      List<String> res = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      helper(res, root, sb);
      return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
      if(root == null) {
        return;
      }
      int len = sb.length();
      sb.append(root.val);
      if(root.left == null && root.right == null) {
        res.add(sb.toString());
      } else {
        sb.append("->");
        helper(res, root.left, sb);
        helper(res, root.right, sb);
      }
      sb.setLength(len);
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    List<String> path;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      root = TreeNode.fromArray(1, 2, 3, null, 5);
      path = s.binaryTreePaths(root);
      System.out.println(path);
    }
  }

}
