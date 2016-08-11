package com.beijunyi.leetcode.ds;

import java.util.*;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int x) {
    val = x;
  }

  public static TreeNode fromArray(Integer... nums) {
    if(nums == null || nums.length == 0 || nums[0] == null) return null;
    TreeNode root = new TreeNode(nums[0]);
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    for(int i = 1; i < nums.length; i++) {
      Integer num = nums[i];
      TreeNode head = i % 2 == 1 ? q.peek() : q.poll();
      TreeNode tail = num != null ? new TreeNode(num) : null;
      if(i % 2 == 1) head.left = tail;
      else head.right = tail;
      if(tail != null) q.offer(tail);
    }
    return root;
  }

  public static Integer[] toArray(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    if(root != null) {
      Queue<TreeNode> q = new LinkedList<>();
      int nullCount = 0;
      q.offer(root);
      ret.add(root.val);
      while(!q.isEmpty()) {
        TreeNode next = q.poll();
        for(TreeNode child : Arrays.asList(next.left, next.right)) {
          if(child != null) {
            for(; nullCount > 0; nullCount--) ret.add(null);
            q.offer(child);
            ret.add(child.val);
          } else {
            nullCount++;
          }
        }
      }
    }
    return ret.toArray(new Integer[ret.size()]);
  }

}
