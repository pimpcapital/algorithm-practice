package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Random;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same
 * probability of being chosen.
 *
 * Follow up:
 *   What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently
 *   without using extra space?
 *
 * Example:
 *   // Init a singly linked list [1,2,3].
 *   ListNode head = new ListNode(1);
 *   head.next = new ListNode(2);
 *   head.next.next = new ListNode(3);
 *   Solution solution = new Solution(head);
 *
 *   // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 *   solution.getRandom();
 */
public class _382_LinkedListRandomNode implements Medium {

  public interface Solution {

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    void init(ListNode head);

    /** Returns a random node's value. */
    int getRandom();
  }

  public static class Solution1 implements Solution {

    ListNode head = null;
    Random randomGenerator = null;

    @Override
    public void init(ListNode head) {
      this.head = head;
      this.randomGenerator = new Random();

    }

    @Override
    public int getRandom() {
      ListNode result = null;
      while(result == null) {
        ListNode current = head;
        for(int n = 1; current != null; n++) {
          if(randomGenerator.nextInt(n) == 0) {
            result = current;
          }
          current = current.next;
        }
      }

      return result.val;

    }
  }

  public static void main(String args[]) {
    for(Solution s : Arrays.asList(new Solution1())) {
      ListNode list = ListNode.fromArray(1, 2, 3);
      s.init(list);
      System.out.println(s.getRandom());
      System.out.println(s.getRandom());
      System.out.println(s.getRandom());
      System.out.println(s.getRandom());
      System.out.println(s.getRandom());
      System.out.println(s.getRandom());
    }
  }



}
