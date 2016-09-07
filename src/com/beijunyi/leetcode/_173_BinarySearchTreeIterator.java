package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class _173_BinarySearchTreeIterator implements Medium, NotHardButTricky {

  public interface Solution {

    void init(TreeNode root);

    boolean hasNext();

    int next();

  }

  public static class Solution1 implements Solution {

    private Stack<TreeNode> path = new Stack<>();
    private TreeNode next;

    @Override
    public void init(TreeNode root) {
      pushLefts(root);
      next = null;
    }

    @Override
    public boolean hasNext() {
      if(next != null) return true;
      if(path.isEmpty()) return false;
      next = path.pop();
      pushLefts(next.right);
      return true;
    }

    @Override
    public int next() {
      if(!hasNext()) throw new NoSuchElementException();
      int ret = next.val;
      next = null;
      return ret;
    }

    private void pushLefts(TreeNode node) {
      if(node == null) return;
      path.push(node);
      while(path.peek().left != null) {
        path.push(path.peek().left);
      }
    }

  }

  public static class Solution2 implements Solution {

    private Stack<TreeNode> stack = new Stack<>();

    @Override
    public void init(TreeNode root) {
      pushAll(root);
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public int next() {
      TreeNode tmpNode = stack.pop();
      pushAll(tmpNode.right);
      return tmpNode.val;
    }

    private void pushAll(TreeNode node) {
      while(node != null) {
        stack.push(node);
        node = node.left;
      }
    }

  }

  public static void main(String args[]) {
    TreeNode root;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      root = TreeNode.fromArray(2, 1);
      s.init(root);
      while(s.hasNext()) {
        System.out.println(s.next());
      }
    }
  }

}
