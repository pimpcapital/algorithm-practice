package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Design a Phone Directory which supports the following operations:
 *
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 *
 * Example:
 *   // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 *   PhoneDirectory directory = new PhoneDirectory(3);
 *
 *   // It can return any available phone number. Here we assume it returns 0.
 *   directory.get();
 *
 *   // Assume it returns 1.
 *   directory.get();
 *
 *   // The number 2 is available, so return true.
 *   directory.check(2);
 *
 *   // It returns 2, the only number that is left.
 *   directory.get();
 *
 *   // The number 2 is no longer available, so return false.
 *   directory.check(2);
 *
 *   // Release number 2 back to the pool.
 *   directory.release(2);
 *
 *   // Number 2 is available again, return true.
 *   directory.check(2);
 */
public class _379_DesignPhoneDirectory implements Medium {

  public interface Solution {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    void init(int maxNumbers);

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    int get();

    /** Check if a number is available or not. */
    boolean check(int number);

    void release(int number);

  }

  public static class Solution1 implements Solution {

    private Collection<Integer> numbers = new LinkedHashSet<>();

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    @Override
    public void init(int maxNumbers) {
      for(int i = 0; i < maxNumbers; i++) {
        numbers.add(i);
      }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    @Override
    public int get() {
      Iterator<Integer> it = numbers.iterator();
      if(it.hasNext()) {
        int ret = it.next();
        it.remove();
        return ret;
      } else {
        return -1;
      }
    }

    /** Check if a number is available or not. */
    @Override
    public boolean check(int number) {
      return numbers.contains(number);
    }

    /** Recycle or release a number. */
    @Override
    public void release(int number) {
      numbers.add(number);
    }

  }

  public static void main(String args[]) {
    for(Solution s : Arrays.asList(new Solution1())) {
      s.init(3);
      s.get();
      s.get();
      s.check(2);
      s.get();
      s.check(2);
      s.release(2);
      s.check(2);
    }
  }


}
