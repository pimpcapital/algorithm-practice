package com.beijunyi.leetcode;

import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

public class _106_ConstructBinaryTreeFromInorderAndPostorderTraversal implements Medium {

  public static class Solution2 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      if (inorder == null || inorder.length < 1)
        return null;
      int i = inorder.length - 1;
      int p = i;
      TreeNode node;
      TreeNode root = new TreeNode(postorder[p]);
      Stack<TreeNode> stack = new Stack<>();
      stack.push(root);
      p--;

      while (true) {
        if (inorder[i] == stack.peek().val) { // inorder[i] is on top of stack, pop stack to get its parent to get to left side
          if (--i < 0)
            break;
          node = stack.pop();
          if (!stack.isEmpty() && inorder[i] == stack.peek().val) // continue pop stack to get to left side
            continue;

          node.left = new TreeNode(postorder[p]);
          stack.push(node.left);
        } else { // inorder[i] is not on top of stack, postorder[p] must be right child
          node = new TreeNode(postorder[p]);
          stack.peek().right = node;
          stack.push(node);
        }
        p--;
      }

      return root;
    }
  }

  public static class Solution1 {

    public TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
      if(ps > pe)
        return null;

      TreeNode node = new TreeNode(postorder[pe]);
      int current = is;
      while(inorder[current] != node.val)
        current++;
      int leftTreeSize = current - 1 - is;

      // in order
      int leftTreeStart1 = is;
      int leftTreeEnd1 = is + leftTreeSize;
      int rightTreeStart1 = current + 1;
      int rightTreeEnd1 = ie;

      // post order
      int leftTreeStart2 = ps;
      int leftTreeEnd2 = ps + leftTreeSize;
      int rightTreeStart2 = ps + leftTreeSize + 1;
      int rightTreeEnd2 = pe - 1;

      node.left = buildTree(inorder, leftTreeStart1, leftTreeEnd1, postorder, leftTreeStart2, leftTreeEnd2);
      node.right = buildTree(inorder, rightTreeStart1, rightTreeEnd1, postorder, rightTreeStart2, rightTreeEnd2);

      return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
      return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
  }

  public static void main(String args[]) {
    TreeNode root = new Solution2().buildTree(new int[] {-4,-10,3,-1,7,11,-8,2}, new int[] {-4,-1,3,-10,11,-8,2,7});
    System.out.println(root);
    root = new Solution1().buildTree(new int[] {-4,-10,3,-1,7,11,-8,2}, new int[] {-4,-1,3,-10,11,-8,2,7});
    System.out.println(root);
  }


}
