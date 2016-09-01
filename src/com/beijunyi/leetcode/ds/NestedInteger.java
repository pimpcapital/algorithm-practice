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

  @Override
  public String toString() {
    return toString(this).toString();
  }

  public static StringBuilder toString(NestedInteger value) {
    StringBuilder ret = new StringBuilder();
    toString(value, ret);
    return ret;
  }

  private static void toString(NestedInteger value, StringBuilder sb) {
    if(value.isInteger()) {
      sb.append(value.getInteger());
    } else {
      sb.append('[');
      List<NestedInteger> children = value.getList();
      for(int i = 0; i < children.size(); i++) {
        if(i != 0) sb.append(',');
        sb.append(toString(children.get(i)));
      }
      sb.append(']');
    }
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
