package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

public class _142_LinkedListCycleTwo implements Medium {

  public interface Solution {
    ListNode detectCycle(ListNode head);
  }

  /**
   * Let C be the position where the cycle starts
   * Let N be the length of the cycle
   * Slow(S) one travels i speed, Fast(F) one travels 2i speed.
   * When S and F meet:
   *   S has travelled C + aN
   *   F has travelled C + bN
   *   i = C + aN, 2i = C + bN, b > a
   *   i = (b - a)N
   * Since a and b are integer, i = multiple of N.
   * This means traveling i distance will always ends up at the same position in the cycle.
   *   X = X + (i % N) = X + (2i % N) = ...
   *
   * Put a second slow pointer(S2) at head. Let both slow pointer move at speed = 1.
   *   When S2 reaches the entry of the loop, it has traveled C.
   *   S has traveled C + i distance in total.
   * Since C + i and C have the same position in the cycle, S and S2 will meet when S2 enters the cycle
   */
  public static class Solution1 implements Solution {

    @Override
    public ListNode detectCycle(ListNode head) {
      if(head == null) return null;
      ListNode slow = head;
      ListNode fast = head;
      do {
        if(fast == null || fast.next == null) return null;
        fast = fast.next.next;
        slow = slow.next;
      } while(slow != fast);
      ListNode slow2 = head;
      while(slow2 != slow) {
        slow = slow.next;
        slow2 = slow2.next;
      }
      return slow;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;

    for(Solution s : Arrays.asList(new Solution1())) {
      head = ListNode.fromArray(3, 2, 0, -4);
      head.next.next.next.next = head.next;
      result = s.detectCycle(head);
      System.out.println(result.val);
    }

  }

}
