package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Some examples:
 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class _150_EvaluateReversePolishNotation implements Medium {

  public interface Solution {
    int evalRPN(String[] tokens);
  }

  public static class Solution1 implements Solution {
    @Override
    public int evalRPN(String[] tokens) {
      Stack<Integer> nums = new Stack<>();
      for(String t : tokens) {
        switch(t) {
          case "+":
            nums.push(nums.pop() + nums.pop());
            break;
          case "-":
            nums.push(-nums.pop() + nums.pop());
            break;
          case "*":
            nums.push(nums.pop() * nums.pop());
            break;
          case "/":
            int divider = nums.pop();
            nums.push(nums.pop() / divider);
            break;
          default:
            nums.push(Integer.valueOf(t));
        }
      }
      return nums.pop();
    }
  }

  public static void main(String args[]) {
    String[] tokens;
    int result;
    for(Solution s : Arrays.asList(new Solution1())) {
      tokens = new String[] {"4", "13", "5", "/", "+"};
      result = s.evalRPN(tokens);
      System.out.println(result);
    }
  }

}
