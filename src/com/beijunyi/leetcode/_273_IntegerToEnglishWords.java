package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 *
 * For example,
 *   123 -> "One Hundred Twenty Three"
 *   12345 -> "Twelve Thousand Three Hundred Forty Five"
 *   1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Hint:
 *   1) Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 *   2) Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and
 *      convert just that chunk to words.
 *   3) There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010?
 *      (middle chunk is zero and should not be printed out)
 */
public class _273_IntegerToEnglishWords implements Hard {

  public interface Solution {
    String numberToWords(int num);
  }

  public static class Solution1 implements Solution {

    private static final String[] WORDS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEN_ABOVE = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] LEVELS = {"", "Thousand", "Million", "Billion", "Trillion", "Quadrillion", "Quintillion", "Sextillion", "Septillion", "Octillion", "Nonillion"};

    @Override
    public String numberToWords(int num) {
      List<String> parts = new ArrayList<>();
      while(num != 0) {
        parts.add(process(num % 1000));
        num /= 1000;
      }
      return buildWords(parts);
    }

    private static String process(int num) {
      StringBuilder ret = new StringBuilder();
      if(num >= 100) {
        ret.append(WORDS[num / 100]).append(" Hundred");
        num %= 100;
      }
      if(num == 0) return ret.toString();
      if(ret.length() != 0) ret.append(" ");

      if(num < 20 && num >= 10) {
        ret.append(TEN_ABOVE[num - 10]);
        return ret.toString();
      } else if(num >= 20) {
        ret.append(TENS[(num - 20) / 10]);
        num %= 10;
      }

      if(num == 0) return ret.toString();
      if(ret.length() != 0 && ret.charAt(ret.length() - 1) != ' ') ret.append(" ");

      ret.append(WORDS[num]);
      return ret.toString();
    }

    private static String buildWords(List<String> parts) {
      StringBuilder sb = new StringBuilder();
      for(int i = parts.size() - 1; i >= 0; i--) {
        if(parts.get(i).isEmpty()) continue;
        if(sb.length() != 0) sb.append(" ");
        sb.append(parts.get(i));
        if(i != 0) sb.append(" ").append(LEVELS[i]);
      }
      return sb.length() == 0 ? "Zero" : sb.toString();
    }

  }

  public static class Solution2 implements Solution {
    private static final String[] WORDS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEN_ABOVE = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] LEVELS = {"", "Thousand", "Million", "Billion", "Trillion", "Quadrillion", "Quintillion", "Sextillion", "Septillion", "Octillion", "Nonillion"};

    @Override
    public String numberToWords(int num) {
      List<List<String>> parts = new ArrayList<>();
      while(num != 0) {
        parts.add(process(num % 1000));
        num /= 1000;
      }
      return buildWords(parts);
    }

    private static List<String> process(int num) {
      List<String> ret = new ArrayList<>();
      if(num >= 100) {
        ret.add(WORDS[num / 100]);
        ret.add("Hundred");
        num %= 100;
      }
      if(num == 0) return ret;
      if(num < 20 && num >= 10) {
        ret.add(TEN_ABOVE[num - 10]);
        return ret;
      } else if(num >= 20) {
        ret.add(TENS[(num - 20) / 10]);
        num %= 10;
      }
      if(num == 0) return ret;
      ret.add(WORDS[num]);
      return ret;
    }

    private static String buildWords(List<List<String>> parts) {
      List<String> levels = new ArrayList<>();
      for(int i = parts.size() - 1; i >= 0; i--) {
        List<String> current = parts.get(i);
        if(current.isEmpty()) continue;
        if(i != 0) current.add(LEVELS[i]);
        levels.add(join(current));
      }
      return levels.size() == 0 ? "Zero" : join(levels);
    }

    private static String join(List<String> words) {
      StringBuilder sb = new StringBuilder();
      for(String word : words) {
        if(sb.length() != 0) sb.append(" ");
        sb.append(word);
      }
      return sb.toString();
    }

  }

  public static void main(String args[]) {
    int num;
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = 0;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 12;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 19;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 101;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 123;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 12345;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 1000000;
      result = s.numberToWords(num);
      System.out.println(result);

      num = 1234567;
      result = s.numberToWords(num);
      System.out.println(result);

      System.out.println();
    }
  }

}
