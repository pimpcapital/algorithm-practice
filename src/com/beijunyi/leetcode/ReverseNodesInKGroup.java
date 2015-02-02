package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroup implements Hard {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }

    public String toString() {
      return val + (next != null ? " -> " + next : "");
    }
  }

  public static class Solution1 {
    public ListNode reverseKGroup(ListNode head, int k) {
      if(head == null || head.next == null || k == 1)
        return head;

      ListNode result = null;
      ListNode prevGroupTail = null;

      while(head != null) {
        ListNode oldHead = head;
        ListNode newHead = head;
        for(int i = 1; i < k; i++) {
          if(newHead.next == null)
            return result == null ? head : result;
          newHead = newHead.next;
        }
        head = newHead.next;

        ListNode toLink = newHead;
        while(toLink != oldHead) {
          ListNode current = oldHead;
          while(current.next != toLink)
            current = current.next;
          toLink.next = current;
          toLink = current;
        }
        toLink.next = head;

        if(result == null)
          result = newHead;
        if(prevGroupTail != null)
          prevGroupTail.next = newHead;


        prevGroupTail = toLink;
      }

      return result;
    }
  }

  public static class Solution2 {
    public ListNode reverseKGroup(ListNode head, int k) {
      if (head==null||head.next==null||k<2) return head;

      ListNode dummy = new ListNode(0);
      dummy.next = head;

      ListNode tail = dummy, prev = dummy,temp;
      int count;
      while(true){
        count =k;
        while(count>0&&tail!=null){
          count--;
          tail=tail.next;
        }
        if (tail==null) break;//Has reached the end


        head=prev.next;//for next cycle
        // prev-->temp-->...--->....--->tail-->....
        // Delete @temp and insert to the next position of @tail
        // prev-->...-->...-->tail-->head-->...
        // Assign @temp to the next node of @prev
        // prev-->temp-->...-->tail-->...-->...
        // Keep doing until @tail is the next node of @prev
        while(prev.next!=tail){
          temp=prev.next;//Assign
          prev.next=temp.next;//Delete

          temp.next=tail.next;
          tail.next=temp;//Insert

        }

        tail=head;
        prev=head;

      }
      return dummy.next;

    }
  }

  public static void main(String args[]) {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    System.out.println(new Solution1().reverseKGroup(n1, 2));
    n1 = new ListNode(1);
    n2 = new ListNode(2);
    n3 = new ListNode(3);
    n4 = new ListNode(4);
    n5 = new ListNode(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    System.out.println(new Solution2().reverseKGroup(n1, 2));
  }
}
