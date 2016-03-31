package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.beijunyi.leetcode.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *         5
 *        / \
 *       4   8
 *      /   / \
 *     11  13  4
 *    /  \    / \
 *   7    2  5   1
 * return
 * [
 *   [  5,  4, 11,  2],
 *   [  5,  8,  4,  5]
 * ]
 */
public class _113_PathSumTwo implements Medium {

  public static class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
      List<List<Integer>> ret = new ArrayList<>();
      updatePath(root, sum, new ArrayList<Integer>(), ret);
      return ret;
    }

    private void updatePath(TreeNode root, int sum, List<Integer> current, List<List<Integer>> result) {
      if(root == null)
        return;
      sum -= root.val;
      if(sum == 0 && root.left == null && root.right == null) {
        List<Integer> clone = new ArrayList<>(current);
        clone.add(root.val);
        result.add(clone);
      } else {
        current.add(root.val);
        updatePath(root.left, sum, current, result);
        updatePath(root.right, sum, current, result);
        current.remove(current.size() - 1);
      }
    }
  }

  public static void main(String args[]) {
    TreeNode root = new TreeNode(5);
    TreeNode l11 = new TreeNode(4);
    TreeNode l12 = new TreeNode(8);
    root.left = l11;
    root.right = l12;
    TreeNode l21 = new TreeNode(11);
    TreeNode l22 = new TreeNode(13);
    TreeNode l23 = new TreeNode(4);
    l11.left = l21;
    l12.left = l22;
    l12.right = l23;
    TreeNode l31 = new TreeNode(7);
    TreeNode l32 = new TreeNode(2);
    TreeNode l33 = new TreeNode(5);
    TreeNode l34 = new TreeNode(1);
    l21.left = l31;
    l21.right = l32;
    l23.left = l33;
    l23.right = l34;
    System.out.println(new Solution().pathSum(root, 22));
  }
}
