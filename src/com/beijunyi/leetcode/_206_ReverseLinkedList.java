package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Reverse a singly linked list.
 */
public class _206_ReverseLinkedList implements Easy {

  public interface Solution {
    ListNode reverseList(ListNode head);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode reverseList(ListNode head) {
      ListNode result = null;
      while(head != null) {
        ListNode next = head.next;
        head.next = result;
        result = head;
        head = next;
      }
      return result;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      head = ListNode.fromArray(1, 2, 3, 4, 5);
      result = s.reverseList(head);
      System.out.println(result);
    }
  }

}
