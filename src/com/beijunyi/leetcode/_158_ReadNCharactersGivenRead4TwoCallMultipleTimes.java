package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.AbstractReader4;
import com.beijunyi.leetcode.ds.Reader4;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note:
 * The read function may be called multiple times.
 */
public class _158_ReadNCharactersGivenRead4TwoCallMultipleTimes implements Hard, PremiumQuestion {

  public interface Solution extends Reader4 {
    int read(char[] buf, int n);
  }

  public static class Solution1 extends AbstractReader4 implements Solution {

    private boolean completed = false;
    private char[] buf4 = new char[4];
    private int leftoverStart = 0;
    private int leftoverLength = 0;

    @Override
    public int read(char[] buf, int n) {
      int start = copyFromLeftover(buf, n);
      if(completed) return start;
      for(int i = start; i < n;) {
        int read = read4(buf4);
        int copy = Math.min(read, n - i);
        System.arraycopy(buf4, 0, buf, i, copy);
        leftoverStart = copy;
        leftoverLength = read;
        i += copy;
        if(read < 4) {
          completed = true;
          return i;
        }
      }
      return n;
    }

    private int copyFromLeftover(char[] buf, int n) {
      int copy = Math.min(leftoverLength - leftoverStart, n);
      System.arraycopy(buf4, leftoverStart, buf, 0, copy);
      leftoverStart += copy;
      return copy;
    }

  }

  public static class Solution2 extends AbstractReader4 implements Solution {

    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    @Override
    public int read(char[] buf, int n) {
      int i = 0;
      while(i < n) {
        if(buffPtr == 0) buffCnt = read4(buff);
        if(buffCnt == 0) break;
        while(i < n && buffPtr < buffCnt) buf[i++] = buff[buffPtr++];
        if(buffPtr == buffCnt) buffPtr = 0;
      }
      return i;
    }
  }

  public static void main(String args[]) {
    String data;
    char[] buf;
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      data = "12345678";
      s.initData(data);

      n = 1;
      buf = new char[7];
      result = s.read(buf, n);
      System.out.println(result);
      System.out.println(Arrays.toString(buf));

      n = 3;
      buf = new char[3];
      result = s.read(buf, n);
      System.out.println(result);
      System.out.println(Arrays.toString(buf));

      n = 10;
      buf = new char[10];
      result = s.read(buf, n);
      System.out.println(result);
      System.out.println(Arrays.toString(buf));

      System.out.println();
    }
  }

}
