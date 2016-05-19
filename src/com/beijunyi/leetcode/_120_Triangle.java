package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.InPlaceModification;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *   [
 *       [2],
 *      [3,4],
 *     [6,5,7],
 *    [4,1,8,3]
 *   ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class _120_Triangle implements Medium {

  public interface Solution {
    int minimumTotal(List<List<Integer>> triangle);
  }

  public static class Solution1 implements Solution, InPlaceModification, Recursive {

    @Override
    public int minimumTotal(List<List<Integer>> triangle) {
      return minimumTotal(triangle, triangle.size() - 2);
    }

    private static int minimumTotal(List<List<Integer>> triangle, int rowOffset) {
      if(rowOffset < 0) return triangle.get(0).get(0);
      List<Integer> currentRow = triangle.get(rowOffset);
      List<Integer> belowRow = triangle.get(rowOffset + 1);
      modifyCurrentRow(currentRow, belowRow);
      return minimumTotal(triangle, rowOffset - 1);
    }

    private static void modifyCurrentRow(List<Integer> currentRow, List<Integer> belowRow) {
      for(int i = 0; i < currentRow.size(); i++) {
        int value = currentRow.get(i);
        currentRow.set(i, value + Math.min(belowRow.get(i), belowRow.get(i + 1)));
      }
    }

  }

  public static class Solution2 implements Solution, Iterative {

    @Override
    public int minimumTotal(List<List<Integer>> triangle) {
      if(triangle.size() == 0) return 0;
      List<Integer> currentSums = new ArrayList<>(triangle.get(triangle.size() - 1));
      for(int i = triangle.size() - 2; i >= 0; i--) {
        List<Integer> row = triangle.get(i);
        List<Integer> nextSums = new ArrayList<>();
        for(int j = 0; j < row.size(); j++) {
          nextSums.add(row.get(j) + Math.min(currentSums.get(j), currentSums.get(j + 1)));
        }
        currentSums = nextSums;
      }
      return currentSums.get(0);
    }

  }

  public static void main(String args[]) {
    List<List<Integer>> triangle;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      triangle = Arrays.asList(
        Arrays.asList(2),
        Arrays.asList(3, 4),
        Arrays.asList(6, 5, 7),
        Arrays.asList(4, 1, 8, 3)
      );
      result = s.minimumTotal(triangle);
      System.out.println(result);
    }

  }

}
