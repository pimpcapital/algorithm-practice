package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes
 * of the first two lists.
 */
public class _021_MergeTwoSortedLists implements Easy {

  public interface Solution {
    ListNode mergeTwoLists(ListNode l1, ListNode l2);
  }

  public static class Solution1 implements Solution {

    @Override
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListWrapper list = new ListWrapper();
      while(l1 != null && l2 != null) {
        if(l1.val < l2.val) {
          ListNode next = l1.next;
          list.add(l1);
          l1 = next;
        } else {
          ListNode next = l2.next;
          list.add(l2);
          l2 = next;
        }
      }
      if(l1 != null) list.add(l1);
      if(l2 != null) list.add(l2);
      return list.head;
    }

    private static class ListWrapper {
      ListNode head;
      ListNode tail;

      public void add(ListNode node) {
        if(head == null)
          head = node;
        else
          tail.next = node;
        tail = node;
      }
    }
  }

  public static void main(String args[]) {
    ListNode l1;
    ListNode l2;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      ListNode n11 = new ListNode(1);
      ListNode n12 = new ListNode(3);
      ListNode n13 = new ListNode(7);
      ListNode n21 = new ListNode(2);
      ListNode n22 = new ListNode(6);
      n11.next = n12;
      n12.next = n13;
      n21.next = n22;
      l1 = n11;
      l2 = n21;
      result = s.mergeTwoLists(l1, l2);
      System.out.println(result);
    }
  }

}
