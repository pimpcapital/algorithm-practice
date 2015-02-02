package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class PartitionList implements Medium {

  private static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
          val = x;
          next = null;
    }

    @Override
    public String toString() {
      return val + (next != null ? ", " + next : "");
    }
  }

  public static class Solution {
    public ListNode partition(ListNode head, int x) {
      ListNode delayed = null;
      ListNode delayedLast = null;

      ListNode newHead = null;
      ListNode newEnd = null;

      ListNode current = head;
      while(current != null) {
        ListNode next = current.next;
        if(current.val >= x) {
          if(delayedLast == null) {
            delayed = current;
            delayedLast = current;
          } else {
            delayedLast.next = current;
            delayedLast = current;
          }
        } else {
          if(newEnd == null) {
            newHead = current;
            newEnd = current;
          } else {
            newEnd.next = current;
            newEnd = current;
          }
        }
        current.next = null;
        current = next;
      }
      if(newHead == null)
        newHead = delayed;
      else
        newEnd.next = delayed;
      return newHead;
    }
  }

  public static void main(String args[]) {
    ListNode head = new ListNode(2);
    head.next = new ListNode(1);
    System.out.println(new Solution().partition(head, 2));
  }

}
