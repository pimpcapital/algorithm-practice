package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.NestedInteger;

public class _385_MiniParser implements Medium {

  public interface Solution {
    NestedInteger deserialize(String s);
  }

  public static class Solution1 implements Solution {
    @Override
    public NestedInteger deserialize(String s) {
      Stack<NestedInteger> ints = new Stack<>();
      int[] offset = new int[] {0};
      NestedInteger head = null;
      while(offset[0] < s.length()) {
        String nextToken = nextToken(s, offset);
        switch(nextToken) {
          case "[":
            NestedInteger nextInt = new NestedInteger();
            if(!ints.isEmpty()) ints.peek().add(nextInt);
            else head = nextInt;
            ints.push(nextInt);
            break;
          case "]":
            ints.pop();
            break;
          default:
            NestedInteger value = new NestedInteger(Integer.valueOf(nextToken));
            if(ints.isEmpty()) return value;
            ints.peek().add(value);
        }
      }
      return head;
    }

    private static String nextToken(String s, int[] offset) {
      int start = offset[0];
      int end = start;
      while(offset[0] < s.length()) {
        switch(s.charAt(offset[0]++)) {
          case '[':
            return s.substring(start, offset[0]);
          case ']':
            if(start + 1 == offset[0]) return s.substring(start, offset[0]);
            end = offset[0]--;
            break;
          case ',':
            end = offset[0];
            break;
        }
        if(end != start) break;
      }
      if(end == start) end = s.length();
      return s.substring(start, end);
    }
  }

  public static void main(String args[]) {
    String str;
    NestedInteger result;

    for(Solution s : Arrays.asList(new Solution1())) {
      str = "[]";
      result = s.deserialize(str);
      System.out.println(result);
    }
  }

}
