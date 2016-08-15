package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
 * column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples:
 *
 *   Given binary tree [3,9,20,null,null,15,7],
 *        3
 *       /\
 *      /  \
 *     9   20
 *         /\
 *        /  \
 *       15  7
 *   return its vertical order traversal as:
 *     [
 *       [9],
 *       [3,15],
 *       [20],
 *       [7]
 *     ]
 *
 *   Given binary tree [3,9,8,4,0,1,7],
 *          3
 *         /\
 *        /  \
 *       9    8
 *      /\   /\
 *     /  \ /  \
 *    4   01   7
 *   return its vertical order traversal as:
 *     [
 *       [4],
 *       [9],
 *       [3,0,1],
 *       [8],
 *       [7]
 *     ]
 *
 *   Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 *              3
 *             /\
 *            /  \
 *           9    8
 *          /\   /\
 *         /  \ /  \
 *        4   01   7
 *            /\
 *           /  \
 *          5   2
 *   return its vertical order traversal as:
 *     [
 *       [4],
 *       [9,5],
 *       [3,0,1],
 *       [8,2],
 *       [7]
 *     ]
 */
public class _314_BinaryTreeVerticalOrderTraversal implements Medium, PremiumQuestion {

  public interface Solution {
    List<List<Integer>> verticalOrder(TreeNode root);
  }

  public static class Solution1 implements Solution, BreadthFirstSearch {
    @Override
    public List<List<Integer>> verticalOrder(TreeNode root) {
      Map<Integer, List<Integer>> valsAtPosMap = new HashMap<>();
      int[] boundary = new int[2];
      verticalOrder(root, valsAtPosMap, boundary);
      List<List<Integer>> ret = new ArrayList<>();
      if(!valsAtPosMap.isEmpty()) {
        for(int i = boundary[0]; i <= boundary[1]; i++) {
          ret.add(valsAtPosMap.get(i));
        }
      }
      return ret;
    }

    private static void verticalOrder(TreeNode root, Map<Integer, List<Integer>> result, int[] boundary) {
      if(root == null) return;
      Queue<TreeNode> q = new LinkedList<>();
      Queue<Integer> pos = new LinkedList<>();
      q.offer(root);
      pos.offer(0);
      while(!q.isEmpty()) {
        TreeNode node = q.poll();
        int p = pos.poll();

        List<Integer> valsAtCol = result.get(p);
        if(valsAtCol == null) {
          valsAtCol = new ArrayList<>();
          result.put(p, valsAtCol);
        }
        valsAtCol.add(node.val);

        boundary[0] = Math.min(p, boundary[0]);
        boundary[1] = Math.max(p, boundary[1]);

        if(node.left != null) {
          q.offer(node.left);
          pos.offer(p - 1);
        }

        if(node.right != null) {
          q.offer(node.right);
          pos.offer(p + 1);
        }
      }
    }
  }

  public static class Solution2 implements Solution, DepthFirstSearch {

    @Override
    public List<List<Integer>> verticalOrder(TreeNode root) {
      Map<Integer, SortedMap<Integer, List<Integer>>> valsAtColRowMap = new HashMap<>();
      int[] boundary = new int[2];
      verticalOrder(root, 0, 0, valsAtColRowMap, boundary);
      List<List<Integer>> ret = new ArrayList<>();
      if(!valsAtColRowMap.isEmpty()) {
        for(int col = boundary[0]; col <= boundary[1]; col++) {
          List<Integer> allValsAtCol = new ArrayList<>();
          for(List<Integer> valsAtColRow : valsAtColRowMap.get(col).values()) {
            allValsAtCol.addAll(valsAtColRow);
          }
          ret.add(allValsAtCol);
        }
      }
      return ret;
    }

    private static void verticalOrder(TreeNode root, int col, int row, Map<Integer, SortedMap<Integer, List<Integer>>> result, int[] boundary) {
      if(root == null) return;
      SortedMap<Integer, List<Integer>> valsAtCol = result.get(col);
      if(valsAtCol == null) {
        valsAtCol = new TreeMap<>();
        result.put(col, valsAtCol);
      }
      List<Integer>  valsAtColRow = valsAtCol.get(row);
      if(valsAtColRow == null) {
        valsAtColRow = new ArrayList<>();
        valsAtCol.put(row, valsAtColRow);
      }
      valsAtColRow.add(root.val);

      boundary[0] = Math.min(col, boundary[0]);
      boundary[1] = Math.max(col, boundary[1]);
      verticalOrder(root.left, col - 1, row + 1, result, boundary);
      verticalOrder(root.right, col + 1, row + 1, result, boundary);
    }
  }

  public static class Solution3 implements Solution, BreadthFirstSearch {
    @Override
    public List<List<Integer>> verticalOrder(TreeNode root) {
      int[] range = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
      findRange(root, 0, range);
      List<List<Integer>> ret = prepareNestedList(range);
      if(root == null) return ret;

      Queue<TreeNode> q = new LinkedList<>();
      Queue<Integer> idx = new LinkedList<>();
      idx.add(-range[0]);
      q.add(root);
      while(!q.isEmpty()){
        TreeNode node = q.poll();
        int i = idx.poll();
        ret.get(i).add(node.val);
        if(node.left != null){
          q.add(node.left);
          idx.add(i - 1);
        }
        if(node.right != null){
          q.add(node.right);
          idx.add(i + 1);
        }
      }

      return ret;
    }

    private static List<List<Integer>> prepareNestedList(int[] range) {
      List<List<Integer>> ret = new ArrayList<>();
      for(int i = range[0]; i <= range[1]; i++) {
        ret.add(new ArrayList<Integer>());
      }
      return ret;
    }

    private static void findRange(TreeNode root, int pos, int[] range) {
      if(root == null) return;
      range[0] = Math.min(range[0], pos);
      range[1] = Math.max(range[1], pos);
      findRange(root.left, pos - 1, range);
      findRange(root.right, pos + 1, range);
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      System.out.println(s.getClass().getSimpleName() + ":");
      System.out.println("[");
      root = TreeNode.fromArray();
      result = s.verticalOrder(root);
      for(List<Integer> col : result) {
        System.out.println("  " + col);
      }
      System.out.println("]");
      System.out.println();

      System.out.println("[");
      root = TreeNode.fromArray(3, 9, 20, null, null, 15, 7);
      result = s.verticalOrder(root);
      for(List<Integer> col : result) {
        System.out.println("  " + col);
      }
      System.out.println("]");
      System.out.println();

      System.out.println("[");
      root = TreeNode.fromArray(3, 9, 8, 4, 0, 1, 7, null, null, null, 2, 5);
      result = s.verticalOrder(root);
      for(List<Integer> col : result) {
        System.out.println("  " + col);
      }
      System.out.println("]");
      System.out.println();
    }
  }

}
