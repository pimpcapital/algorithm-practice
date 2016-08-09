package com.beijunyi.leetcode.ds;

import java.util.LinkedList;
import java.util.Queue;

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
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.offer(root);
    for(int i = 1; i < nums.length; i++) {
      Integer num = nums[i];
      TreeNode head = i % 2 == 1 ? nodes.peek() : nodes.poll();
      TreeNode tail = num != null ? new TreeNode(num) : null;
      if(i % 2 == 1) head.left = tail;
      else head.right = tail;
      if(tail != null) nodes.offer(tail);
    }
    return root;
  }
}
