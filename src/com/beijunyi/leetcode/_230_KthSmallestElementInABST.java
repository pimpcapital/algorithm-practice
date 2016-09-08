package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note:
 *   You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Follow up:
 *   What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How
 *   would you optimize the kthSmallest routine?
 *
 * Hint:
 *   1) Try to utilize the property of a BST.
 *   2) What if you could modify the BST node's structure?
 *   3) The optimal runtime complexity is O(height of BST).
 */
public class _230_KthSmallestElementInABST implements Medium {

  public interface Solution {
    int kthSmallest(TreeNode root, int k);
  }

  public static class Solution1 implements Solution {
    @Override
    public int kthSmallest(TreeNode root, int k) {
      Stack<TreeNode> parents = new Stack<>();
      pushLefts(root, parents);
      int count = 1;
      while(!parents.isEmpty()) {
        TreeNode next = parents.pop();
        if(k == count++) return next.val;
        pushLefts(next.right, parents);
      }
      return -1; // error: not found
    }

    private static void pushLefts(TreeNode node, Stack<TreeNode> parents) {
      while(node != null) {
        parents.push(node);
        node = node.left;
      }
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    int k;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(2, 1, 3);
      k = 2;
      result = s.kthSmallest(root, k);
      System.out.println(result);
    }
  }

}
