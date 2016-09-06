package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class _108_ConvertSortedArrayToBinarySearchTree implements Medium {

  public interface Solution {
    TreeNode sortedArrayToBST(int[] nums);
  }

  public static class Solution1 implements Solution {
    @Override
    public TreeNode sortedArrayToBST(int[] nums) {
      return buildNode(nums, 0, nums.length);
    }

    private static TreeNode buildNode(int[] nums, int left, int right) {
      if(left == right) return null;
      int mid = left + (right - left) / 2;
      TreeNode ret = new TreeNode(nums[mid]);
      ret.left = buildNode(nums, left, mid);
      ret.right = buildNode(nums, mid + 1, right);
      return ret;
    }
  }

  public static void main(String args[]) {
    int[] nums;
    TreeNode result;
    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1, 2, 3, 4, 5, 6, 8, 12};
      result = s.sortedArrayToBST(nums);
      System.out.println(Arrays.toString(TreeNode.toArray(result)));
    }

  }

}
