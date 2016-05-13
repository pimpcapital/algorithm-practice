package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to
 * next().
 *
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 *
 * Call next() gets you 1, the first element in the list.
 *
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 *
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 *
 * Hint:
 *  Think of "looking ahead". You want to cache the next element.
 *  Is one variable sufficient? Why or why not?
 *  Test your design with call order of peek() before next() vs next() before peek().
 *  For a clean implementation, check out Google's guava library source code.
 *
 * Follow up:
 *  How would you extend your design to be generic and work with all types, not just integer?
 */
public class _284_PeekingIterator implements Medium {

  public interface Solution extends Iterator<Integer> {

    void init(Iterator<Integer> iterator);

    // Returns the next element in the iteration without advancing the iterator.
    Integer peek();

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    Integer next();

    @Override
    boolean hasNext();
  }

  public static class Solution1 implements Solution {

    private Iterator<Integer> embedded = null;
    private Integer value = null;
    private boolean loaded = false;

    @Override
    public void init(Iterator<Integer> iterator) {
      embedded = iterator;
      value = null;
      loaded = false;
    }

    @Override
    public Integer peek() {
      load();
      return value;
    }

    @Override
    public Integer next() {
      load();
      if(value == null)
        throw new NoSuchElementException();
      loaded = false;
      return value;
    }

    @Override
    public boolean hasNext() {
      load();
      return value != null;
    }

    @Override
    public void remove() {
      if(!loaded)
        throw new IllegalStateException();
      embedded.remove();
    }

    private void load() {
      if(!loaded) {
        try {
          value = embedded.next();
        } catch(NoSuchElementException ignore) {
          value = null;
        }
        loaded = true;
      }
    }

  }

  public static class Solution2 implements Solution {

    private Iterator<Integer> iterator;
    private boolean hasPeeked;
    private Integer peekedElement;

    @Override
    public void init(Iterator<Integer> iterator) {
      if(iterator==null)
        throw new NullPointerException();
      else
        this.iterator = iterator;
    }

    @Override
    public Integer peek() {
      peekedElement = hasPeeked?peekedElement:iterator.next();
      hasPeeked = true;
      return peekedElement;
    }

    @Override
    public Integer next() {
      int nextElem = hasPeeked?peekedElement:iterator.next();
      hasPeeked = false;
      return nextElem;
    }

    @Override
    public boolean hasNext() {
      return hasPeeked || iterator.hasNext();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  public static void main(String args[]) {
    Iterator<Integer> iterator;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      iterator = Arrays.asList(1, 2, 3, 4).iterator();
      s.init(iterator);
      System.out.println(s.peek());
      System.out.println(s.next());
      System.out.println(s.hasNext());
      System.out.println(s.next());
      System.out.println(s.hasNext());
      System.out.println(s.peek());
      System.out.println(s.next());
      System.out.println(s.next());
      System.out.println(s.hasNext());
    }

  }

}
