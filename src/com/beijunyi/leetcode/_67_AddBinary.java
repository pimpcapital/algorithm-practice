package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * For example,
 *   a = "11"
 *   b = "1"
 *   Return "100".
 */
public class _67_AddBinary implements Easy {

  public interface Solution {
    String addBinary(String a, String b);
  }

  public static class Solution1 implements Solution {

    @Override
    public String addBinary(String a, String b) {
      int digits = Math.max(a.length(), b.length()) + 1;
      char[] aBits = initBitsArray(a, digits);
      char[] bBits = initBitsArray(b, digits);
      char[] ret = new char[digits];

      int carry = 0;
      for(int i = digits - 1; i >= 0; i--) {
        int count = carry;
        if(aBits[i] == '1') count++;
        if(bBits[i] == '1') count++;
        ret[i] = count % 2 == 1 ? '1' : '0';
        carry = count > 1 ? 1 : 0;
      }

      int offset = countLeadingZeroes(ret);
      if(offset == ret.length) offset--;
      int length = ret.length - offset;
      return new String(ret, offset, length);
    }

    private char[] initBitsArray(String str, int digits) {
      char[] ret = new char[digits];
      int offset = digits - str.length();
      for(int i = 0; i < offset; i++)
        ret[i] = '0';
      for(int i = 0; i < str.length(); i++)
        ret[offset + i] = str.charAt(i);
      return ret;
    }

    private int countLeadingZeroes(char[] bits) {
      for(int i = 0; i < bits.length; i++) {
        if(bits[i] != '0') return i;
      }
      return bits.length;
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public String addBinary(String a, String b) {
      if(a == null || a.isEmpty())
        return b;
      if(b == null || b.isEmpty())
        return a;


      StringBuilder stb = new StringBuilder();
      int i = a.length() - 1;
      int j = b.length() - 1;
      int aBit;
      int bBit;
      int carry = 0;
      int result;

      while(i >= 0 || j >= 0 || carry == 1) {
        aBit = (i >= 0) ? Character.getNumericValue(a.charAt(i--)) : 0;
        bBit = (j >= 0) ? Character.getNumericValue(b.charAt(j--)) : 0;
        result = aBit ^ bBit ^ carry;
        carry = ((aBit + bBit + carry) >= 2) ? 1 : 0;
        stb.append(result);
      }
      return stb.reverse().toString();
    }
  }

  public static void main(String args[]) {

    String a;
    String b;
    String r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      a = "11";
      b = "1";
      r = s.addBinary(a, b);
      System.out.println(r);

      a = "0";
      b = "0";
      r = s.addBinary(a, b);
      System.out.println(r);
    }

  }

}
