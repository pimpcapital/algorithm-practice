package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the
 * node's value. If it is a null node, we record using a sentinel value such as #.
 *        _9_
 *       /   \
 *      3     2
 *     / \   / \
 *    4   1  #  6
 *   / \ / \   / \
 *   # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a
 * null node.
 *
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary
 * tree. Find an algorithm without reconstructing the tree.
 *
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such
 * as "1,,3".
 *
 * Example 1:
 *   "9,3,4,#,#,1,#,#,2,#,6,#,#"
 *   Return true
 *
 * Example 2:
 *   "1,#"
 *   Return false
 *
 * Example 3:
 *   "9,#,#,1"
 *   Return false
 */
public class _331_VerifyPreorderSerializationOfABinaryTree implements Medium {

  public interface Solution {
    boolean isValidSerialization(String preorder);
  }

  /**
   * 1. values of numbers don't matter. there are only 2 types of nodes here: null vs non-null
   * 2. starting with 1 slot
   * 3. when you add a non-null node, you fill one existing slot and create 2 new slots.
   * 4. when you add a null node, you fill one slot and add no new slots.
   * 5. keep counting the total number of slots
   * 6. when slot == 0, it either ends with success (no more node to process), or with failure (there are more nodes)
   * 7. when all nodes are processed, check if all slots are filled
   */
  public static class Solution1 implements Solution {
    @Override
    public boolean isValidSerialization(String preorder) {
      String[] tokens = preorder.split(",");
      int nulls = 1;
      for(int i = 0; i < tokens.length; i++) {
        String token = tokens[i];
        switch(token) {
          case "#":
            if(--nulls == 0) return i == tokens.length - 1;
            break;
          default:
            nulls++;
        }
      }
      return nulls == 0;
    }
  }

  /**
   * Some used stack. Some used the depth of a stack. Here I use a different perspective. In a binary tree, if we
   * consider null as leaves, then
   *
   * all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
   * all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
   * Suppose we try to build this tree. During building, we record the difference between out degree and in
   *   degreediff = outdegree - indegree.
   * When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not
   * null, we increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never
   * be negative and diff will be zero when finished.
   */
  public static class Solution2 implements Solution {
    @Override
    public boolean isValidSerialization(String preorder) {
      String[] nodes = preorder.split(",");
      int diff = 1;
      for (String node: nodes) {
        if (--diff < 0) return false;
        if (!node.equals("#")) diff += 2;
      }
      return diff == 0;
    }
  }

  public static void main(String args[]) {
    String preorder;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      preorder = "#,#";
      result = s.isValidSerialization(preorder);
      System.out.println(result);

      preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
      result = s.isValidSerialization(preorder);
      System.out.println(result);

      preorder = "1,#";
      result = s.isValidSerialization(preorder);
      System.out.println(result);

      preorder = "9,#,#,1";
      result = s.isValidSerialization(preorder);
      System.out.println(result);
    }
  }


}
