package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 *
 * return 1->4->3->2->5->NULL.
 *
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedListTwo implements Medium {

  public static class Solution1 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
      ListNode beforeHead = new ListNode(0);
      beforeHead.next = head;
      ListNode beforeReverse = beforeHead;
      ListNode reverseStart = null;
      ListNode reverseEnd = null;
      ListNode afterReverse;
      for(int i = 1; i < m; i++) {
        beforeReverse = beforeReverse.next;
      }
      for(int i = 0; i <= n - m; i++) {
        if(reverseStart == null) {
          reverseStart = beforeReverse.next;
          reverseEnd = reverseStart;
        } else
          reverseEnd = reverseEnd.next;
      }
      if(reverseStart == reverseEnd)
        return head;
      afterReverse = reverseEnd.next;
      for(int i = 0; i <= n - m; i++) {
        ListNode temp = reverseStart.next;
        beforeReverse.next = reverseStart;
        reverseStart.next = afterReverse;
        afterReverse = reverseStart;
        reverseStart = temp;
      }
      return beforeHead.next;
    }
  }

  public static class Solution2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
      ListNode start = new ListNode(0);
      start.next = head;
      ListNode tail = null;
      ListNode beforeHead = start;
      for(int i = 1; i <= n; i++) {
        if(i < m) {
          beforeHead = head;
          head = head.next;
        } else if(i == m) {
          tail = head;
        } else {
          assert tail != null;
          beforeHead.next = tail.next;
          tail.next = tail.next.next;
          beforeHead.next.next = head;
          head = beforeHead.next;
        }
      }
      return start.next;
    }
  }

  public static void main(String args[]) {
    ListNode head = new ListNode(1);
    ListNode current = head;
    for(int i = 2; i <= 5; i++) {
      current.next = new ListNode(i);
      current = current.next;
    }
    System.out.println(new Solution1().reverseBetween(head, 2, 4));

    head = new ListNode(1);
    current = head;
    for(int i = 2; i <= 5; i++) {
      current.next = new ListNode(i);
      current = current.next;
    }
    System.out.println(new Solution2().reverseBetween(head, 2, 4));
  }

}
