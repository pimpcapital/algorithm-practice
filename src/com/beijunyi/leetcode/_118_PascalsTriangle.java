package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 *
 * For example, given numRows = 5,
 * Return
 *   [
 *         [1],
 *        [1,1],
 *       [1,2,1],
 *      [1,3,3,1],
 *     [1,4,6,4,1]
 *   ]
 */
public class _118_PascalsTriangle implements Easy {

  public interface Solution {
    List<List<Integer>> generate(int numRows);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<List<Integer>> generate(int numRows) {
      List<List<Integer>> ret = new ArrayList<>();
      List<Integer> lastRow = null;
      for(int r = 1; r <= numRows; r++) {
        List<Integer> row = new ArrayList<>(r);
        row.add(1);
        for(int c = 1; c < r; c++)  {
          int value = lastRow.get(c - 1);
          if(c < r - 1) value += lastRow.get(c);
          row.add(value);
        }
        ret.add(row);
        lastRow = row;
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    int numRows;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      numRows = 5;
      result = s.generate(numRows);
      for(List<Integer> row : result) {
        System.out.println(row);
      }
    }
  }

}
