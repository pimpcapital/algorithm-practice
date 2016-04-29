package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BinaryIndexedTree;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 *  Given nums = [1, 3, 5]
 *
 *  sumRange(0, 2) -> 9
 *  update(1, 2)
 *  sumRange(0, 2) -> 8
 * Note:
 *  The array is only modifiable by the update function.
 *  You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class _307_RangeSumQueryMutable implements Medium {

  public interface Solution {
    void setup(int[] nums);
    void update(int i, int val);
    int sumRange(int i, int j);
  }

  public static class Solution1 implements Solution, BinaryIndexedTree {

    private SegmentTreeNode root;

    @Override
    public void setup(int[] nums) {
      root = buildTree(nums, 0, nums.length);
    }

    private static SegmentTreeNode buildTree(int[] nums, int start, int end) {
      if(start == end - 1) return new SegmentTreeNode(start, nums[start]);
      int mid = (start + end) / 2;
      SegmentTreeNode left = buildTree(nums, start, mid);
      SegmentTreeNode right = buildTree(nums, mid, end);
      return new SegmentTreeNode(left, right);
    }

    @Override
    public void update(int i, int val) {
      assert root != null;
      root.update(i, val);
    }

    @Override
    public int sumRange(int i, int j) {
      assert root != null;
      return root.sumRange(i, j);
    }

    private static class SegmentTreeNode {
      public int value;
      public final int start;
      public final int end;
      public final SegmentTreeNode left;
      public final SegmentTreeNode right;

      public SegmentTreeNode(SegmentTreeNode left, SegmentTreeNode right) {
        assert left.end == right.start;
        this.start = left.start;
        this.end = right.end;
        this.value = left.value + right.value;
        this.left = left;
        this.right = right;
      }

      public SegmentTreeNode(int index, int value) {
        this.start = index;
        this.end = index + 1;
        this.value = value;
        this.left = null;
        this.right = null;
      }

      public void update(int i, int val) {
        assert i >= start && i < end;

        // propagate if possible
        if(left != null && i >= left.start && i < left.end) left.update(i, val); // left child has the target
        else if(right != null && i >= right.start && i < right.end) right.update(i, val); // right child has the target

        // update value
        if(left == null && right == null) value = val; // if leaf
        else value = (left != null ? left.value : 0) + (right != null ? right.value : 0); // sum the two children
      }

      public int sumRange(int i, int j) {
        assert i >= start && j < end;

        if(left == null && right == null) return value;
        if(left != null && j < left.end) return left.sumRange(i, j); // if both start and end fall into the left
        if(right != null && i >= right.start) return right.sumRange(i, j); // if both fall into the right
        return (left != null ? left.sumRange(i, left.end) : 0) // right part from the left + left part from the right
                 + (right != null ? right.sumRange(right.start, j) : 0);
      }
    }
  }

  /**
   * Binary Indexed Trees (BIT or Fenwick tree):
   * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
   *
   * Example: given an array a[0]...a[7], we use a array BIT[9] to represent a tree, where index [2] is the parent of
   * [1] and [3], [6] is the parent of [5] and [7], [4] is the parent of [2] and [6], and [8] is the parent of [4].
   *  I.e.,
   *
   * BIT[] as a binary tree:
   *            ______________*
   *            ______*
   *            __*     __*
   *            *   *   *   *
   * indices: 0 1 2 3 4 5 6 7 8
   *
   * BIT[i] = ([i] is a left child) ? the partial sum from its left most descendant to itself : the partial sum from
   * its parent (exclusive) to itself. (check the range of "__").
   *
   * Eg. BIT[1]=a[0], BIT[2]=a[1]+BIT[1]=a[1]+a[0], BIT[3]=a[2],
   * BIT[4]=a[3]+BIT[3]+BIT[2]=a[3]+a[2]+a[1]+a[0],
   * BIT[6]=a[5]+BIT[5]=a[5]+a[4],
   * BIT[8]=a[7]+BIT[7]+BIT[6]+BIT[4]=a[7]+a[6]+...+a[0], ...
   *
   * Thus, to update a[1]=BIT[2], we shall update BIT[2], BIT[4], BIT[8], i.e., for current [i], the next update [j]
   * is j=i+(i&-i) //double the last 1-bit from [i].
   *
   * Similarly, to get the partial sum up to a[6]=BIT[7], we shall get the sum of BIT[7], BIT[6], BIT[4], i.e., for
   * current [i], the next summand [j] is j=i-(i&-i) // delete the last 1-bit from [i].
   *
   * To obtain the original value of a[7] (corresponding to index [8] of BIT), we have to subtract BIT[7], BIT[6],
   * BIT[4] from BIT[8], i.e., starting from [idx-1], for current [i], the next subtrahend [j] is j=i-(i&-i), up to
   * j==idx-(idx&-idx) exclusive. (However, a quicker way but using extra space is to store the original array.)
   */
  public static class Solution2 implements Solution, BinaryIndexedTree {

    int[] nums;
    int[] BIT;
    int n;

    public void setup(int[] nums) {
      this.nums = nums;

      n = nums.length;
      BIT = new int[n + 1];
      for (int i = 0; i < n; i++)
        init(i, nums[i]);
    }

    public void init(int i, int val) {
      i++;
      while (i <= n) {
        BIT[i] += val;
        i += (i & -i);
      }
    }

    public void update(int i, int val) {
      int diff = val - nums[i];
      nums[i] = val;
      init(i, diff);
    }

    public int getSum(int i) {
      int sum = 0;
      i++;
      while (i > 0) {
        sum += BIT[i];
        i -= (i & -i);
      }
      return sum;
    }

    public int sumRange(int i, int j) {
      return getSum(j) - getSum(i - 1);
    }
  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      s.setup(new int[] {1, 3, 5});
      System.out.println(s.sumRange(0, 2));
      System.out.println(s.sumRange(1, 2));
      System.out.println(s.sumRange(0, 2));

      System.out.println(s.sumRange(0, 1));
      s.update(1, 10);
      System.out.println(s.sumRange(1, 2));
    }

  }

}
