package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * For example,
 *   add(1); add(3); add(5);
 *   find(4) -> true
 *   find(7) -> false
 */
public class _170_TwoSumThreeDataStructureDesign implements Easy, PremiumQuestion {

  public interface Solution {

    void add(int number);

    boolean find(int value);

  }

  /**
   * It is really important to make the "add" method O(1) complexity for the test cases to pass.
   * Otherwise, there are tradeoffs to be made.
   *
   * Also, iterating list is much faster than iterating a map's key set.
   */
  public static class Solution1 implements Solution {

    private List<Integer> list = new ArrayList<>();
    private Map<Integer, Boolean> map = new HashMap<>();

    public void add(int number) {
      if(map.containsKey(number)) {
        map.put(number, true);
      } else {
        map.put(number, false);
        list.add(number);
      }
    }

    public boolean find(int value) {
      for(int num1 : list) {
        int num2 = value - num1;
        if(num2 == num1) {
          if(map.get(num1)) return true;
        } else {
          if(map.containsKey(num2)) return true;
        }
      }
      return false;
    }
  }

  public static void main(String args[]) {
    for(Solution s : Arrays.asList(new Solution1())) {
      s.add(1);
      s.add(3);
      s.add(5);
      System.out.println(s.find(4));
      System.out.println(s.find(7));
    }
  }


}
