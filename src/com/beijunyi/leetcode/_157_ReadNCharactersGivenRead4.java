package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;
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
 *   The read function will only be called once for each test case.
 */
public class _157_ReadNCharactersGivenRead4 implements Medium, PremiumQuestion {

  public interface Solution extends Reader4 {
    int read(char[] buf, int n);
  }

  public static class Solution1 extends AbstractReader4 implements Solution {

    @Override
    public int read(char[] buf, int n) {
      for(int i = 0; i < n;) {
        char[] buf4 = new char[4];
        int read = read4(buf4);
        int copy = Math.min(read, n - i);
        System.arraycopy(buf4, 0, buf, i, copy);
        i += copy;
        if(copy < 4) return i;
      }
      return n;
    }

  }

  public static void main(String args[]) {
    String data;
    char[] buf;
    int n;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      data = "123456";
      n = 5;
      buf = new char[7];
      s.initData(data);
      result = s.read(buf, n);
      System.out.println(result);
      System.out.println(Arrays.toString(buf));

      data = "123456";
      n = 3;
      buf = new char[3];
      s.initData(data);
      result = s.read(buf, n);
      System.out.println(result);
      System.out.println(Arrays.toString(buf));

      data = "123456";
      n = 10;
      buf = new char[10];
      s.initData(data);
      result = s.read(buf, n);
      System.out.println(result);
      System.out.println(Arrays.toString(buf));
    }


  }

}
