package com.beijunyi.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 *
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class LongestValidParentheses {

  public static class Solution1 {
    public int longestValidParentheses(String s) {
      Stack<Integer> stack = new Stack<Integer>();
      int max=0;
      int left = -1;
      for(int j=0;j<s.length();j++){
        if(s.charAt(j)=='(') stack.push(j);
        else {
          if (stack.isEmpty()) left=j;
          else{
            stack.pop();
            if(stack.isEmpty()) max=Math.max(max,j-left);
            else max=Math.max(max,j-stack.peek());
          }
        }
      }
      return max;
    }
  }

  public static class Solution2 {
    public int longestValidParentheses(String s) {
      return ltr(s, 0, s.length());
    }

    public int ltr(String s, int start, int end) {
      int left = start;
      int openLeft = 0;
      int max = 0;
      for(int i = start; i < end; i++) {
        if(s.charAt(i) == '(')
          openLeft++;
        else
          openLeft--;
        if(openLeft < 0) {
          int length = i - left;
          if(length > max)
            max = length;
          left = i + 1;
          openLeft = 0;
        }
      }
      if(openLeft == 0) {
        int length = end - left;
        if(length > max)
          max = length;
      } {
        int length = rtl(s, left, end);
        if(length > max)
          max = length;
      }
      return max;
    }

    public int rtl(String s, int start, int end) {
      int right = end;
      int openRight = 0;
      int max = 0;
      for(int i = end - 1; i >= start; i--) {
        if(s.charAt(i) == ')')
          openRight++;
        else
          openRight--;
        if(openRight < 0) {
          int length = right - (i + 1);
          if(length > max)
            max = length;
          right = i;
          openRight = 0;
        }
      }
      if(openRight == 0) {
        int length = right - start;
        if(length > max)
          max = length;
      }
      return max;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().longestValidParentheses("()(()"));
    System.out.println(new Solution2().longestValidParentheses("()(()"));
  }

}
