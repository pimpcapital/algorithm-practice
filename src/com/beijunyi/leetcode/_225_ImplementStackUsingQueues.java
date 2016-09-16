package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.difficulty.SillyQuestion;

/**
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 *
 * Notes:
 * 1) You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and
 *    is empty operations are valid.
 * 2) Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque
 *    (double-ended queue), as long as you use only standard operations of a queue.
 * 3) You may assume that all operations are valid (for example, no pop or top operations will be called on an empty
 *    stack).
 */
public class _225_ImplementStackUsingQueues implements Easy, SillyQuestion {

  public interface Solution {
    // Push element x onto stack.
    void push(int x);

    // Removes the element on top of the stack.
    void pop();

    // Get the top element.
    int top();

    // Return whether the stack is empty.
    boolean empty();
  }

  public static class Solution1 implements Solution {

    private Queue<Integer> q = new LinkedList<>();

    @Override
    public void push(int x) {
      q.offer(x);
    }

    @Override
    public void pop() {
      requeue(q.size() - 1);
      q.poll();
    }

    @Override
    public int top() {
      requeue(q.size() - 1);
      int ret = q.poll();
      q.offer(ret);
      return ret;
    }

    private void requeue(int n) {
      for(int i = 0; i < n; i++) {
        q.offer(q.poll());
      }
    }

    @Override
    public boolean empty() {
      return q.isEmpty();
    }
  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1())) {
      s.push(1);
      s.push(2);
      s.push(3);
      while(!s.empty()) {
        System.out.println(s.top());
        s.pop();
      }
    }

  }

}
