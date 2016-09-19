package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see
 * ordered from top to bottom.
 *
 * For example:
 * Given the following binary tree,
 *      1            <---
 *    /   \
 *   2     3         <---
 *    \     \
 *     5     4       <---
 * You should return [1, 3, 4].
 */
public class _199_BinaryTreeRightSideView implements Medium {

  public interface Solution {
    List<Integer> rightSideView(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<Integer> rightSideView(TreeNode root) {
      List<Integer> ret = new ArrayList<>();
      if(root == null) return ret;

      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      int levelCount = 1;

      int nextLevelCount = 0;
      while(levelCount > 0) {
        TreeNode next = q.poll();
        if(next.left != null) {
          q.offer(next.left);
          nextLevelCount++;
        }
        if(next.right != null) {
          q.offer(next.right);
          nextLevelCount++;
        }
        if(--levelCount == 0) {
          ret.add(next.val);
          levelCount = nextLevelCount;
          nextLevelCount = 0;
        }
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(1, 2);
      result = s.rightSideView(root);
      System.out.println(result);
    }

  }

}
