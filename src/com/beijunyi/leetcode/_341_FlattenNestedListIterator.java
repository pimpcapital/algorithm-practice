package com.beijunyi.leetcode;

import java.util.Iterator;
import java.util.List;

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

    @Override
    public void init(List<NestedInteger> nestedList) {

    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Integer next() {
      return null;
    }

  }

}
