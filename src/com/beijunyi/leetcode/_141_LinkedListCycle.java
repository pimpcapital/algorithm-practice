package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * Follow up:
 *  Can you solve it without using extra space?
 */
public class _141_LinkedListCycle implements Medium {

  public interface Solution {
    boolean hasCycle(ListNode head);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean hasCycle(ListNode head) {
      ListNode runner1 = head;
      ListNode runner2 = head;
      do {
        runner1 = run(runner1, 1);
        runner2 = run(runner2, 2);
        if(runner1 == null || runner2 == null) return false;
        if(runner1 == runner2) return true;
      } while(true);
    }

    private static ListNode run(ListNode runner, int steps) {
      for(int i = 0; i < steps; i++) {
        if(runner == null) break;
        runner = runner.next;
      }
      return runner;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public boolean hasCycle(ListNode head){
      if(head == null || head.next == null) return false;
      if(head.next == head) return true;
      ListNode nextNode = head.next;
      head.next = head;
      return hasCycle(nextNode);
    }
  }

  public static void main(String args[]) {
    ListNode head;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      ListNode ln11 = new ListNode(1);
      ListNode ln12 = new ListNode(2);
      ln11.next = ln12;
      ln12.next = new ListNode(3);
      head = ln11;
      System.out.println(s.hasCycle(head));

      ListNode ln21 = new ListNode(1);
      ListNode ln22 = new ListNode(2);
      ln21.next = ln22;
      ListNode ln23 = new ListNode(3);
      ln22.next = ln23;
      ListNode ln24 = new ListNode(4);
      ln23.next = ln24;
      ln24.next = ln22;
      head = ln21;
      System.out.println(s.hasCycle(head));
    }
  }

}
