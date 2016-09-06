package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.ds.ListNode;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class _109_ConvertSortedListToBinarySearchTree {

  public interface Solution {
    TreeNode sortedListToBST(ListNode head);
  }

  public static class Solution1 implements Solution {

    private ListNode current;

    @Override
    public TreeNode sortedListToBST(ListNode head) {
      int size = findSize(head);
      current = head;
      return buildTree(0, size);
    }

    private TreeNode buildTree(int left, int right) {
      if(left == right) return null;
      int mid = left + (right - left) / 2;
      TreeNode leftChild = buildTree(left, mid);
      TreeNode ret = new TreeNode(current.val);
      current = current.next;
      TreeNode rightChild = buildTree(mid + 1, right);
      ret.left = leftChild;
      ret.right = rightChild;
      return ret;
    }

    private int findSize(ListNode head) {
      int count = 0;
      while(head != null) {
        head = head.next;
        count++;
      }
      return count;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      head = ListNode.fromArray(1, 2, 4, 5, 11, 15, 18, 22, 32, 111);
      result = s.sortedListToBST(head);
      System.out.println(Arrays.toString(TreeNode.toArray(result)));
    }
  }

}
