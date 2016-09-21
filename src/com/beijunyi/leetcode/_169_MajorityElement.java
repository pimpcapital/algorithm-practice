package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.BoyerMooreVoting;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than
 * ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 */
public class _169_MajorityElement implements Easy {

  /**
   * This can be solved by Moore's voting algorithm.
   * Basic idea of the algorithm is if we cancel out each occurrence of an element e with all the other elements that
   * are different from e then e will exist till end if it is a majority element.
   *
   * Below code loops through each element and maintains a count of the element that has the potential of being the
   * majority element.vIf next element is same then increments the count, otherwise decrements the count. If the count
   * reaches 0 then update the potential index to the current element and sets count to 1.
   */
  public static class Solution implements BoyerMooreVoting {
    public int majorityElement(int[] num) {
      int majorityIndex = 0;
      for(int count = 1, i = 1; i < num.length; i++) {
        if(num[majorityIndex] == num[i])
          count++;
        else
          count--;
        if (count == 0) {
          majorityIndex = i;
          count = 1;
        }
      }
      return num[majorityIndex];
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().majorityElement(new int[] {1, 2, 3, 2, 2}));
  }

}
