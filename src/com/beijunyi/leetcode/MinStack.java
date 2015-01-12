package com.beijunyi.leetcode;

import java.util.*;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {

  long min;
  Stack<Long> stack;

  /**
   * The question is ask to construct One stack. So I am using one stack.
   *
   * The idea is to store the gap between the min value and the current value;
   *
   * The problem for my solution is the cast. I have no idea to avoid the cast. Since the possible gap between the current value and the min value could be Integer.MAXVALUE-Integer.MINVALUE;
   */
  public MinStack() {
    stack = new Stack<>();
  }

  public void push(int x) {
    if(stack.isEmpty()) {
      stack.push(0L);
      min = x;
    } else {
      stack.push(x - min);//Could be negative if min value needs to change
      if(x < min) min = x;
    }
  }

  public void pop() {
    if(stack.isEmpty()) return;

    long pop = stack.pop();

    if(pop < 0) min = min - pop;//If negative, increase the min value

  }

  public int top() {
    long top = stack.peek();
    if(top > 0) {
      return (int)(top + min);
    } else {
      return (int)(min);
    }
  }

  public int getMin() {
    return (int)min;
  }

  public static void main(String args[]) {
    MinStack ms = new MinStack();
    ms.push(5);
    ms.push(4);
    ms.push(6);
    System.out.println(ms.getMin());
    System.out.println(ms.top());
    ms.pop();
    ms.pop();
    System.out.println(ms.getMin());
    System.out.println(ms.top());
  }

}
