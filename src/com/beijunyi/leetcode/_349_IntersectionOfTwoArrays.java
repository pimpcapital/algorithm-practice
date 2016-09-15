package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example:
 *   Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 *
 * Note:
 *   Each element in the result must be unique.
 *   The result can be in any order.
 */
public class _349_IntersectionOfTwoArrays implements Easy {

  public interface Solution {
    int[] intersection(int[] nums1, int[] nums2);
  }

  public static class Solution1 implements Solution {
    @Override
    public int[] intersection(int[] nums1, int[] nums2) {
      Set<Integer> numSet1 = new HashSet<>();
      for(int num : nums1) numSet1.add(num);
      Set<Integer> numSet2 = new HashSet<>();
      for(int num : nums2) numSet2.add(num);
      numSet1.retainAll(numSet2);
      int[] ret = new int[numSet1.size()];
      int index = 0;
      for(int num : numSet1) ret[index++] = num;
      return ret;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public int[] intersection(int[] nums1, int[] nums2) {
      Set<Integer> set = new HashSet<>();
      Arrays.sort(nums1);
      Arrays.sort(nums2);
      int i = 0;
      int j = 0;
      while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
          i++;
        } else if (nums1[i] > nums2[j]) {
          j++;
        } else {
          set.add(nums1[i]);
          i++;
          j++;
        }
      }
      int[] result = new int[set.size()];
      int k = 0;
      for (Integer num : set) {
        result[k++] = num;
      }
      return result;
    }
  }

  public static void main(String args[]) {
    int[] nums1;
    int[] nums2;
    int[] result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums1 = new int[] {1, 2, 2, 1};
      nums2 = new int[] {2, 2};
      result = s.intersection(nums1, nums2);
      System.out.println(Arrays.toString(result));
    }
  }

}
