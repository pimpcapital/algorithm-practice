package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 *        _______6______
 *       /              \
 *    ___2__          ___8__
 *   /      \        /      \
 *   0      _4       7       9
 *         /  \
 *        3   5
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
 * since a node can be a descendant of itself according to the LCA definition.
 */
public class _235_LowestCommonAncestorOfABinarySearchTree implements Easy {

  public interface Solution {
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
  }

  public static class Solution1 implements Solution, DepthFirstSearch, Recursive {
    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root == null || root == p || root == q) return root;
      TreeNode left = lowestCommonAncestor(root.left, p, q);
      TreeNode right = lowestCommonAncestor(root.right, p, q);
      if(left != null && right != null) return root;
      return left != null ? left : right;
    }
  }

  public static class Solution2 implements Solution, Recursive {
    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root == null || root == p || root == q) return root;
      if(root.val >= p.val) {
        if(root.val >= q.val) return lowestCommonAncestor(root.left, p, q);
        return root;
      } else {
        if(root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
      }
    }
  }

  public static class Solution3 implements Solution, Iterative {
    @Override
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      while(true) {
        if(root.val > p.val && root.val > q.val)
          root = root.left;
        else if (root.val < p.val && root.val < q.val)
          root = root.right;
        else
          return root;
      }
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    TreeNode p;
    TreeNode q;
    TreeNode result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      root = TreeNode.fromArray(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
      p = root.left;
      q = root.right;
      result = s.lowestCommonAncestor(root, p, q);
      System.out.println(result);
    }
  }

}
