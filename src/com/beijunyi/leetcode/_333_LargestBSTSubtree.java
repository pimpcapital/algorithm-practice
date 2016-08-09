package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with
 * largest number of nodes in it.
 *
 * Note:
 *   A subtree must include all of its descendants.
 *   Here's an example:
 *         10
 *        / \
 *       5  15
 *      / \   \
 *     1   8   7
 *   The Largest BST Subtree in this case is:
 *       5
 *      / \
 *     1   8
 *   The return value is the subtree's size, which is 3.
 *
 * Hint:
 *   You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will
 *   result in O(nlogn) time complexity.
 *
 * Follow up:
 *   Can you figure out ways to solve it with O(n) time complexity?
 */
public class _333_LargestBSTSubtree implements Medium, PremiumQuestion {

  public interface Solution {
    int largestBSTSubtree(TreeNode root);
  }

  public static class Solution1 implements Solution {

    @Override
    public int largestBSTSubtree(TreeNode root) {
      return Math.abs(findLargestBSTSubtree(root)[0]);
    }

    private static int[] findLargestBSTSubtree(TreeNode root) {
      if(root == null) return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE}; // size, min, max
      int[] left = findLargestBSTSubtree(root.left);
      int[] right = findLargestBSTSubtree(root.right);
      int[] ret = new int[3];
      if(left[0] >= 0 && right[0] >= 0 // descendants are all BST
           && left[2] <= root.val // largest value on left <= current
           && right[1] > root.val) { // smallest value on right > current
        ret[0] = left[0] + right[0] + 1;
      } else {
        ret[0] = -Math.max(Math.abs(left[0]), Math.abs(right[0]));
      }
      ret[1] = Math.min(root.val, Math.min(left[1], right[1]));
      ret[2] = Math.max(root.val, Math.max(left[2], right[2]));
      return ret;
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(10, 5, 15, 1, 8, 7);
      result = s.largestBSTSubtree(root);
      System.out.println(result);

      root = TreeNode.fromArray(2, 3, null, 1);
      result = s.largestBSTSubtree(root);
      System.out.println(result);

      root = TreeNode.fromArray(1, 4, 3, 2);
      result = s.largestBSTSubtree(root);
      System.out.println(result);

      root = TreeNode.fromArray(4, 1, null, 2, null, 3);
      result = s.largestBSTSubtree(root);
      System.out.println(result);

    }

  }

}
