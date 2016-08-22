package com.beijunyi.leetcode.ds;

public abstract class AbstractReader4 implements Reader4 {

  private char[] data;
  private int pos = 0;

  @Override
  public void initData(String data) {
    this.data = data.toCharArray();
    this.pos = 0;
  }

  @Override
  public int read4(char[] buf) {
    int copy = Math.min(4, data.length - pos);
    System.arraycopy(data, pos, buf, 0, copy);
    pos += 4;
    return copy;
  }

}
