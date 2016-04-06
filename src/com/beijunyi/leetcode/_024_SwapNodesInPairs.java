package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

public class _024_SwapNodesInPairs implements Medium {

  public static class Solution2 {
    public ListNode swapPairs(ListNode head) {
      if(head == null)
        return null;

      ListNode newHead = head.next;
      if(newHead == null)
        return head;

      ListNode current = head;
      ListNode prev = null;
      while(current != null && current.next != null) {
        ListNode next = current.next;
        current.next = next.next;
        next.next = current;

        if(prev != null)
          prev.next = next;
        prev = current;
        current = current.next;
      }

      return newHead;
    }
  }

  public static class Solution1 {
    public ListNode swapPairs(ListNode head) {
      ListNode start = new ListNode(0); //make head no longer a special case
      start.next = head;

      for(ListNode cur = start; cur.next != null && cur.next.next != null; cur = cur.next.next) {
        cur.next = swap(cur.next, cur.next.next);
      }
      return start.next;
    }
    private ListNode swap(ListNode next1, ListNode next2) {
      next1.next = next2.next;
      next2.next = next1;
      return next2;
    }
  }

  public static void main(String args[]) {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = null;
    System.out.println(new Solution1().swapPairs(n1));

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = null;
    System.out.println(new Solution2().swapPairs(n1));
  }
}
