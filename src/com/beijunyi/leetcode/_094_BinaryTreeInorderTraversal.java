package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.InputModification;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

public class _094_BinaryTreeInorderTraversal implements Medium {

  public interface Solution {
    List<Integer> inorderTraversal(TreeNode root);
  }

  public static class Solution1 implements Solution, Iterative, InputModification {
    @Override
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> ret = new ArrayList<>();
      if(root == null) return ret;
      Stack<TreeNode> path = new Stack<>();
      path.push(root);
      while(!path.isEmpty()) {
        TreeNode current = path.peek();
        if(current.left != null) {
          path.push(current.left);
          current.left = null;
        } else {
          ret.add(current.val);
          path.pop();
          if(current.right != null) path.push(current.right);
        }
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution, Recursive {

    private List<Integer> list;

    @Override
    public List<Integer> inorderTraversal(TreeNode root) {
      list = new ArrayList<>();
      if(root == null)  return list;
      inorder(root);
      return list;
    }

    public void inorder(TreeNode root) {
      if(root.left != null) inorder(root.left);
      list.add(root.val);
      if(root.right != null) inorder(root.right);
    }
  }

  public static class Solution3 implements Solution, Iterative {

    @Override
    public List<Integer> inorderTraversal(TreeNode root) {
      final List<Integer> inorderResult = new ArrayList<>();
      if(root == null) {
        return inorderResult;
      }

      TreeNode cur = root;
      final Stack<TreeNode> stack = new Stack<>();
      while(cur != null || !stack.isEmpty()) {
        if(cur != null) {
          stack.push(cur);
          cur = cur.left;
        } else {
          cur = stack.pop();
          inorderResult.add(cur.val);
          cur = cur.right;
        }
      }// end while

      return inorderResult;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      root = TreeNode.fromArray(1, null, 2, 3);
      result = s.inorderTraversal(root);
      System.out.println(result);
    }
  }

}
