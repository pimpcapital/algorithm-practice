package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.difficulty.Important;
import com.beijunyi.leetcode.category.solution.BinaryIndexedTree;
import com.beijunyi.leetcode.category.solution.DivideAndConquer;
import com.beijunyi.leetcode.category.solution.WithBinarySearchTree;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 */
public class _315_CountOfSmallerNumbersAfterSelf implements Hard, Important {

  public interface Solution {
    List<Integer> countSmaller(int[] nums);
  }

  /**
   * Starting from back, uses Fenwick Tree to keep the counts of numbers that are smaller than X
   */
  public static class Solution1 implements Solution, BinaryIndexedTree {

    @Override
    public List<Integer> countSmaller(int[] nums) {
      LinkedList<Integer> ret = new LinkedList<>();
      if(nums.length == 0) return ret;

      normalize(nums, -min(nums));
      int max = max(nums);
      int[] tree = new int[max + 1];

      for(int i = nums.length - 1; i >= 0; i--) {
        int num = nums[i];
        ret.addFirst(get(num - 1, tree));
        increment(nums[i], tree);
      }

      return ret;
    }

    private static int min(int[] nums) {
      int min = Integer.MAX_VALUE;
      for(int num : nums) min = Math.min(min, num);
      return min;
    }

    private static void normalize(int[] nums, int delta) {
      for(int i = 0; i < nums.length; i++) nums[i] += delta;
    }

    private static int max(int[] nums) {
      int max = Integer.MIN_VALUE;
      for(int num : nums) max = Math.max(max, num);
      return max;
    }

    private static void increment(int value, int[] tree) {
      for(int i = value + 1; i <= tree.length; i += (i&-i)) tree[i - 1]++;
    }

    // return count of values that are smaller than or equal to the given value
    private static int get(int value, int[] tree) {
      int count = 0;
      for(int i = value + 1; i > 0; i -= (i&-i)) count += tree[i - 1];
      return count;
    }

  }

  /**
   * Add values from back to front into a special binary tree.
   * Every binary tree node has 2 extra values:
   *   1) count of current value
   *   2) count of values smaller than the current value
   */
  public static class Solution2 implements Solution, WithBinarySearchTree {

    @Override
    public List<Integer> countSmaller(int[] nums) {
      LinkedList<Integer> ret = new LinkedList<>();
      BinaryTree tree = new BinaryTree();
      for(int i = nums.length - 1; i >= 0; i--) ret.addFirst(tree.addValue(nums[i]));
      return ret;
    }

    private static class BinaryTreeNode {
      private final int value;
      private BinaryTreeNode left;
      private BinaryTreeNode right;
      private int leftChildren = 0; // count of all nodes on the left side
      private int count = 0;

      public BinaryTreeNode(int value) {
        this.value = value;
      }
    }

    private static class BinaryTree {
      private BinaryTreeNode root;

      // add a value to the tree and return the count of existing values that are smaller than the given value
      private int addValue(int value) {
        if(root == null) root = new BinaryTreeNode(value);
        return addValue(value, root, 0);
      }

      private int addValue(int value, BinaryTreeNode current, int leftChildrenTotal) {
        if(current.value < value) {
          if(current.right == null) current.right = new BinaryTreeNode(value);
          return addValue(value, current.right, leftChildrenTotal + current.leftChildren + current.count);
        } else if(current.value > value) {
          current.leftChildren++;
          if(current.left == null) current.left = new BinaryTreeNode(value);
          return addValue(value, current.left, leftChildrenTotal);
        } else {
          current.count++;
          return leftChildrenTotal + current.leftChildren;
        }
      }
    }

  }

  /**
   * The basic idea is to do merge sort to nums[]. To record the result, we need to keep the index of each number in the
   * original array. So instead of sort the number in nums, we sort the indexes of each number.
   * Example: nums = [5,2,6,1], indexes = [0,1,2,3]
   * After sort: indexes = [3,1,0,2]
   *
   * While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.
   *
   * We keep a rightcount to record how many numbers from right[] we have added and keep an array count[] to record the
   * result.
   *
   * When we move a number from right[] into the new sorted array, we increase rightcount by 1.
   *
   * When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by
   * rightcount.
   */
  public static class Solution3 implements Solution, DivideAndConquer {
    private int[] count;

    @Override
    public List<Integer> countSmaller(int[] nums) {
      List<Integer> res = new ArrayList<>();

      count = new int[nums.length];
      int[] indexes = new int[nums.length];
      for(int i = 0; i < nums.length; i++){
        indexes[i] = i;
      }
      mergesort(nums, indexes, 0, nums.length - 1);
      for(int i = 0; i < count.length; i++){
        res.add(count[i]);
      }
      return res;
    }

    private void mergesort(int[] nums, int[] indexes, int start, int end){
      if(end <= start){
        return;
      }
      int mid = (start + end) / 2;
      mergesort(nums, indexes, start, mid);
      mergesort(nums, indexes, mid + 1, end);

      merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
      int mid = (start + end) / 2;
      int left_index = start;
      int right_index = mid+1;
      int rightcount = 0;
      int[] new_indexes = new int[end - start + 1];

      int sort_index = 0;
      while(left_index <= mid && right_index <= end){
        if(nums[indexes[right_index]] < nums[indexes[left_index]]){
          new_indexes[sort_index] = indexes[right_index];
          rightcount++;
          right_index++;
        }else{
          new_indexes[sort_index] = indexes[left_index];
          count[indexes[left_index]] += rightcount;
          left_index++;
        }
        sort_index++;
      }
      while(left_index <= mid){
        new_indexes[sort_index] = indexes[left_index];
        count[indexes[left_index]] += rightcount;
        left_index++;
        sort_index++;
      }
      while(right_index <= end){
        new_indexes[sort_index++] = indexes[right_index++];
      }
      for(int i = start; i <= end; i++){
        indexes[i] = new_indexes[i - start];
      }
    }
  }

  public static void main(String args[]) {
    int[] nums;
    List<Integer> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[]{5, 2, 6, 1};
      result = s.countSmaller(nums);
      System.out.println(result);
    }
  }

}
