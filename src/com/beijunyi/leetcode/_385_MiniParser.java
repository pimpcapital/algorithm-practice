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
      int numStart = -1;
      int numEnd = -1;
      Stack<NestedInteger> lists = new Stack<>();
      NestedInteger head = null;
      s += ",";
      for(int i = 0; i < s.length(); i++) {
        boolean pop = false;
        switch(s.charAt(i)) {
          case ']':
            pop = true;
          case ',':
            numEnd = i;
            break;
          case '[':
            NestedInteger next = new NestedInteger();
            if(lists.isEmpty()) head = next;
            else lists.peek().add(next);
            lists.push(next);
            break;
          default:
            if(numStart == -1) numStart = i;
        }
        if(numStart >= 0 && numEnd > numStart) {
          NestedInteger newInt = new NestedInteger(Integer.valueOf(s.substring(numStart, numEnd)));
          if(lists.isEmpty()) head = newInt;
          else lists.peek().add(newInt);
          numStart = -1;
          numEnd = -1;
        }
        if(pop) lists.pop();
      }
      return head;
    }

  }

  public static class Solution2 implements Solution {
    @Override
    public NestedInteger deserialize(String s) {
      int numStart = -1;
      Stack<NestedInteger> ints = new Stack<>();
      for(int i = 0; i < s.length(); i++) {
        switch(s.charAt(i)) {
          case ']':
            addInt(s, numStart, i, ints);
            numStart = -1;
            NestedInteger tail = ints.pop();
            if(ints.isEmpty()) return tail;
            else ints.peek().add(tail);
            break;
          case ',':
            addInt(s, numStart, i, ints);
            numStart = -1;
            break;
          case '[':
            ints.push(new NestedInteger());
            break;
          default:
            if(numStart == -1) numStart = i;
        }
      }
      addInt(s, numStart, s.length(), ints);
      return ints.pop();
    }

    private static void addInt(String s, int start, int end, Stack<NestedInteger> ints) {
      if(start >= 0 && end > start) {
        NestedInteger newInt = new NestedInteger(Integer.valueOf(s.substring(start, end)));
        if(ints.isEmpty()) ints.push(newInt);
        else ints.peek().add(newInt);
      }
    }

  }

  public static class Solution3 implements Solution {
    @Override
    public NestedInteger deserialize(String s) {
      int sign = 0;
      int value = 0;
      Stack<NestedInteger> ints = new Stack<>();
      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        switch(c) {
          case ']':
            addInt(sign, value, ints);
            sign = 0;
            value = 0;
            NestedInteger tail = ints.pop();
            if(ints.isEmpty()) return tail;
            else ints.peek().add(tail);
            break;
          case ',':
            addInt(sign, value, ints);
            sign = 0;
            value = 0;
            break;
          case '[':
            ints.push(new NestedInteger());
            break;
          case '-':
            sign = -1;
            break;
          default:
            if(sign == 0) sign = 1;
            value = value * 10 + (c - '0');
        }
      }
      addInt(sign, value, ints);
      return ints.pop();
    }

    private static void addInt(int sign, int value, Stack<NestedInteger> ints) {
      if(sign != 0) {
        NestedInteger newInt = new NestedInteger(value * sign);
        if(ints.isEmpty()) ints.push(newInt);
        else ints.peek().add(newInt);
      }
    }

  }

  public static void main(String args[]) {
    String str;
    NestedInteger result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      str = "[]";
      result = s.deserialize(str);
      System.out.println(result);

      str = "[-1]";
      result = s.deserialize(str);
      System.out.println(result);

      str = "[-1,-2]";
      result = s.deserialize(str);
      System.out.println(result);

      str = "[123,456,[788,799,833],[[]],10,[]]";
      result = s.deserialize(str);
      System.out.println(result);

      System.out.println();
    }
  }

}
