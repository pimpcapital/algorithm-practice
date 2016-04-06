package com.beijunyi.leetcode;

import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class _020_ValidParentheses implements Easy {

  public static class Solution {
    public boolean isValid(String s) {

      if (s==null||s.length()==0) return true;

      Stack<Character> stack= new Stack<>();

      char array[] = s.toCharArray();

      for(char e : array) {
        if(e == '(' || e == '{' || e == '[')
          stack.push(e);
        else if(e == ')' || e == '}' || e == ']') {
          if(stack.isEmpty()) return false;
          char temp = stack.pop();
          switch(e) {
            case ')':
              if(temp != '(') return false;
              break;
            case ']':
              if(temp != '[') return false;
              break;
            case '}':
              if(temp != '{') return false;
              break;
          }
        } else return false;
      }
      return stack.isEmpty();

    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().isValid("()[]{}"));
  }

}
