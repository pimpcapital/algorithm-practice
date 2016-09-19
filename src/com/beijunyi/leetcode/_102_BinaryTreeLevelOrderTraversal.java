package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 *   Given binary tree [3,9,20,null,null,15,7],
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 *   return its level order traversal as:
 *     [
 *       [3],
 *       [9,20],
 *       [15,7]
 *     ]
 */
public class _102_BinaryTreeLevelOrderTraversal implements Easy {

  public interface Solution {
    List<List<Integer>> levelOrder(TreeNode root);
  }

  public static class Solution1 implements Solution, BreadthFirstSearch {

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> ret = new ArrayList<>();
      if(root == null) return ret;

      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      q.offer(null);
      List<Integer> level = new ArrayList<>();

      while(!q.isEmpty()) {
        TreeNode next = q.poll();
        if(next != null) {
          level.add(next.val);
          if(next.left != null) q.offer(next.left);
          if(next.right != null) q.offer(next.right);
        } else {
          ret.add(level);
          if(!q.isEmpty()) q.offer(null);
          level = new ArrayList<>();
        }
      }
      return ret;
    }

  }

  public static class Solution2 implements Solution, DepthFirstSearch {
    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      addToLevel(res, root, 0);
      return res;
    }

    private static void addToLevel(List<List<Integer>> res, TreeNode root, int height) {
      if(root == null) return;
      if(height >= res.size()) {
        res.add(new LinkedList<>());
      }
      res.get(height).add(root.val);
      addToLevel(res, root.left, height+1);
      addToLevel(res, root.right, height+1);
    }
  }

  public static class Solution3 implements Solution, BreadthFirstSearch {
    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
      Queue<TreeNode> queue = new LinkedList<>();
      List<List<Integer>> wrapList = new LinkedList<>();

      if(root == null) return wrapList;

      queue.offer(root);
      while(!queue.isEmpty()){
        int levelNum = queue.size();
        List<Integer> subList = new LinkedList<>();
        for(int i=0; i<levelNum; i++) {
          if(queue.peek().left != null) queue.offer(queue.peek().left);
          if(queue.peek().right != null) queue.offer(queue.peek().right);
          subList.add(queue.poll().val);
        }
        wrapList.add(subList);
      }
      return wrapList;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      root = TreeNode.fromArray(3, 9, 20, null, null, 15, 7);
      result = s.levelOrder(root);
      System.out.println(result);
    }

  }

}
