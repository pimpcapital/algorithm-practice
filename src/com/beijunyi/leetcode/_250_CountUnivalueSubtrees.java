package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 *   Given binary tree,
 *         5
 *        / \
 *       1   5
 *      / \   \
 *     5   5   5
 *   return 4.
 */
public class _250_CountUnivalueSubtrees implements Medium, PremiumQuestion {

  public interface Solution {
    int countUnivalSubtrees(TreeNode root);
  }

  public static class Solution1 implements Solution, Recursive {
    @Override
    public int countUnivalSubtrees(TreeNode root) {
      return Math.abs(univalCount(root));
    }

    private static int univalCount(TreeNode root) { // abs value = count, positive if all unified
      if(root == null) return 0;
      int leftCount = univalCount(root.left);
      int rightCount = univalCount(root.right);
      int total = Math.abs(leftCount) + Math.abs(rightCount);
      if(leftCount >= 0 && rightCount >= 0
           && (root.left == null || root.left.val == root.val)
           && (root.right == null || root.right.val == root.val) ) {
        return total + 1;
      } else {
        return -total;
      }
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(5, 1, 5, 5, 5, 5);
      result = s.countUnivalSubtrees(root);
      System.out.println(result);
    }
  }

}
