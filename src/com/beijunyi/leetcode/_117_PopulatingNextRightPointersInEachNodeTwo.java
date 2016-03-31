package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;
import com.beijunyi.leetcode.ds.TreeLinkNode;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *       1
 *     /   \
 *    2     3
 *   / \     \
 *  4   5     7
 * After calling your function, the tree should look like:
 *       1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \    \
 *  4-> 5 -> 7 -> NULL
 */
public class _117_PopulatingNextRightPointersInEachNodeTwo implements Hard {

  public static class Solution1 {

    //based on level order traversal
    public void connect(TreeLinkNode root) {

      TreeLinkNode head = null; //head of the next level
      TreeLinkNode prev = null; //the leading node on the next level
      TreeLinkNode cur = root;  //current node of current level

      while (cur != null) {

        while (cur != null) { //iterate on the current level
          //left child
          if (cur.left != null) {
            if (prev != null) {
              prev.next = cur.left;
            } else {
              head = cur.left;
            }
            prev = cur.left;
          }
          //right child
          if (cur.right != null) {
            if (prev != null) {
              prev.next = cur.right;
            } else {
              head = cur.right;
            }
            prev = cur.right;
          }
          //move to next node
          cur = cur.next;
        }

        //move to next level
        cur = head;
        head = null;
        prev = null;
      }

    }
  }

  public static class Solution2 {
    public void connect(TreeLinkNode root) {
      TreeLinkNode level = root;
      while(level != null) {
        TreeLinkNode cur = level;
        TreeLinkNode nextLevel = null;
        TreeLinkNode prevRight = null;
        while(cur != null) {
          if(cur.left != null) {
            if(prevRight != null)
              prevRight.next = cur.left;
            else
              nextLevel = cur.left;
            prevRight = cur.left;
          }
          if(cur.right != null) {
            if(prevRight != null)
              prevRight.next = cur.right;
            else
              nextLevel = cur.right;
            prevRight = cur.right;
          }
          cur = cur.next;
          if(nextLevel == null)
            nextLevel = prevRight;
        }
        level = nextLevel;
      }
    }
  }

  public static void main(String args[]) {
    TreeLinkNode input = createInput();
    new Solution2().connect(input);
    System.out.println(nodeToString(input));

    input = createInput();
    new Solution1().connect(input);
    System.out.println(nodeToString(input));
  }

  private static TreeLinkNode createInput() {
    TreeLinkNode root = new TreeLinkNode(-1);
    TreeLinkNode n2 = new TreeLinkNode(-7);
    TreeLinkNode n3 = new TreeLinkNode(9);
    root.left = n2;
    root.right = n3;
    TreeLinkNode n4 = new TreeLinkNode(-1);
    TreeLinkNode n5 = new TreeLinkNode(-7);
    n3.left = n4;
    n3.right = n5;
    TreeLinkNode n6 = new TreeLinkNode(8);
    TreeLinkNode n7 = new TreeLinkNode(-9);
    n4.right = n6;
    n5.left = n7;
    return root;
  }

  private static String nodeToString(TreeLinkNode root) {
    StringBuilder sb = new StringBuilder();
    TreeLinkNode level = root;
    while(level != null) {
      TreeLinkNode cur = level;
      TreeLinkNode nextLevel = level.left;
      while(cur != null) {
        if(nextLevel == null)
          nextLevel = cur.left;
        if(nextLevel == null)
          nextLevel = cur.right;
        sb.append(cur.val).append(" ");
        cur = cur.next;
      }
      sb.append("# ");
      level = nextLevel;
    }
    return sb.toString();
  }


}
