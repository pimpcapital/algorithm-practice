package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */
public class _270_ClosestBinarySearchTreeValue implements Easy, PremiumQuestion {

  public interface Solution {
    int closestValue(TreeNode root, double target);
  }

  public static class Solution1 implements Solution {

    @Override
    public int closestValue(TreeNode root, double target) {
      int nextClosest = root.val;

      if(target > root.val && root.right != null)
        nextClosest = closestValue(root.right, target);

      if(target < root.val && root.left != null)
        nextClosest = closestValue(root.left, target);

      if(Math.abs(target - nextClosest) < Math.abs(target - root.val)) return nextClosest;
      return root.val;
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    double target;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      root = TreeNode.fromArray(8, 5, 12, 1, 6, null, 8);
      target = 5.7;
      result = s.closestValue(root, target);
      System.out.println(result);
    }
  }

}
