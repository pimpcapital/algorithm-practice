package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Examples:
 *   "123", 6 -> ["1+2+3", "1*2*3"]
 *   "232", 8 -> ["2*3+2", "2+3*2"]
 *   "105", 5 -> ["1*0+5","10-5"]
 *   "00", 0 -> ["0+0", "0-0", "0*0"]
 *   "3456237490", 9191 -> []
 */
public class _282_ExpressionAddOperators implements Hard {

  public interface Solution {
    List<String> addOperators(String num, int target);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<String> addOperators(String num, int target) {
      List<String> result = new ArrayList<>();
      addOperators(num, 0, 1, target, new StringBuilder(), result);
      return result;
    }

    private static void addOperators(String num, int offset, long mult, long target, StringBuilder path, List<String> result) {
      int maxLength = num.length() - offset;
      long value = 0;
      for(int l = 0; l < maxLength; l++) {
        int digit = num.charAt(offset + l) - '0';
        path.append(digit);
        if(l != 0 && value == 0) return;
        value = value * 10 + digit;

        int tail = offset + l + 1;
        long multedValue = mult * value;
        long updatedTarget = target - multedValue;
        if(tail == num.length()) {
          if(updatedTarget == 0) result.add(path.toString());
        } else {
          int reset = path.length();

          path.append('+');
          addOperators(num, tail, 1, updatedTarget, path, result);
          path.setLength(reset);

          path.append('-');
          addOperators(num, tail, -1, updatedTarget, path, result);
          path.setLength(reset);

          path.append('*');
          addOperators(num, tail, multedValue, target, path, result);
          path.setLength(reset);
        }
      }
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public List<String> addOperators(String num, int target) {
      List<String> rst = new ArrayList<>();
      if(num == null || num.length() == 0) return rst;
      helper(rst, "", num, target, 0, 0, 0);
      return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
      if(pos == num.length()){
        if(target == eval) rst.add(path);
        return;
      }
      for(int i = pos; i < num.length(); i++){
        if(i != pos && num.charAt(pos) == '0') break;
        long cur = Long.parseLong(num.substring(pos, i + 1));
        if(pos == 0){
          helper(rst, path + cur, num, target, i + 1, cur, cur);
        }
        else{
          helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
          helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
          helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
        }
      }
    }
  }


  public static void main(String args[]) {
    String num;
    int target;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = "123";
      target = 6;
      result = s.addOperators(num, target);
      System.out.println(result);

      num = "105";
      target = 5;
      result = s.addOperators(num, target);
      System.out.println(result);

      num = "00";
      target = 0;
      result = s.addOperators(num, target);
      System.out.println(result);

      num = "3456237490";
      target = 9191;
      result = s.addOperators(num, target);
      System.out.println(result);

      num = "999999999";
      target = 81;
      result = s.addOperators(num, target);
      System.out.println(result);
    }
  }

}
