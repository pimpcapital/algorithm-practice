package com.beijunyi.leetcode;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 *
 * What is the minimum candies you must give?
 */
public class Candy implements Hard {

  public static class Solution {
    public int candy(int[] ratings) {
      int size = ratings.length;
      if(size <= 1)
        return size;

      int[] num = new int[size];
      num[0] = 1;

      for(int i = 1; i < size; i++) {
        if(ratings[i] > ratings[i - 1])
          num[i] = num[i - 1] + 1;
        else
          num[i] = 1;
      }

      for(int i = size - 1; i > 0; i--) {
        if(ratings[i - 1] > ratings[i])
          num[i - 1] = Math.max(num[i] + 1, num[i - 1]);
      }

      int result = 0;
      for(int i = 0; i < size; i++) {
        result += num[i];
      }
      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().candy(new int[]{1, 1, 3, 4, 1, 2, 4, 6}));
  }

}
