package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Note:
 *   Given target value is a floating point.
 *   You may assume k is always valid, that is: k ≤ total nodes.
 *   You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Follow up:
 *   Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 *
 * Hint:
 *   1. Consider implement these two helper functions:
 *   2. getPredecessor(N), which returns the next smaller node to N.
 *   3. getSuccessor(N), which returns the next larger node to N.
 *   4. Try to assume that each node has a parent pointer, it makes the problem much easier.
 *   5. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
 *   6. You would need two stacks to track the path in finding predecessor and successor node separately.
 */
public class _272_ClosestBinarySearchTreeValueTwo implements Hard, PremiumQuestion {

  public interface Solution {
    List<Integer> closestKValues(TreeNode root, double target, int k);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, Collections.<Integer>reverseOrder());
      closestKValues(root, target, k, minHeap, maxHeap, Integer.MIN_VALUE, Integer.MAX_VALUE);
      return new ArrayList<>(minHeap);
    }

    private static void closestKValues(TreeNode root, double target, int k, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int min, int max) {
      if(root == null) return;
      tryInsert(root.val, target, k, minHeap, maxHeap);
      if(minHeap.size() < k || !(minHeap.peek() >= target && min >= maxHeap.peek())) {
        closestKValues(root.left, target, k, minHeap, maxHeap, min, Math.min(root.val, max));
      }
      if(maxHeap.size() < k || !(maxHeap.peek() <= target && max <= minHeap.peek())) {
        closestKValues(root.right, target, k, minHeap, maxHeap, Math.max(root.val, min), max);
      }
    }

    private static void tryInsert(int val, double target, int k, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
      boolean insert = minHeap.size() < k;
      if(!insert) {
        double delta = Math.abs(target - val);
        double minDelta = Math.abs(target - minHeap.peek());
        double maxDelta = Math.abs(target - maxHeap.peek());
        insert = delta < Math.max(minDelta, maxDelta);
        if(insert) {
          if(minDelta < maxDelta) minHeap.remove(maxHeap.poll());
          else maxHeap.remove(minHeap.poll());
        }
      }
      if(insert) {
        minHeap.offer(val);
        maxHeap.offer(val);
      }
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
      List<Integer> res = new ArrayList<>();

      Stack<Integer> s1 = new Stack<>(); // predecessors
      Stack<Integer> s2 = new Stack<>(); // successors

      inorder(root, target, false, s1);
      inorder(root, target, true, s2);

      while (k-- > 0) {
        if (s1.isEmpty())
          res.add(s2.pop());
        else if (s2.isEmpty())
          res.add(s1.pop());
        else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
          res.add(s1.pop());
        else
          res.add(s2.pop());
      }

      return res;
    }

    // inorder traversal
    private void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
      if (root == null) return;

      inorder(reverse ? root.right : root.left, target, reverse, stack);
      // early terminate, no need to traverse the whole tree
      if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
      // track the value of current node
      stack.push(root.val);
      inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
  }

  public static class Solution3 implements Solution {
    @Override
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
      List<Integer> ret = new LinkedList<>();
      Stack<TreeNode> succ = new Stack<>();
      Stack<TreeNode> pred = new Stack<>();
      initializePredecessorStack(root, target, pred);
      initializeSuccessorStack(root, target, succ);
      if(!succ.isEmpty() && !pred.isEmpty() && succ.peek().val == pred.peek().val) {
        getPredecessor(pred);
      }
      while(k-- > 0) {
        if(succ.isEmpty()) {
          ret.add(getPredecessor(pred));
        } else if(pred.isEmpty()) {
          ret.add(getSuccessor(succ));
        } else {
          double succDiff = Math.abs(target - succ.peek().val);
          double predDiff = Math.abs(target - pred.peek().val);
          if(succDiff < predDiff) {
            ret.add(getSuccessor(succ));
          } else {
            ret.add(getPredecessor(pred));
          }
        }
      }
      return ret;
    }

    private static void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> succ) {
      while(root != null) {
        if(root.val == target) {
          succ.push(root);
          break;
        } else if(root.val > target) {
          succ.push(root);
          root = root.left;
        } else {
          root = root.right;
        }
      }
    }

    private static void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> pred) {
      while(root != null) {
        if(root.val == target) {
          pred.push(root);
          break;
        } else if(root.val < target) {
          pred.push(root);
          root = root.right;
        } else{
          root = root.left;
        }
      }
    }

    private static int getSuccessor(Stack<TreeNode> succ) {
      TreeNode curr = succ.pop();
      int ret = curr.val;
      curr = curr.right;
      while(curr != null) {
        succ.push(curr);
        curr = curr.left;
      }
      return ret;
    }

    private static int getPredecessor(Stack<TreeNode> pred) {
      TreeNode curr = pred.pop();
      int ret = curr.val;
      curr = curr.left;
      while(curr != null) {
        pred.push(curr);
        curr = curr.right;
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    double target;
    int k;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      root = TreeNode.fromArray(1, null, 2);
      target = 3.14;
      k = 1;
      result = s.closestKValues(root, target, k);
      System.out.println(result);

      root = TreeNode.fromArray(4, 2, 5, 1, 3);
      target = 4.857143;
      k = 3;
      result = s.closestKValues(root, target, k);
      System.out.println(result);

      root = TreeNode.fromArray(3, 1, 4, null, 2);
      target = 2.00;
      k = 1;
      result = s.closestKValues(root, target, k);
      System.out.println(result);

      root = TreeNode.fromArray(8, 5, 12, 1, 6, null, 9);
      target = 5.66;
      k = 3;
      result = s.closestKValues(root, target, k);
      System.out.println(result);

      root = TreeNode.fromArray(45, 30, 46, 10, 36, null, 49, 8, 24, 34, 42, 48, null, 4, 9, 14, 25, 31, 35, 41, 43, 47, null, 0, 6, null, null, 11, 20, null, 28, null, 33, null, null, 37, null, null, 44, null, null, null, 1, 5, 7, null, 12, 19, 21, 26, 29, 32, null, null, 38, null, null, null, 3, null, null, null, null, null, 13, 18, null, null, 22, null, 27, null, null, null, null, null, 39, 2, null, null, null, 15, null, null, 23, null, null, null, 40, null, null, null, 16, null, null, null, null, null, 17);
      target = 1.428571;
      k = 7;
      result = s.closestKValues(root, target, k);
      System.out.println(result);

      root = TreeNode.fromArray(27, 17, 33, 15, 26, 32, 45, 7, 16, 23, null, 31, null, 40, 49, 5, 14, null, null, 21, 25, 29, null, 34, 41, 48, null, 2, 6, 10, null, 20, 22, 24, null, 28, 30, null, 39, null, 42, 46, null, 1, 4, null, null, 9, 11, 18, null, null, null, null, null, null, null, null, null, 37, null, null, 44, null, 47, 0, null, 3, null, 8, null, null, 13, null, 19, 35, 38, 43, null, null, null, null, null, null, null, null, null, 12, null, null, null, null, 36);
      target = 0.00;
      k = 32;
      result = s.closestKValues(root, target, k);
      System.out.println(result);

      System.out.println();
    }

  }

}
