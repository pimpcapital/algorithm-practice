package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level
 * by level from leaf to root).
 *
 * For example:
 *   Given binary tree [3,9,20,null,null,15,7],
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 *   return its bottom-up level order traversal as:
 *     [
 *       [15,7],
 *       [9,20],
 *       [3]
 *     ]
 */
public class _107_BinaryTreeLevelOrderTraversalTwo implements Easy {

  public interface Solution {
    List<List<Integer>> levelOrderBottom(TreeNode root);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
      List<List<Integer>> ret = new ArrayList<>();
      if(root == null) return ret;

      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      int levelSize = 1;
      List<Integer> level = new ArrayList<>(levelSize);
      int nextLevelSize = 0;
      while(!q.isEmpty()) {
        TreeNode next = q.poll();
        levelSize--;
        level.add(next.val);
        if(next.left != null) {
          q.offer(next.left);
          nextLevelSize++;
        }
        if(next.right != null) {
          q.offer(next.right);
          nextLevelSize++;
        }
        if(levelSize == 0) {
          levelSize = nextLevelSize;
          nextLevelSize = 0;
          ret.add(level);
          level = new ArrayList<>(levelSize);
        }
      }
      Collections.reverse(ret);
      return ret;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(3, 9, 20, null, null, 15, 7);
      result = s.levelOrderBottom(root);
      System.out.println(result);
    }
  }

}
