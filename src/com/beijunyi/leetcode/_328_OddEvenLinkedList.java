package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking
 * about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 *
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class _328_OddEvenLinkedList implements Medium {

  public interface Solution {
    ListNode oddEvenList(ListNode head);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode oddEvenList(ListNode head) {
      if(head == null) return null;
      LinkedListWrapper odds = new LinkedListWrapper();
      LinkedListWrapper evens = new LinkedListWrapper();
      int count = 0;
      ListNode current = head;
      while(current != null) {
        count++;
        ListNode next = current.next;
        if(count % 2 == 1) {
          odds.add(current);
        } else {
          evens.add(current);
        }
        current = next;
      }
      odds.tail.next = evens.head;
      return odds.head;
    }

    private static class LinkedListWrapper {
      public ListNode head;
      public ListNode tail;

      public void add(ListNode node) {
        if(node == null) return;
        if(tail == null) {
          head = node;
          tail = head;
        } else {
          tail.next = node;
          tail = node;
        }
        node.next = null;
      }

    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      ListNode node1 = new ListNode(1);
      ListNode node2 = new ListNode(2);
      ListNode node3 = new ListNode(3);
      ListNode node4 = new ListNode(4);
      ListNode node5 = new ListNode(5);
      node1.next = node2;
      node2.next = node3;
      node3.next = node4;
      node4.next = node5;
      head = node1;
      result = s.oddEvenList(head);
      System.out.println(result.toString());
    }

  }

}
