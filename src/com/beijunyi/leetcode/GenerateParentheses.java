package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.beijunyi.leetcode.difficulty.Medium;

public class GenerateParentheses implements Medium {

  public static class Solution {
    public List<String> generateParenthesis(int n) {
      List<String> result = new ArrayList<>();
      generateParenthesis(n, n, new char[n * 2], 0, result);
      return result;
    }

    public void generateParenthesis(int left, int right, char[] current, int pos, List<String> result) {
      if(left == 0 && right == 0) {
        result.add(new String(current));
        return;
      }
      if(left != 0) {
        current[pos] = '(';
        generateParenthesis(left - 1, right, current, pos + 1, result);
      }
      if(right > left) {
        current[pos] = ')';
        generateParenthesis(left, right - 1, current, pos + 1, result);
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().generateParenthesis(8));
  }

}
