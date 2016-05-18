package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *    1
 *   / \
 *  2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = 12 + 13 = 25.
 */
public class _129_SumRootToLeafNumbers implements Medium {

  public interface Solution {
    int sumNumbers(TreeNode root);
  }

  /**
   * Time: O(n*log(n))
   *  n is the total number of nodes.
   *  n string builder in total. Every string builder does log(n) appends. There are in total n copies of StringBuilder
   *  references.
   *
   * Space: O(n*log(n))
   */
  public static class Solution1 implements Solution {

    @Override
    public int sumNumbers(TreeNode root) {
      List<StringBuilder> paths = generatePaths(root);
      int ret = 0;
      for(StringBuilder path : paths) {
        path.reverse();
        ret += Integer.valueOf(path.toString());
      }
      return ret;
    }

    private static List<StringBuilder> generatePaths(TreeNode root) {
      if(root == null)
        return new ArrayList<>();
      List<StringBuilder> paths = generatePaths(root.left);
      paths.addAll(generatePaths(root.right));
      String val = Integer.toString(root.val);
      if(paths.isEmpty()) { // this is leaf
        paths.add(new StringBuilder(val));
      } else {
        for(StringBuilder sb : paths)
          sb.append(val); // add to end (to avoid array copying. will reverse later)
      }
      return paths;
    }

  }

  /**
   * Time: O(n)
   * Space: O(log(n))
   */
  public static class Solution2 implements Solution {

    @Override
    public int sumNumbers(TreeNode root) {
      return sum(root, 0);
    }

    public int sum(TreeNode n, int s) {
      if(n == null) return 0;
      s *= 10;
      if(n.right == null && n.left == null) return s + n.val;
      return sum(n.left, s + n.val) + sum(n.right, s + n.val);
    }

  }

  public static void main(String args[]) {
    TreeNode root;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      TreeNode n1 = new TreeNode(1);
      TreeNode n2 = new TreeNode(2);
      TreeNode n3 = new TreeNode(3);
      n1.left = n2;
      n1.right = n3;
      root = n1;
      result = s.sumNumbers(root);
      System.out.println(result);
    }
  }
}
