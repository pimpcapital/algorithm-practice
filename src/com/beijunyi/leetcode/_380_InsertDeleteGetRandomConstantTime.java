package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * 1) insert(val): Inserts an item val to the set if not already present.
 * 2) remove(val): Removes an item val from the set if present.
 * 3) getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 *               being returned.
 *
 * Example:
 *   // Init an empty set.
 *   RandomizedSet randomSet = new RandomizedSet();
 *
 *   // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 *   randomSet.insert(1);
 *
 *   // Returns false as 2 does not exist in the set.
 *   randomSet.remove(2);
 *
 *   // Inserts 2 to the set, returns true. Set now contains [1,2].
 *   randomSet.insert(2);
 *
 *   // getRandom should return either 1 or 2 randomly.
 *   randomSet.getRandom();
 *
 *   // Removes 1 from the set, returns true. Set now contains [2].
 *   randomSet.remove(1);
 *
 *   // 2 was already in the set, so return false.
 *   randomSet.insert(2);
 *
 *   // Since 1 is the only number in the set, getRandom always return 1.
 *   randomSet.getRandom();
 */
public class _380_InsertDeleteGetRandomConstantTime implements Hard {

  public interface Solution {

    /** Initialize your data structure here. */
    void init();

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    boolean insert(int val);

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    boolean remove(int val);

    /** Get a random element from the set. */
    int getRandom();

  }

  /**
   * Insertion:
   *   insert to end
   *
   * Remove:
   *   remove element and move the last element to fill the gap
   */
  public static class Solution1 implements Solution {

    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random random;

    @Override
    public void init() {
      map = new HashMap<>();
      list = new ArrayList<>();
      random = new Random();
    }

    @Override
    public boolean insert(int val) {
      if(map.containsKey(val)) return false;

      int length = map.size();
      if(list.size() == length) list.add(val);
      else list.set(length, val);
      map.put(val, length);

      return true;
    }

    public boolean remove(int val) {
      if(!map.containsKey(val)) return false;
      int pos = map.remove(val);
      int lastVal = list.get(map.size());
      if(lastVal != val) {
        list.set(pos, lastVal);
        map.put(lastVal, pos);
      }
      return true;
    }

    public int getRandom() {
      return list.get(random.nextInt(map.size()));
    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1())) {

      s.init();
      System.out.println(s.insert(1));
      System.out.println(s.remove(2));
      System.out.println(s.insert(2));
      System.out.println(s.getRandom());
      System.out.println(s.remove(1));
      System.out.println(s.insert(2));
      System.out.println(s.getRandom());
      System.out.println();

      s.init();
      System.out.println(s.remove(0));
      System.out.println(s.remove(0));
      System.out.println(s.insert(0));
      System.out.println(s.getRandom());
      System.out.println(s.remove(0));
      System.out.println(s.insert(0));
      System.out.println();

    }

  }


}
