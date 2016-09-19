package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Follow up:
 *   Could you do it in O(n) time and O(1) space?
 */
public class _234_PalindromeLinkedList implements Easy {

  public interface Solution {
    boolean isPalindrome(ListNode head);
  }

  public static class Solution1 implements Solution {
    @Override
    public boolean isPalindrome(ListNode head) {
      int length = lengthOf(head);
      if(length < 2) return true;

      int tailIndex = length / 2 + length % 2;
      ListNode parentOfTail = nthNode(head, tailIndex - 1);
      ListNode tail = parentOfTail.next;
      parentOfTail.next = null;
      tail = reverse(tail);

      while(tail != null) {
        if(tail.val != head.val) return false;
        head = head.next;
        tail = tail.next;
      }
      return true;
    }

    private static int lengthOf(ListNode node) {
      int length = 0;
      while(node != null) {
        node = node.next;
        length++;
      }
      return length;
    }

    private static ListNode nthNode(ListNode node, int n) {
      while(n != 0) {
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
    public boolean isPalindrome(ListNode head) {
      if(head == null) {
        return true;
      }
      ListNode p1 = head;
      ListNode p2 = head;
      ListNode p3 = p1.next;
      ListNode pre = p1;
      //find mid pointer, and reverse head half part
      while(p2.next != null && p2.next.next != null) {
        p2 = p2.next.next;
        pre = p1;
        p1 = p3;
        p3 = p3.next;
        p1.next = pre;
      }

      //odd number of elements, need left move p1 one step
      if(p2.next == null) {
        p1 = p1.next;
      }
      else {   //even number of elements, do nothing

      }
      //compare from mid to head/tail
      while(p3 != null) {
        if(p1.val != p3.val) {
          return false;
        }
        p1 = p1.next;
        p3 = p3.next;
      }
      return true;

    }
  }

  public static void main(String args[]) {
    ListNode head;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      head = ListNode.fromArray(1, 2, 3, 4, 4, 3, 2, 1);
      result = s.isPalindrome(head);
      System.out.println(result);
    }
  }

}
