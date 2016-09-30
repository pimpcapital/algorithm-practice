package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.NestedInteger;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *   Given the list [[1,1],2,[1,1]],
 *   By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 *     [1,1,2,1,1].
 *
 * Example 2:
 *   Given the list [1,[4,[6]]],
 *   By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 *     [1,4,6].
 */
public class _341_FlattenNestedListIterator implements Medium {

  public interface Solution extends Iterator<Integer> {

    void init(List<NestedInteger> nestedList);

  }

  public static class Solution1 implements Solution {

    private final Stack<List<NestedInteger>> stack = new Stack<>();
    private final Stack<Integer> indices = new Stack<>();
    private Integer current;

    @Override
    public void init(List<NestedInteger> nestedList) {
      stack.push(nestedList);
      indices.push(0);
    }

    @Override
    public boolean hasNext() {
      if(current != null) return true;
      if(stack.isEmpty()) return false;
      List<NestedInteger> list = stack.peek();
      int index = indices.pop();
      if(index == list.size()) {
        stack.pop();
      } else {
        indices.push(index + 1);
        NestedInteger next = list.get(index);
        if(next.isInteger()) {
          current = next.getInteger();
        } else {
          stack.push(next.getList());
          indices.push(0);
        }
      }
      return hasNext();
    }

    @Override
    public Integer next() {
      if(!hasNext()) throw new NoSuchElementException();
      Integer ret =  current;
      current = null;
      return ret;
    }

  }

  public static class Solution2 implements Solution {

    private Stack<Iterator<NestedInteger>> iterators = new Stack<>();
    private Integer next = null;

    @Override
    public void init(List<NestedInteger> nestedList) {
      iterators.push(nestedList.iterator());
    }

    @Override
    public boolean hasNext() {
      if(next != null) return true;
      while(!iterators.isEmpty() && !iterators.peek().hasNext()) iterators.pop();
      if(iterators.isEmpty()) return false;
      NestedInteger nextNi = iterators.peek().next();
      if(nextNi.isInteger()) {
        next = nextNi.getInteger();
        return true;
      } else {
        iterators.push(nextNi.getList().iterator());
        return hasNext();
      }
    }

    @Override
    public Integer next() {
      hasNext();
      Integer ret = next;
      next = null;
      return ret;
    }
  }

  public static void main(String args[]) {
    List<NestedInteger> nestedList;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nestedList = NestedInteger.fromArray(new Integer[] {1, 1}, 2, new Integer[] {1, 1});
      s.init(nestedList);
      while(s.hasNext()) {
        System.out.println(s.next());
      }
      System.out.println();

      nestedList = NestedInteger.fromArray(1, new Object[]{4, new Object[]{6}});
      s.init(nestedList);
      while(s.hasNext()) {
        System.out.println(s.next());
      }
      System.out.println();
    }
  }

}
