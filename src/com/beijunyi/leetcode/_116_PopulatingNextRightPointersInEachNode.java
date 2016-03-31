package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeLinkNode;

/**
 * Given a binary tree
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 *
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *       1
 *     /   \
 *    2     3
 *   / \   / \
 *  4   5 6   7
 * After calling your function, the tree should look like:
 *       1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \  / \
 *  4->5->6->7 -> NULL
 */
public class _116_PopulatingNextRightPointersInEachNode implements Medium {

  public static class Solution {
    public void connect(TreeLinkNode root) {
      if(root == null)
        return;
      if(root.left != null) {
        root.left.next = root.right;
        if(root.next != null)
          root.right.next = root.next.left;
      }
      connect(root.left);
      connect(root.right);
    }
  }

  public static void main(String args[]) {
    TreeLinkNode root = new TreeLinkNode(1);
    TreeLinkNode n2 = new TreeLinkNode(2);
    TreeLinkNode n3 = new TreeLinkNode(3);
    root.left = n2;
    root.right = n3;
    TreeLinkNode n4 = new TreeLinkNode(4);
    TreeLinkNode n5 = new TreeLinkNode(5);
    TreeLinkNode n6 = new TreeLinkNode(6);
    TreeLinkNode n7 = new TreeLinkNode(7);
    n2.left = n4;
    n2.right = n5;
    n3.left = n6;
    n3.right = n7;
    new Solution().connect(root);
    StringBuilder sb = new StringBuilder();
    TreeLinkNode level = root;
    while(level != null) {
      TreeLinkNode cur = level;
      while(cur != null) {
        sb.append(cur.val).append(" ");
        cur = cur.next;
      }
      sb.append("# ");
      level = level.left;
    }
    System.out.println(sb);
  }


}
