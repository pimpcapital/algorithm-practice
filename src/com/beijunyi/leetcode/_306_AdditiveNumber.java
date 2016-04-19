package com.beijunyi.leetcode;

import java.math.BigInteger;
import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;

public class _306_AdditiveNumber implements Medium {

  public interface Solution {
    boolean isAdditiveNumber(String num);
  }

  public static class Solution1 implements Solution, Recursive {

    @Override
    public boolean isAdditiveNumber(String num) {
      BigInteger a = BigInteger.ZERO;
      for(int i = 1; i <= num.length() / 2; i++) {
        a = a.multiply(BigInteger.TEN).add(getDigit(num, i - 1));
        BigInteger b = BigInteger.ZERO;
        for(int j = i + 1; j < num.length(); j++) {
          b = b.multiply(BigInteger.TEN).add(getDigit(num, j - 1));
          if(Math.max(i, j - i) > num.length() - j) break;
          if(isAdditiveNumber(num, j, a, b)) return true;
          if(b.equals(BigInteger.ZERO)) break;
        }
        if(a.equals(BigInteger.ZERO)) break;
      }
      return false;
    }

    private static BigInteger getDigit(String num, int index) {
      return BigInteger.valueOf(num.charAt(index) - '0');
    }

    private static boolean isAdditiveNumber(String num, int offset, BigInteger a, BigInteger b) {
      BigInteger sum = a.add(b);
      String sumStr = sum.toString();
      int nextOffset = offset + sumStr.length();
      return num.startsWith(sumStr, offset)
               && (nextOffset == num.length() || isAdditiveNumber(num, nextOffset, b, sum));
    }
  }

  public static class Solution2 implements Solution, Iterative {

    @Override
    public boolean isAdditiveNumber(String num) {
      int n = num.length();
      for(int i = 1; i <= n / 2; i++)
        for(int j = 1; Math.max(j, i) <= n - i - j; j++)
          if(isValid(i, j, num)) return true;
      return false;
    }
    private boolean isValid(int i, int j, String num) {
      if (num.charAt(0) == '0' && i > 1) return false;
      if (num.charAt(i) == '0' && j > 1) return false;
      String sum;
      BigInteger x1 = new BigInteger(num.substring(0, i));
      BigInteger x2 = new BigInteger(num.substring(i, i + j));
      for (int start = i + j; start != num.length(); start += sum.length()) {
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        sum = x2.toString();
        if (!num.startsWith(sum, start)) return false;
      }
      return true;
    }
  }

  public static void main(String args[]) {
    String num;
    boolean r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = "112358";
      r = s.isAdditiveNumber(num);
      System.out.println(r);

      num = "199100199";
      r = s.isAdditiveNumber(num);
      System.out.println(r);

      num = "0235813";
      r = s.isAdditiveNumber(num);
      System.out.println(r);
    }
  }


}
