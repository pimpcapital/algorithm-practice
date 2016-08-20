package com.beijunyi.leetcode;

import java.util.Arrays;

/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 *
 * You may assume each number in the sequence is unique.
 *
 * Follow up:
 *   Could you do it using only constant space complexity?
 */
public class _255_VerifyPreorderSequenceInBinarySearchTree {

  public interface Solution {
    boolean verifyPreorder(int[] preorder);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean verifyPreorder(int[] preorder) {
      if(preorder.length < 2) return true;
      int max = 0; // root is the threshold
      int min = -1; // no predefined minimum
      for(int i = 1; i < preorder.length; i++) {
        int val = preorder[i];
        if(min != -1 && val <= preorder[min]) return false;
        if(val > preorder[max]) { // greater than threshold
          min = max; // then set min to be the old threshold
          max = i; // set i to be the new threshold
        } else if(val > preorder[i - 1]) { // whenever number starts to increase
          if(min == -1) min = i - 1;
          while(min - 1 >= 0 && preorder[min - 1] < val) min--; // find the number that is just smaller than current
        }
      }
      return true;
    }

  }

  public static void main(String[] args) {
    int[] preorder;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      preorder = new int[] {4, 2, 3, 1};
      result = s.verifyPreorder(preorder);
      System.out.println(result);

      preorder = new int[] {4, 1, 3, 2};
      result = s.verifyPreorder(preorder);
      System.out.println(result);

      preorder = new int[] {1, 3, 4, 2};
      result = s.verifyPreorder(preorder);
      System.out.println(result);

      preorder = new int[] {10, 7, 4, 8, 6, 40, 23};
      result = s.verifyPreorder(preorder);
      System.out.println(result);

      preorder = new int[] {6, 4, 1, 2, 5, 3};
      result = s.verifyPreorder(preorder);
      System.out.println(result);
    }
  }

}
