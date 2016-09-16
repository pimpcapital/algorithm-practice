package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * For example:
 *   Given 1->2->3->4->5->NULL and k = 2,
 *   return 4->5->1->2->3->NULL.
 */
public class _061_RotateList implements Medium {

  public interface Solution {
    ListNode rotateRight(ListNode head, int k);
  }

  public static class Solution1 implements Solution {

    @Override
    public ListNode rotateRight(ListNode head, int k) {
      if(head == null) return null;
      int length = findLength(head);
      if((k = k % length) == 0) return head;

      head = reverse(head);
      ListNode kParent = findKNode(head, k - 1);
      ListNode tail = kParent.next;
      kParent.next = null;
      tail = reverse(tail);
      kParent = head;
      head = reverse(head);
      kParent.next = tail;
      return head;
    }

    private static int findLength(ListNode node) {
      int ret = 0;
      while(node != null) {
        node = node.next;
        ret++;
      }
      return ret;
    }

    private static ListNode findKNode(ListNode node, int n) {
      while(n > 0) {
        node = node.next;
        n--;
      }
      return node;
    }

    private static ListNode reverse(ListNode node) {
      ListNode headOfTail = null;
      while(node != null) {
        ListNode next = node.next;
        node.next = headOfTail;
        headOfTail = node;
        node = next;
      }
      return headOfTail;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public ListNode rotateRight(ListNode head, int k) {
      if(head == null) return null;
      int length = findLength(head);
      if((k = k % length) == 0) return head;

      int wrappedNodes = length - k;
      ListNode nodeBeforeWrap = findKNode(head, wrappedNodes - 1);
      ListNode headOfWrappedNodes = nodeBeforeWrap.next;
      nodeBeforeWrap.next = null;
      ListNode lastOfWrappedNodes = findKNode(headOfWrappedNodes, k - 1);
      lastOfWrappedNodes.next = head;

      return headOfWrappedNodes;
    }

    private static int findLength(ListNode node) {
      int ret = 0;
      while(node != null) {
        node = node.next;
        ret++;
      }
      return ret;
    }

    private static ListNode findKNode(ListNode node, int n) {
      while(n > 0) {
        node = node.next;
        n--;
      }
      return node;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    int k;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      head = ListNode.fromArray(1, 2);
      k = 0;
      result = s.rotateRight(head, k);
      System.out.println(result);

      head = ListNode.fromArray(1, 2, 3, 4, 5);
      k = 2;
      result = s.rotateRight(head, k);
      System.out.println(result);

      head = ListNode.fromArray(1, 2, 3, 4, 5);
      k = 7;
      result = s.rotateRight(head, k);
      System.out.println(result);
    }
  }

}
