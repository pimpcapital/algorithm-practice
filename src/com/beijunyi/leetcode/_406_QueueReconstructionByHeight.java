package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.GreedyAlgorithm;
import com.beijunyi.leetcode.category.solution.WithBinarySearchTree;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 *   The number of people is less than 1,100.
 *
 * Example
 *   Input:
 *   [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 *   Output:
 *   [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class _406_QueueReconstructionByHeight implements Medium {

  public interface Solution {
    int[][] reconstructQueue(int[][] people);
  }

  public static class Solution1 implements Solution, GreedyAlgorithm {

    @Override
    public int[][] reconstructQueue(int[][] people) {
      Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
      LinkedList<int[]> ret = new LinkedList<>();
      for(int[] person : people) ret.add(person[1], person);
      return ret.toArray(people);
    }

  }

  /**
   * Uses a custom binary tree to keep track of people added to front
   */
  public static class Solution2 implements Solution, WithBinarySearchTree {

    @Override
    public int[][] reconstructQueue(int[][] people) {
      Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
      BinaryTree tree = new BinaryTree();
      for(int[] person : people) tree.add(person);
      LinkedList<int[]> ret = new LinkedList<>();
      tree.flattenTo(ret);
      return ret.toArray(people);
    }

    private static class BinaryTree {
      BinaryTreeNode root;

      void add(int[] person) {
        if(root == null) root = new BinaryTreeNode(person);
        else add(person, root, 0);
      }

      void add(int[] person, BinaryTreeNode current, int peopleInFront) {
        if(person[1] - peopleInFront <= current.peopleAddedToLeft) {
          if(current.left == null) current.left = new BinaryTreeNode(person);
          else add(person, current.left, peopleInFront);
          current.peopleAddedToLeft++;
        } else {
          if(current.right == null) current.right = new BinaryTreeNode(person);
          else add(person, current.right, peopleInFront + 1 + current.peopleAddedToLeft);
        }
      }

      void flattenTo(List<int[]> result) {
        if(root == null) return;
        root.flattenTo(result);
      }

    }

    private static class BinaryTreeNode {
      final int[] person;
      BinaryTreeNode left;
      BinaryTreeNode right;
      int peopleAddedToLeft = 0;

      public BinaryTreeNode(int[] person) {
        this.person = person;
      }

      void flattenTo(List<int[]> result) {
        if(left != null) left.flattenTo(result);
        result.add(person);
        if(right != null)  right.flattenTo(result);
      }

    }

  }

  public static void main(String args[]) {
    int[][] people;
    int[][] result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      people = new int[][]{
        {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
      };
      result = s.reconstructQueue(people);
      for(int[] person : result) {
        System.out.print(Arrays.toString(person));
      }
      System.out.println();
    }
  }

}
