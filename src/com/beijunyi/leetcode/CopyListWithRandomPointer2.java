package com.beijunyi.leetcode;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer2 {

  public static class RandomListNode {
    private RandomListNode next;
    private RandomListNode random;
    private int label;

    public RandomListNode(int label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return "RandomListNode{" +
               "label=" + label +
               ", random=" + (random != null ? random.label : null) +
               ", next=" + next +
               '}';
    }
  }

  /**
   * Step 1: duplicate each nodes, 1->2->3 becomes 1->`1->2->`2->3->`3;
   * Step 2: copy node's random according the previous node;
   * Step 3: split the list into two
   */
  public static class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
      if(head == null) return null;

      // step1: 1->2->3 become 1->`1->2->`2->3->`3
      RandomListNode cur = head;
      while(cur != null) {
        RandomListNode newNode = new RandomListNode(cur.label);
        newNode.next = cur.next;
        cur.next = newNode;
        cur = newNode.next;
      }

      // step2: copy random
      cur = head;
      while(cur != null) {
        RandomListNode newCur = cur.next;
        newCur.random = (cur.random == null) ? null : cur.random.next;
        cur = newCur.next;
      }

      // step3: split
      RandomListNode newHead = head.next;
      cur = head;
      while(cur != null) {
        RandomListNode newCur = cur.next;
        cur.next = newCur.next;
        newCur.next = (cur.next != null) ? cur.next.next : null;
        cur = cur.next;
      }

      return newHead;
    }
  }

  public static void main(String args[]) {
    RandomListNode one = new RandomListNode(1);
    RandomListNode two = new RandomListNode(2);
    RandomListNode three = new RandomListNode(3);
    one.next = two;
    two.next = three;
    one.random = three;
    three.random = two;
    System.out.println(new Solution().copyRandomList(one));
  }

}
