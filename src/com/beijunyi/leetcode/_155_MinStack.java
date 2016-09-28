package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class _155_MinStack implements Easy {

  public interface Solution {
    void push(int x);
    void pop();
    int top();
    int getMin();
  }

  public static class Solution1 implements Solution {

    long min = Long.MAX_VALUE;
    Stack<Long> stack = new Stack<>();

    @Override
    public void push(int x) {
      if(stack.isEmpty()) {
        stack.push(0L);
        min = x;
      } else {
        stack.push(x - min);//Could be negative if min value needs to change
        if(x < min) min = x;
      }
    }

    @Override
    public void pop() {
      if(stack.isEmpty()) return;

      long pop = stack.pop();

      if(pop < 0) min = min - pop;//If negative, increase the min value

    }

    @Override
    public int top() {
      long top = stack.peek();
      if(top > 0) {
        return (int)(top + min);
      } else {
        return (int)(min);
      }
    }

    @Override
    public int getMin() {
      return (int)min;
    }
  }

  public static class Solution2 implements Solution {

    private final Stack<int[]> stack = new Stack<>();

    @Override
    public void push(int x) {
      stack.push(new int[]{x, Math.min(getMin(), x)});
    }

    @Override
    public void pop() {
      stack.pop();
    }

    @Override
    public int top() {
      return stack.peek()[0];
    }

    @Override
    public int getMin() {
      return stack.isEmpty() ? Integer.MAX_VALUE : stack.peek()[1];
    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      s.push(5);
      s.push(4);
      s.push(6);
      System.out.println(s.getMin());
      System.out.println(s.top());
      s.pop();
      s.pop();
      System.out.println(s.getMin());
      System.out.println(s.top());

      System.out.println();
    }

  }

}
