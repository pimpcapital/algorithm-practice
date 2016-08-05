package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 *
 * Example:
 *   Given binary tree
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 * Returns [4, 5, 3], [2], [1].
 *
 * Explanation:
 *   1. Removing the leaves [4, 5, 3] would result in this tree:
 *     1
 *    /
 *   2
 *
 *   2. Now removing the leaf [2] would result in this tree:
 *     1
 *
 *   3. Now removing the leaf [1] would result in the empty tree:
 *     []
 *
 * Returns [4, 5, 3], [2], [1].
 */
public class _366_FindLeavesOfBinaryTree implements Medium, PremiumQuestion {

  public interface Solution {
    List<List<Integer>> findLeaves(TreeNode root);
  }

  public static class Solution1 implements Solution, Recursive {

    @Override
    public List<List<Integer>> findLeaves(TreeNode root) {
      int depth = getDepth(root);
      List<List<Integer>> ret = new ArrayList<>(depth);
      for(int i = 0; i < depth; i++) {
        ret.add(new ArrayList<Integer>());
      }
      insertLeaves(root, ret);
      return ret;
    }

    private static int getDepth(TreeNode root) {
      if(root == null) return 0;
      return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    private static int insertLeaves(TreeNode root, List<List<Integer>> result) {
      if(root == null) return 0;
      int insertPos = Math.max(insertLeaves(root.left, result), insertLeaves(root.right, result));
      result.get(insertPos).add(root.val);
      return insertPos + 1;
    }

  }

  public static class Solution2 implements Solution, Recursive {

    @Override
    public List<List<Integer>> findLeaves(TreeNode root) {
      List<List<Integer>> ret = new ArrayList<>();
      insertLeaves(root, ret);
      return ret;
    }

    private static int insertLeaves(TreeNode node, List<List<Integer>> result) {
      if(null == node) return -1;
      int height = 1 + Math.max(insertLeaves(node.left, result), insertLeaves(node.right, result));
      if(result.size() < height + 1)  result.add(new ArrayList<Integer>());
      result.get(height).add(node.val);
      return height;
    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      TreeNode n1 = new TreeNode(1);
      TreeNode n2 = new TreeNode(2);
      TreeNode n3 = new TreeNode(3);
      TreeNode n4 = new TreeNode(4);
      TreeNode n5 = new TreeNode(5);
      n1.left = n2;
      n1.right = n3;
      n2.left = n4;
      n2.right = n5;
      List<List<Integer>> result = s.findLeaves(n1);
      for(List<Integer> leaves : result) {
        System.out.println(leaves);
      }
      System.out.println();
    }

  }

}
