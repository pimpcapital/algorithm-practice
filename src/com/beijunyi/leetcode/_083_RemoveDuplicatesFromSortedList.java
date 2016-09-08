package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class _083_RemoveDuplicatesFromSortedList implements Easy {

  public interface Solution {
    ListNode deleteDuplicates(ListNode head);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode deleteDuplicates(ListNode head) {
      if(head == null) return null;
      ListNode holder = new ListNode(0);
      holder.next = head;
      ListNode first = head;
      ListNode current = first.next;
      first.next = null;
      while(current != null) {
        ListNode next = current.next;
        if(first.val != current.val) {
          first.next = current;
          first = first.next;
          first.next = null;
        }
        current = next;
      }
      return holder.next;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public ListNode deleteDuplicates(ListNode head) {
      if(head == null || head.next == null)return head;
      head.next = deleteDuplicates(head.next);
      return head.val == head.next.val ? head.next : head;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      head = ListNode.fromArray(1, 2);
      result = s.deleteDuplicates(head);
      System.out.println(result);

      head = ListNode.fromArray(1, 1);
      result = s.deleteDuplicates(head);
      System.out.println(result);

      head = ListNode.fromArray(1, 1, 2);
      result = s.deleteDuplicates(head);
      System.out.println(result);

      System.out.println();
    }
  }

}
