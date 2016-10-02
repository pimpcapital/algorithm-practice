package com.beijunyi.leetcode;

import java.util.*;
import java.util.stream.Collectors;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 */
public class _128_LongestConsecutiveSequence implements Hard {

  public interface Solution {
    int longestConsecutive(int[] num);
  }


  public static class Solution1 implements Solution {
    public int longestConsecutive(int[] num) {
      Set<Integer> set = new HashSet<>(num.length);
      for(int n : num) {
        set.add(n);
      }

      int maxLength = 0;
      for(int n : num) {
        if(set.contains(n)) {
          int length = 1;
          int next = n - 1;
          while(set.contains(next)) {
            length++;
            set.remove(next--);
          }
          next = n + 1;
          while(set.contains(next)) {
            length++;
            set.remove(next++);
          }

          if(length > maxLength)
            maxLength = length;
        }
      }

      return maxLength;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public int longestConsecutive(int[] num) {
      Set<Integer> nums = Arrays.stream(num).boxed().collect(Collectors.toSet());
      int max = 0;
      for(int n : num) {
        if(!nums.contains(n - 1)) {
          int count = 1;
          while(nums.contains(++n)) count++;
          max = Math.max(count, max);
        }
      }
      return max;
    }
  }

  public static void main(String args[]) {
    int[] num;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = new int[]{100, 4, 200, 1, 3, 2};
      result = s.longestConsecutive(num);
      System.out.println(result);
    }

  }

}
