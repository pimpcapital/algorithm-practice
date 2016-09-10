package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.*;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization and
 * deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following tree
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class _297_SerializeAndDeserializeBinaryTree implements Hard {

  public interface Solution {

    // Encodes a tree to a single string.
    String serialize(TreeNode root);

    // Decodes your encoded data to tree.
    TreeNode deserialize(String data);
  }

  public static class Solution1 implements Solution, Iterative, BreadthFirstSearch {

    @Override
    public String serialize(TreeNode root) {
      if(root == null) return "";
      StringBuilder sb = new StringBuilder();
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      int nulls = 0;
      while(!q.isEmpty()) {
        TreeNode node = q.poll();
        if(node != null) {
          for(; nulls > 0; nulls--) sb.append("N,");
          sb.append(node.val).append(",");
          q.offer(node.left);
          q.offer(node.right);
        } else {
          nulls++;
        }
      }
      return sb.toString();
    }

    @Override
    public TreeNode deserialize(String data) {
      if(data.isEmpty()) return null;
      String[] tokens = data.split(",");
      TreeNode root = new TreeNode(Integer.valueOf(tokens[0]));
      Queue<TreeNode> q = new LinkedList<>();
      TreeNode current = root;
      for(int i = 1; i < tokens.length; i++) {
        String token = tokens[i];
        TreeNode next = "N".equals(token) ? null : new TreeNode(Integer.valueOf(token));
        if(i % 2 == 1) current.left = next;
        else current.right = next;
        if(next != null) q.offer(next);
        if(i % 2 == 0) current = q.poll();
      }
      return root;
    }
  }

  public static class Solution2 implements Solution, Recursive, DepthFirstSearch {

    private static final String NULL = "N";

    @Override
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      buildString(root, sb);
      return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
      if (node == null) {
        sb.append(NULL).append(",");
      } else {
        sb.append(node.val).append(",");
        buildString(node.left, sb);
        buildString(node.right,sb);
      }
    }
    @Override
    public TreeNode deserialize(String data) {
      Queue<String> nodes = new LinkedList<>();
      nodes.addAll(Arrays.asList(data.split(",")));
      return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
      String val = nodes.remove();
      if (val.equals(NULL)) return null;
      else {
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
      }
    }
  }

  public static void main(String args[]) {
    TreeNode origin;
    String serialized;
    TreeNode deserialized;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      origin = TreeNode.fromArray(1, 2, 3, null, null, 4, 5);
      serialized = s.serialize(origin);
      deserialized = s.deserialize(serialized);
      System.out.println(deserialized);
    }
  }

}
