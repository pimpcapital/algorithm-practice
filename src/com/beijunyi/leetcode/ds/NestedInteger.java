package com.beijunyi.leetcode.ds;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

  private Object value;

  public NestedInteger() {
    this.value = new ArrayList<NestedInteger>();
  }

  public NestedInteger(int value) {
    this.value = value;
  }

  public NestedInteger(List<NestedInteger> nestedValues) {
    this.value = nestedValues;
  }

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger() {
    return value instanceof Integer;
  }

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger() {
    return isInteger() ? (int) value : null;
  }

  // Set this NestedInteger to hold a nested list and adds a nested integer to it.
  @SuppressWarnings("unchecked")
  public void add(NestedInteger ni) {
    ((List) value).add(ni);
  }

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  @SuppressWarnings("unchecked")
  public List<NestedInteger> getList() {
    return isInteger() ? null : (List) value;
  }

  public static List<NestedInteger> fromArray(Object... values) {
    List<NestedInteger> ret = new ArrayList<>();
    for(Object v : values) {
      if(v instanceof Integer) {
        ret.add(new NestedInteger((int) v));
      } else if(v instanceof Object[]) {
        ret.add(new NestedInteger(fromArray((Object[]) v)));
      } else {
        throw new UnsupportedOperationException();
      }
    }
    return ret;
  }

}
