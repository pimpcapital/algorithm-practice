package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 */
public class PermutationsTwo implements Hard {

  public static class Solution2 {

    public List<List<Integer>> permuteUnique(int[] num) {
      List<List<Integer>> opt = new ArrayList<>();

      // edge case
      if(num.length == 0)
        return opt;

      // base case
      List<Integer> firstLst = new ArrayList<>();
      firstLst.add(num[0]);
      opt.add(firstLst);

      // iteration
      for(int i = 1; i < num.length; i++) {
        HashSet<List<Integer>> visited = new HashSet<>();
        int numOfLastOpt = opt.size();
        for(int j = 0; j < numOfLastOpt; j++) {
          // insert new int into each valid gap
          int positions = opt.get(j).size();
          for(int u = 0; u <= positions; u++) {
            if(u != 0) {
              if(opt.get(j).get(u - 1) != num[i]) {
                List<Integer> newLst = new ArrayList<>(opt.get(j));
                newLst.add(u, num[i]);
                if(!visited.contains(newLst)) {
                  opt.add(newLst);
                  visited.add(newLst);
                }
              }
            } else {
              List<Integer> newLst = new ArrayList<>(opt.get(j));
              newLst.add(u, num[i]);
              if(!visited.contains(newLst)) {
                opt.add(newLst);
                visited.add(newLst);
              }
            }
          }
        }
        // delete previous opt
        for(int j = 0; j < numOfLastOpt; j++)
          opt.remove(0);
      }

      return opt;
    }
  }

  /**
   * Basically, assume we have "1234", the idea is to increase the number in ascending order, so next is "1243", next is "1324", and so on.
   */
  public static class Solution1 {
    List<List<Integer>> permuteUnique(int[] num) {
      Arrays.sort(num);
      List<List<Integer>> result = new ArrayList<>();
      List<Integer> permutation = new ArrayList<>();
      for(int n : num)
        permutation.add(n);
      result.add(permutation);

      int j;
      int i;
      while(true) {
        for(i = num.length - 1; i > 0; i--)
          if(num[i - 1] < num[i])
            break;

        if(i == 0)
          break;

        for(j = num.length - 1; j > i - 1; j--)
          if(num[j] > num[i - 1])
            break;

        int temp = num[i - 1];
        num[i - 1] = num[j];
        num[j] = temp;
        reverse(num, i, num.length - 1);
        permutation = new ArrayList<>();
        for(int n : num)
          permutation.add(n);
        result.add(permutation);
      }
      return result;
    }

    void reverse(int[] num, int s, int e) {
      while(s < e) {
        int temp = num[s];
        num[s++] = num[e];
        num[e--] = temp;
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution2().permuteUnique(new int[]{2, 2, 1, 1}));
    System.out.println(new Solution1().permuteUnique(new int[]{2, 2, 1, 1}));
  }
}
