package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example
 *   Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 *   Return: 1 --> 2 --> 3 --> 4 --> 5
 */
public class _203_RemoveLinkedListElements implements Easy {

  public interface Solution {
    ListNode removeElements(ListNode head, int val);
  }

  public static class Solution1 implements Solution {

    @Override
    public ListNode removeElements(ListNode head, int val) {
      ListNode holder = new ListNode(0);
      ListNode tail = holder;
      while(head != null) {
        if(head.val != val) tail = tail.next = head;
        head = head.next;
      }
      tail.next = null;
      return holder.next;
    }

  }

  public static void main(String args[]) {
    ListNode head;
    int val;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      head = ListNode.fromArray(1, 2, 6, 3, 4, 5, 6);
      val = 6;
      result = s.removeElements(head, val);
      System.out.println(result);
    }
  }

}
