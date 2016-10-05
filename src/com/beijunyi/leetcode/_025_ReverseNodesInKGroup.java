package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */
public class _025_ReverseNodesInKGroup implements Hard {

  public interface Solution {
    ListNode reverseKGroup(ListNode head, int k);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode reverseKGroup(ListNode head, int k) {
      int total = 0;
      for(ListNode i = head; i != null; total++, i = i.next);

      ListNode dmy = new ListNode(0);
      dmy.next = head;
      for(ListNode prev = dmy, tail = head; total >= k; total -= k) {
        for (int i = 1; i < k; i++) {
          ListNode next = tail.next.next;
          tail.next.next = prev.next;
          prev.next = tail.next;
          tail.next = next;
        }

        prev = tail;
        tail = tail.next;
      }
      return dmy.next;
    }
  }

  public static class Solution2 implements Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;

      ListNode tail = dummy;
      while(true) {
        ListNode[] headTail = reverseNextK(tail.next, k);
        if(headTail == null) break;
        tail.next = headTail[0];
        tail = headTail[1];
      }
      return dummy.next;
    }

    private static ListNode[] reverseNextK(ListNode head, int k) {
      if(!checkMinLength(head, k)) return null;

      ListNode headOfTail = null;
      ListNode current = head;
      for(int i = 0; i < k; i++) {
        ListNode next = current.next;
        current.next = headOfTail;
        headOfTail = current;
        current = next;
      }

      head.next = current;
      return new ListNode[]{headOfTail, head};
    }

    private static boolean checkMinLength(ListNode head, int k) {
      while(head != null && --k > 0) head = head.next;
      return k == 0;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    int k;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      head = ListNode.fromArray(1, 2, 3, 4, 5);
      k = 2;
      result = s.reverseKGroup(head, k);
      System.out.println(result);
    }
  }
}
