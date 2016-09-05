package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

public class _147_InsertionSortList implements Medium {

  public interface Solution {
    ListNode insertionSortList(ListNode head);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode insertionSortList(ListNode head) {
      if(head == null || head.next == null) return head;
      ListNode holder = new ListNode(0);
      holder.next = head;
      ListNode toSort = head.next;
      head.next = null; // disconnect sorted part and unsorted part
      while(toSort != null) {
        ListNode insertPoint = holder;
        while(insertPoint.next != null && insertPoint.next.val <= toSort.val) {
          insertPoint = insertPoint.next;
        }
        ListNode nextToSort = toSort.next;
        toSort.next = insertPoint.next;
        insertPoint.next = toSort;
        toSort = nextToSort;
      }
      return holder.next;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;
    for(Solution s : Arrays.asList(new Solution1())) {
      head = ListNode.fromArray(3, 1, 2, 5, 1, 2);
      result = s.insertionSortList(head);
      System.out.println(result);
    }

  }

}
