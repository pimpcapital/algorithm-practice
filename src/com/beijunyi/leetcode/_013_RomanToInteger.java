package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a roman numeral, convert it to an integer.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class _013_RomanToInteger implements Easy {

  public interface Solution {
    int romanToInt(String s);
  }

  public static class Solution1 implements Solution {

    private static final int[] VALUES = makeValueTable();

    @Override
    public int romanToInt(String s) {
      int count = 0;
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        int value = VALUES[c];
        if(i + 1 < s.length()) {
          char nextC = s.charAt(i + 1);
          int nextValue = VALUES[nextC];
          if(nextValue > value) {
            value = nextValue - value;
            i++;
          }
        }
        count += value;
      }
      return count;
    }

    private static int[] makeValueTable() {
      int[] ret = new int[128];
      ret['I'] = 1;
      ret['V'] = 5;
      ret['X'] = 10;
      ret['L'] = 50;
      ret['C'] = 100;
      ret['D'] = 500;
      ret['M'] = 1000;
      return ret;
    }
  }

  public static class Solution2 implements Solution {

    private static final int[] VALUES = makeValueTable();

    @Override
    public int romanToInt(String s) {
      int count = 0;
      int prevValue = 0;
      for(int i = 0; i < s.length(); i++) {
        int value = VALUES[s.charAt(i)];
        if(value > prevValue) {
          count -= (prevValue + prevValue);
        }
        count += value;
        prevValue = value;
      }
      return count;
    }

    private static int[] makeValueTable() {
      int[] ret = new int[128];
      ret['I'] = 1;
      ret['V'] = 5;
      ret['X'] = 10;
      ret['L'] = 50;
      ret['C'] = 100;
      ret['D'] = 500;
      ret['M'] = 1000;
      return ret;
    }
  }

  public static class Solution3 implements Solution {

    @Override
    public int romanToInt(String s) {
      int res = 0;
      for (int i = s.length() - 1; i >= 0; i--) {
        char c = s.charAt(i);
        switch (c) {
          case 'I':
            res += (res >= 5 ? -1 : 1);
            break;
          case 'V':
            res += 5;
            break;
          case 'X':
            res += 10 * (res >= 50 ? -1 : 1);
            break;
          case 'L':
            res += 50;
            break;
          case 'C':
            res += 100 * (res >= 500 ? -1 : 1);
            break;
          case 'D':
            res += 500;
            break;
          case 'M':
            res += 1000;
            break;
        }
      }
      return res;
    }
  }

  public static void main(String args[]) {
    String str;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      str = "MCM";
      result = s.romanToInt(str);
      System.out.println(result);

      str = "LXXX";
      result = s.romanToInt(str);
      System.out.println(result);

      System.out.println();
    }
  }

}
