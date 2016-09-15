package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Iterative;
import com.beijunyi.leetcode.category.solution.Recursive;
import com.beijunyi.leetcode.category.solution.WithStack;

/**
 * Given an encoded string, return it's decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *   s = "3[a]2[bc]", return "aaabcbc".
 *   s = "3[a2[c]]", return "accaccacc".
 *   s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class _394_DecodeString implements Medium {

  public interface Solution {
    String decodeString(String s);
  }

  public static class Solution1 implements Solution, Iterative, WithStack {
    @Override
    public String decodeString(String s) {
      Stack<Integer> repeats = new Stack<>();
      Stack<StringBuilder> tokens = new Stack<>();
      tokens.push(new StringBuilder());
      int repeat = 0;
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if(c >= '0' && c <= '9') {
          repeat = repeat * 10 + (c - '0');
        } else if(c == '[') {
          repeats.push(repeat);
          repeat = 0;
          tokens.push(new StringBuilder());
        } else if(c == ']') {
          StringBuilder sb = tokens.pop();
          int count = repeats.pop();
          while(count > 0) {
            tokens.peek().append(sb);
            count--;
          }
        } else {
          tokens.peek().append(c);
        }
      }
      return tokens.pop().toString();
    }
  }

  /**
   * Potentially faster solution as there is only one StringBuilder
   * However, this won't work if repeating 0 time is allowed i.e. 0[abc]
   */
  public static class Solution2 implements Solution, Recursive {
    @Override
    public String decodeString(String s) {
      StringBuilder sb = new StringBuilder();
      decodeString(s, 0, sb);
      return sb.toString();
    }

    private int decodeString(String s, int offset, StringBuilder sb) {
      int repeat = 0;
      int i = offset;
      while(i < s.length()) {
        char c = s.charAt(i);
        if(c >= '0' && c <= '9') {
          repeat = repeat * 10 + c - '0';
        } else if(c == '[') {
          int start = i + 1;
          while(repeat > 0) {
            i = decodeString(s, start, sb);
            repeat--;
          }
        } else if(c == ']') {
          return i;
        } else {
          sb.append(c);
        }
        i++;
      }
      return i;
    }
  }

  public static void main(String args[]) {
    String s;
    String result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "3[a2[c]]";
      result = solution.decodeString(s);
      System.out.println(result);

      System.out.println();
    }
  }

}
