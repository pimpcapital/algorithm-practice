package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *   Given linked list: 1->2->3->4->5, and n = 2.
 *   After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 *   Given n will always be valid.
 *   Try to do this in one pass.
 */
public class _019_RemoveNthNodeFromEndOfList implements Easy {

  public interface Solution {
    ListNode removeNthFromEnd(ListNode head, int n);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode removeNthFromEnd(ListNode head, int n) {
      int i = 0; // keep counting how many nodes has traveled
      ListNode holder = new ListNode(0);
      ListNode remove = holder;
      holder.next = head;

      ListNode current = head;
      while(current != null) {
        current = current.next;
        i++;
        if(i > n) remove = remove.next;
      }
      remove.next = remove.next.next;
      return holder.next;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    int n;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      head = ListNode.fromArray(1, 2, 3, 4, 5);
      n = 2;
      result = s.removeNthFromEnd(head, n);
      System.out.println(result);
    }
  }

}
