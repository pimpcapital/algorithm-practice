package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class _143_ReorderList implements Medium {

  public interface Solution {
    void reorderList(ListNode head);
  }

  public static class Solution1 implements Solution {
    @Override
    public void reorderList(ListNode head) {
      if(head == null) return;

      // count
      int count = 0;
      ListNode current = head;
      while(current != null) {
        count++;
        current = current.next;
      }

      // skip half
      current = head;
      int mid = count / 2 + count % 2;
      for(int i = 0; i < mid - 1; i++) {
        current = current.next;
      }
      ListNode tail = current.next;
      current.next = null;

      // reverse tail
      ListNode newTailStart = null;
      while(tail != null) {
        ListNode next = tail.next;
        tail.next = newTailStart;
        newTailStart = tail;
        tail = next;
      }
      tail = newTailStart;

      // merge head & tail
      current = head;
      while(tail != null) {  // size of tail is never bigger than head
        ListNode originNext = current.next;
        current.next = tail;
        tail = tail.next;
        current = current.next;
        current.next = originNext;
        current = current.next;
      }

    }

  }

  public static class Solution2 implements Solution {
    @Override
    public void reorderList(ListNode head) {
      if (head == null) return;
      int size = size(head);
      if (size == 1) return;

      // Split list into two halves. Head and Tail.
      int half = size/2 + size%2;
      ListNode tail = head;
      ListNode headLast = head;
      for (int i=0; i<half; i++) {
        headLast = tail;
        tail = tail.next;
      }
      headLast.next = null;

      // Reverse tails order
      // and do final merge
      tail = reverse(tail);
      merge(head, tail);
    }

    private static int size(ListNode head) {
      int size = 0;
      while(head != null) {
        size++ ;
        head = head.next;
      }
      return size;
    }

    private static ListNode reverse(ListNode head) {
      ListNode next = null;
      ListNode iteration;
      while ((iteration = head.next) != null) {
        head.next = next;
        next = head;
        head = iteration;
      }
      head.next = next;
      return head;
    }

    private static void merge(ListNode head, ListNode tail) {
      ListNode nextHead;
      while ((nextHead = head.next) != null) {
        ListNode nextTail = tail.next;
        head.next = tail;
        tail.next = nextHead;
        head = nextHead;
        tail = nextTail;
      }
      if (tail != null) {
        head.next = tail;
      }
    }
  }

  public static void main(String args[]) {
    ListNode head;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      head = ListNode.fromArray(1, 2, 3, 4);
      s.reorderList(head);
      System.out.println(head);

      head = ListNode.fromArray(1, 2, 3, 4, 5);
      s.reorderList(head);
      System.out.println(head);
    }
  }

}
