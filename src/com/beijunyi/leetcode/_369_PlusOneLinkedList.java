package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 *   Input:
 *   1->2->3
 *
 *   Output:
 *   1->2->4
 */
public class _369_PlusOneLinkedList implements Medium, PremiumQuestion {

  public interface Solution {
    ListNode plusOne(ListNode head);
  }

  public static class Solution1 implements Solution {

    @Override
    public ListNode plusOne(ListNode head) {
      ListNode lead = new ListNode(0);
      lead.next = head;

      ListNode lastNonNine = lead;

      ListNode current = head;
      while(current != null) {
        if(current.val != 9) {
          lastNonNine = current;
        }
        current = current.next;
      }

      lastNonNine.val++;
      while(lastNonNine.next != null) {
        lastNonNine.next.val = 0;
        lastNonNine = lastNonNine.next;
      }

      if(lead.val == 0) {
        return lead.next;
      } else {
        return lead;
      }
    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1())) {
      ListNode list = ListNode.fromArray(1, 2, 3);
      s.plusOne(list);
      System.out.println(ListNode.toString(list));
    }

  }

}
