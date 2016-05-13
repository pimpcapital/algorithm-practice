package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
 * display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class _006_ZigZagConversion implements Easy {

  public interface Solution {
    String convert(String s, int numRows);
  }

  public static class Solution1 implements Solution {

    @Override
    public String convert(String s, int nRows) {
      char[] c = s.toCharArray();

      StringBuffer[] sb = new StringBuffer[nRows]; // for every row, make a str builder
      for (int i = 0; i < sb.length; i++)
        sb[i] = new StringBuffer();

      int i = 0;
      while (i < c.length) {
        for (int idx = 0; idx < nRows && i < c.length; idx++) // vertically down
          sb[idx].append(c[i++]);
        for (int idx = nRows-2; idx >= 1 && i < c.length; idx--) // obliquely up
          sb[idx].append(c[i++]);
      }
      for (int idx = 1; idx < sb.length; idx++)
        sb[0].append(sb[idx]);
      return sb[0].toString();
    }

  }

  public static void main(String args[]) {
    String str;
    int numRows;
    String result;

    for(Solution s : Arrays.asList(new Solution1())) {
      str = "PAYPALISHIRING";
      numRows = 3;
      result = s.convert(str, numRows);
      System.out.println(result);

      str = "ABC";
      numRows = 2;
      result = s.convert(str, numRows);
      System.out.println(result);
    }


  }

}
