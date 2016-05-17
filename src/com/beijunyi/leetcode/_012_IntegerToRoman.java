package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class _012_IntegerToRoman implements Medium {

  public interface Solution {
    String intToRoman(int num);
  }

  public static class Solution1 implements Solution {

    @Override
    public String intToRoman(int num) {
      String M[] = {"", "M", "MM", "MMM"};
      String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
      String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
      String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
      return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public String intToRoman(int num) {
      int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
      String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < values.length; i++) {
        while(num >= values[i]) {
          num -= values[i];
          sb.append(strs[i]);
        }
      }
      return sb.toString();
    }
  }

  public static class Solution3 implements Solution {

    @Override
    public String intToRoman(int num) {
      if(num / 1000 != 0)
        return convert(num/1000, 1000) + intToRoman(num % 1000);
      if(num / 100 != 0)
        return convert(num/100, 100) + intToRoman(num % 100);
      if(num / 10 != 0)
        return convert(num/10, 10) + intToRoman(num % 10);
      return convert(num, 1);
    }

    private String convert(int digit, int pos){
      String l = "", m = "", h = "";
      if(pos == 1){
        l = "I";
        m = "V";
        h = "X";
      }
      if(pos == 10){
        l = "X";
        m = "L";
        h = "C";
      }
      if(pos == 100){
        l = "C";
        m = "D";
        h = "M";
      }
      if(pos == 1000){
        l = "M";
      }
      switch(digit){
        case 1:
          return l;
        case 2:
          return l + l;
        case 3:
          return l + l + l;
        case 4:
          return l + m;
        case 5:
          return m;
        case 6:
          return m + l;
        case 7:
          return m + l + l;
        case 8:
          return m + l + l + l;
        case 9:
          return l + h;
        default:
          return "";
      }
    }

  }

  public static void main(String args[]) {
    int num;
    String result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      num = 111;
      result = s.intToRoman(num);
      System.out.println(result);

      num = 3999;
      result = s.intToRoman(num);
      System.out.println(result);
    }


  }

}
