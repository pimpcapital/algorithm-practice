package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.ListNode;

public class _148_SortList implements Medium {

  public interface Solution {
    ListNode sortList(ListNode head);
  }


  // Quick sort. O(n) memory usage
  public static class Solution1 implements Solution {
    @Override
    public ListNode sortList(ListNode head) {
      if(head == null || head.next == null) return head;
      ListNode pivot = head;
      ListNode pivotHead = pivot;
      ListNode left = new ListNode(-1);
      ListNode leftHead = left;
      ListNode right = new ListNode(-1);
      ListNode rightHead = right;
      ListNode current = head.next;

      // [elements smaller than pivot], [elements equal to pivot], [elements larger than pivot]
      while(current != null) {
        if(current.val < pivot.val) {
          left.next = current;
          left = left.next;
        } else if (current.val > pivot.val) {
          right.next = current;
          right = right.next;
        } else {
          pivot.next = current;
          pivot = pivot.next;
        }
        current = current.next;
      }

      // disconnect the tails from their original succeeders
      pivot.next = null;
      left.next = null;
      right.next = null;

      left = sortList(leftHead.next);
      right = sortList(rightHead.next);
      pivot.next = right;
      ListNode ret = left;

      // find the tail of left
      while(left != null && left.next != null) left = left.next;
      if(left == null) ret = pivotHead;
      else left.next = pivotHead;

      return ret;
    }
  }

  // Merge sort. O(n) memory usage
  public static class Solution2 implements Solution {
    @Override
    public ListNode sortList(ListNode head) {
      if(head == null || head.next == null) return head;

      ListNode prev = head;
      ListNode slow = head;
      ListNode fast = head;
      while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
      }

      prev.next = null; // disconnect the 2 halves
      ListNode firstHalf = sortList(head);
      ListNode secondHalf = sortList(slow);
      return merge(firstHalf, secondHalf);
    }

    private ListNode merge(ListNode firstHalf, ListNode secondHalf) {
      ListNode headNodeHolder = new ListNode(0);
      ListNode current = headNodeHolder;
      while(firstHalf != null && secondHalf != null) {
        if(firstHalf.val < secondHalf.val) {
          current.next = firstHalf;
          firstHalf = firstHalf.next;
        } else {
          current.next = secondHalf;
          secondHalf = secondHalf.next;
        }
        current = current.next;
      }
      // links the remained elements
      if(firstHalf != null) current.next = firstHalf;
      else current.next = secondHalf;

      return headNodeHolder.next;
    }
  }

  public static class Solution3 implements Solution {
    @Override
    public ListNode sortList(ListNode head) {
      if(head == null || head.next == null) return head;
      ListNode dummy = new ListNode(0);
      dummy.next = head;

      int pSize = 2;
      int pCount = 0;
      ListNode prevPTail = dummy;
      while(true) {
        prevPTail = sortPartition(prevPTail, pSize);
        if(prevPTail == null) {
          if(pCount == 1) break;
          pSize *= 2;
          pCount = 0;
          prevPTail = dummy;
        } else {
          pCount++;
        }
      }
      return dummy.next;
    }

    // sorts the next n elements starting from prevTail.next. returns the tail
    private static ListNode sortPartition(ListNode prevTail, int n) {
      if(prevTail.next == null) return null;
      ListNode left = prevTail.next;
      ListNode right = left;
      int mid = n / 2;
      int leftLength = mid;
      int rightLength = n - mid;
      for(int i = 0; i < mid; i++) right = right.next;

      ListNode resultHolder = new ListNode(0);
      ListNode resultTail = resultHolder;
      ListNode originTailNext = null;
      while(leftLength != 0 || rightLength != 0) {
        if(rightLength == 0 || leftLength != 0 && left.val < right.val) {
          resultTail.next = left;
          left = left.next;
          leftLength--;
        } else {
          if(rightLength == 1) originTailNext = right.next;
          resultTail.next = right;
          right = right.next;
          rightLength--;
        }
        resultTail = resultTail.next;
      }
      prevTail.next = resultHolder.next;
      resultTail.next = originTailNext;
      return resultTail;
    }
  }

  public static void main(String args[]) {
    ListNode head;
    ListNode result;
    for(Solution s : Arrays.asList(new Solution3())) {
      head = ListNode.fromArray(3, 2, 4);
      result = s.sortList(head);
      System.out.println(ListNode.toString(result));

//      head = ListNode.fromArray(3, 2, 1, 2, 1, 3, 4, 5, 1, 2, 3, 3, 3, 2, 1, 1);
//      result = s.sortList(head);
//      System.out.println(ListNode.toString(result));

    }
  }

}
