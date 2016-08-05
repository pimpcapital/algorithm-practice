package com.beijunyi.leetcode.ds;

public class ListNode {

  public int val;
  public ListNode next;

  public ListNode(int x) {
    val = x;
    next = null;
  }

  public String toString() {
    return val + (next != null ? " -> " + next : "");
  }

  public static ListNode fromArray(int... nums) {
    if(nums == null || nums.length == 0) return null;
    ListNode head = new ListNode(nums[0]);
    ListNode current = head;
    for(int i = 1; i < nums.length; i++) {
      int num = nums[i];
      current.next = new ListNode(num);
      current = current.next;
    }
    return head;
  }

  public static String toString(ListNode head) {
    StringBuilder builder = new StringBuilder();
    while(head != null) {
      if(builder.length() != 0) {
        builder.append(" -> ");
      }
      builder.append(head.val);
      head = head.next;
    }
    return builder.toString();
  }

}
