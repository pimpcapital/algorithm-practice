package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 *   Given binary tree {1,#,2,3},
 *     1
 *      \
 *       2
 *      /
 *     3
 *   return [3,2,1].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class _145_BinaryTreePostorderTraversal implements Hard {

  public interface Solution {
    List<Integer> postorderTraversal(TreeNode root);
  }

  public static class Solution1 implements Solution, Recursive {

    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> ret = new ArrayList<>();
      postorderTraversal(root, ret);
      return ret;
    }

    private static void postorderTraversal(TreeNode node, List<Integer> result) {
      if(node == null) return;
      postorderTraversal(node.left, result);
      postorderTraversal(node.right, result);
      result.add(node.val);
    }

  }

  public static class Solution2 implements Solution, Iterative {
    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> ret = new ArrayList<>();
      if(root == null) return ret;

      Stack<State> stack = new Stack<>();
      stack.push(new State(root, false, false));

      while(!stack.isEmpty()) {
        State state = stack.pop();
        TreeNode node = state.node;
        if(node == null) continue;
        if(state.completedRight) {
          ret.add(node.val);
        } else if(state.completedLeft) {
          stack.push(new State(node, true, true));
          stack.push(new State(node.right, false, false));
        } else {
          stack.push(new State(node, true, false));
          stack.push(new State(node.left, false, false));
        }
      }

      return ret;
    }

    private static class State {
      final TreeNode node;
      final boolean completedLeft;
      final boolean completedRight;

      State(TreeNode node, boolean completedLeft, boolean completedRight) {
        this.node = node;
        this.completedLeft = completedLeft;
        this.completedRight = completedRight;
      }
    }
  }

  public static class Solution3 implements Solution {
    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> ret = new ArrayList<>();

      Stack<TreeNode> stack = new Stack<>();
      Stack<TreeNode> rights = new Stack<>();
      pushAllLefts(root, stack);

      while(!stack.isEmpty()) {
        TreeNode next = stack.peek();
        if(!rights.isEmpty() && rights.peek() == next) {
          stack.pop();
          rights.pop();
          ret.add(next.val);
        } else {
          rights.push(next);
          pushAllLefts(next.right, stack);
        }
      }

      return ret;
    }

    private static void pushAllLefts(TreeNode node, Stack<TreeNode> stack) {
      while(node != null) {
        stack.push(node);
        node = node.left;
      }
    }
  }

  /**
   * Basic idea is using preorder traversal but traverse right child node first, then left child node.
   * Right->Left->Curr, then reverse the result
   */
  public static class Solution4 implements Solution {
    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
      LinkedList<Integer> ret = new LinkedList<>();
      Stack<TreeNode> stack = new Stack<>();
      if(root == null) return ret;

      stack.push(root);
      while(!stack.isEmpty()) {
        TreeNode cur = stack.pop();
        ret.addFirst(cur.val);
        if(cur.left != null) stack.push(cur.left);
        if(cur.right != null) stack.push(cur.right);
      }
      return ret;
    }
  }

  public static void main(String args[]) {
    TreeNode root;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3(), new Solution4())) {
      root = TreeNode.fromArray(1, null, 2, 3);
      result = s.postorderTraversal(root);
      System.out.println(result);
    }
  }

}
