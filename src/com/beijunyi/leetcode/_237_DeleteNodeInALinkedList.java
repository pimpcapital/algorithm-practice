package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 *
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should
 * become 1 -> 2 -> 4 after calling your function.
 */
public class _237_DeleteNodeInALinkedList implements Easy {

  public interface Solution {
    void deleteNode(ListNode node);
  }

  public static class Solution1 implements Solution {
    @Override
    public void deleteNode(ListNode node) {
      while(true) {
        node.val = node.next.val;
        if(node.next.next == null) {
          node.next = null;
          break;
        }
        node = node.next;
      }
    }
  }

  public static void main(String args[]) {
    ListNode node;

    for(Solution s : Arrays.asList(new Solution1())) {
      node = ListNode.fromArray(1, 2, 3, 4, 5);
      s.deleteNode(node);
      System.out.println(node);
    }
  }

}
