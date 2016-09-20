package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is
 * the smallest possible.
 *
 * Note:
 *   The length of num is less than 10002 and will be ≥ k.
 *   The given num does not contain any leading zero.
 *
 * Example 1:
 *   Input: num = "1432219", k = 3
 *   Output: "1219"
 *   Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 *   Input: num = "10200", k = 1
 *   Output: "200"
 *   Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 *
 * Example 3:
 *   Input: num = "10", k = 2
 *   Output: "0"
 *   Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class _402_RemoveKDigits implements Medium {

  public interface Solution {
    String removeKdigits(String num, int k);
  }

  public static class Solution1 implements Solution {
    @Override
    public String removeKdigits(String num, int k) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < num.length();) {
        char c = num.charAt(i);
        if(k > 0 && sb.length() > 0 && c < sb.charAt(sb.length() - 1)) {
          sb.setLength(sb.length() - 1);
          k--;
        } else {
          if(sb.length() != 0 || c != '0') sb.append(c);
          i++;
        }
      }
      sb.setLength(Math.max(0, sb.length() - k));
      return sb.length() == 0 ? "0" : sb.toString();
    }
  }

  /**
   * Similar idea but with 2 loops instead of suppressing the increment of "i"
   */
  public static class Solution2 implements Solution {
    @Override
    public String removeKdigits(String num, int k) {
      StringBuilder sb = new StringBuilder();
      for(char c : num.toCharArray()) {
        while(k > 0 && sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
          sb.setLength(sb.length() - 1); // remove last digit
          k--;
        }
        if(sb.length() != 0 || c != '0') sb.append(c);  // Only append when it is not leading zero
      }
      if(k <= sb.length()) sb.setLength(sb.length() - k);  // use all remaining k
      return sb.length() == 0 ? "0" : sb.toString();
    }
  }

  public static void main(String args[]) {
    String num;
    int k;
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = "1234567890";
      k = 9;
      result = s.removeKdigits(num, k);
      System.out.println(result);

      num = "1432219";
      k = 3;
      result = s.removeKdigits(num, k);
      System.out.println(result);

      num = "11432219";
      k = 3;
      result = s.removeKdigits(num, k);
      System.out.println(result);

      num = "10200";
      k = 1;
      result = s.removeKdigits(num, k);
      System.out.println(result);

      num = "10";
      k = 2;
      result = s.removeKdigits(num, k);
      System.out.println(result);
    }
  }

}
