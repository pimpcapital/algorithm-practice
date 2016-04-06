package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 *
 * For example, given k = 3,
 * Return [1,3,3,1].
 *
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class _119_PascalsTriangleTwo implements Easy {

  public static class Solution2 {
    public List<Integer> getRow(int rowIndex) {
      Integer[] result =  new Integer[rowIndex + 1];
      Arrays.fill(result, 0);
      result[0] = 1;
      for(int i = 1; i < rowIndex + 1; i++)
        for(int j = i; j >= 1; j--)
          result[j] += result[j - 1];
      return Arrays.asList(result);
    }
  }

  /**
   * the mth element of the nth row of the Pascal's triangle is C(n, m) = n!/(m! * (n-m)!)
   *
   * C(n, m-1) = n!/((m-1)! * (n-m+1)!)
   * so C(n, m) = C(n, m-1) * (n-m+1) / m
   * In additional, C(n, m) == C(n, n-m)
   */
  public static class Solution1 {
    public List<Integer> getRow(int rowIndex) {
      List<Integer> resultList = new ArrayList<>();
      if(rowIndex == 0) {
        resultList.add(1);
        return resultList;
      }

      int num = rowIndex / 2;
      long temp = 1; // Some test cases have numbers larger than Integer.MAX_VALUE
      resultList.add((int)temp);

      // Compute first half using C(n, m) = C(n, m-1) * (n-m+1) / m;
      for(int i = 1; i <= num; i++) {
        temp *= rowIndex - i + 1;
        temp /= i;
        resultList.add((int)temp);
      }

      // Mirror the second half
      for(int i = (rowIndex - 1) / 2; i >= 0; i--)
        resultList.add(resultList.get(i));

      return resultList;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().getRow(5));
    System.out.println(new Solution2().getRow(5));
  }
}
