package com.beijunyi.leetcode.ds;

public class Interval {

  public int start;
  public int end;

  public Interval(int s, int e) {
    start = s;
    end = e;
  }
  public Interval() {
    this(0, 0);
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + ']';
  }
}
