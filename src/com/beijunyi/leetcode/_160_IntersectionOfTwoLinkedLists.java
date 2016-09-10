package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *   A:          a1 → a2
 *                       ↘
 *                         c1 → c2 → c3
 *                       ↗
 *   B:    b1 → b2 → b3
 * begin to intersect at node c1.
 *
 *
 * Notes:
 *   If the two linked lists have no intersection at all, return null.
 *   The linked lists must retain their original structure after the function returns.
 *   You may assume there are no cycles anywhere in the entire linked structure.
 *   Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class _160_IntersectionOfTwoLinkedLists implements Easy {

  public interface Solution {
    ListNode getIntersectionNode(ListNode headA, ListNode headB);
  }

  public static class Solution1 implements Solution {
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      return getIntersectionNode(headA, findLength(headA), headB, findLength(headB));
    }

    private static ListNode getIntersectionNode(ListNode headA, int lengthA, ListNode headB, int lengthB) {
      if(lengthA < lengthB) return getIntersectionNode(headB, lengthB, headA, lengthA);
      while(lengthA > lengthB)  {
        headA = headA.next;
        lengthA--;
      }
      for(int i = 0; i < lengthA; i++) {
        if(headA == headB) return headA;
        headA = headA.next;
        headB = headB.next;
      }
      return null;
    }

    private static int findLength(ListNode node) {
      int ret = 0;
      while(node != null) {
        ret++;
        node = node.next;
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    ListNode headA;
    ListNode headB;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      headA = ListNode.fromArray(1);
      headB = ListNode.fromArray();
      result = s.getIntersectionNode(headA, headB);
      System.out.println(result);

      headA = ListNode.fromArray(1, 2, 3, 4, 5);
      headB = ListNode.fromArray(11, 12, 13);
      headB.next.next.next = headA.next.next;
      result = s.getIntersectionNode(headA, headB);
      System.out.println(result);
    }
  }


}
