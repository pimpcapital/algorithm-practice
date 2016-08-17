package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child
 * connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * For example,
 *   1
 *    \
 *     3
 *    / \
 *   2   4
 *        \
 *         5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *     2
 *      \
 *       3
 *      /
 *     2
 *    /
 *   1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
public class _298_BinaryTreeLongestConsecutiveSequence {

  public interface Solution {
    int longestConsecutive(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public int longestConsecutive(TreeNode root) {
      return Math.abs(longestAndCurrentStreak(root)[0]);
    }

    private static int[] longestAndCurrentStreak(TreeNode root) {
      if(root == null) return new int[2]; // longest streak, current streak
      int[] fromLeft = longestAndCurrentStreak(root.left);
      int[] fromRight = longestAndCurrentStreak(root.right);
      int currentStreak = 0;
      if(root.left != null && root.left.val == root.val + 1) currentStreak = Math.max(currentStreak, fromLeft[1]);
      if(root.right != null && root.right.val == root.val + 1) currentStreak = Math.max(currentStreak, fromRight[1]);
      currentStreak++;
      int longestStreak = Math.abs(fromLeft[0]) > Math.abs(fromRight[0]) ? fromLeft[0] : fromRight[0];
      if(Math.abs(longestStreak) <= currentStreak) longestStreak = currentStreak;
      return new int[] {longestStreak, currentStreak};
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public int longestConsecutive(TreeNode root) {
      if(root == null) return 0;
      return longestConsecutive(root, root.val, 0, 0);
    }

    private static int longestConsecutive(TreeNode node, int parent, int streak, int max) {
      if(node == null) return max;
      streak = node.val == parent + 1 ? streak + 1 : 1;
      max = Math.max(streak, max);
      return Math.max(
        longestConsecutive(node.left, node.val, streak, max),
        longestConsecutive(node.right, node.val, streak, max)
      );
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      root = TreeNode.fromArray(1, null, 3, 2, 4, null, null, null, 5);
      result = s.longestConsecutive(root);
      System.out.println(result);

      root = TreeNode.fromArray(2, null, 3, 2, null, 1);
      result = s.longestConsecutive(root);
      System.out.println(result);

      System.out.println();
    }


  }

}
