package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
 * original list.
 *
 * For example,
 *   Given 1->2->3->3->4->4->5, return 1->2->5.
 *   Given 1->1->1->2->3, return 2->3.
 */
public class _082_RemoveDuplicatesFromSortedListTwo implements Medium {

  public interface Solution {
    ListNode deleteDuplicates(ListNode head);
  }

  public static class Solution1 implements Solution {

    @Override
    public ListNode deleteDuplicates(ListNode head) {
      ListNode dummy = new ListNode(0);
      ListNode tail = dummy;

      ListNode previous = null;
      ListNode current = head;
      boolean add = true;
      while(current != null) {
        if(previous != null) {
          if(current.val != previous.val) {
            if(add) {
              tail.next = previous;
              tail = previous;
              tail.next = null;
            }
            add = true;
          } else {
            add = false;
          }
        }
        previous = current;
        current = current.next;
      }
      if(add && previous != null) {
        tail.next = previous;
        previous.next = null;
      }

      return dummy.next;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public ListNode deleteDuplicates(ListNode head) {
      ListNode dummy = new ListNode(0);
      ListNode tail = dummy;

      ListNode current = head;
      while(current != null) {
        boolean duplicated = false;
        while(current.next != null && current.val == current.next.val) {
          duplicated = true;
          current = current.next;
        }
        if(!duplicated) {
          tail.next = current;
          tail = tail.next;
        }
        current = current.next;
      }
      tail.next = null;

      return dummy.next;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      head = ListNode.fromArray(1, 2, 3, 3, 4, 4, 5);
      result = s.deleteDuplicates(head);
      System.out.println(result);

      head = ListNode.fromArray(1, 1, 1, 2, 3);
      result = s.deleteDuplicates(head);
      System.out.println(result);

      head = ListNode.fromArray(1, 2, 2);
      result = s.deleteDuplicates(head);
      System.out.println(result);

      System.out.println();
    }

  }

}
