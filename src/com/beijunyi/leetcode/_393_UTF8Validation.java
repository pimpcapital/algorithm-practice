package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 * 1. For 1-byte character, the first bit is a 0, followed by its unicode code.
 * 2. For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most
 *    significant 2 bits being 10.
 * This is how the UTF-8 encoding would work:
 *
 *   Char. number range  |        UTF-8 octet sequence
 *      (hexadecimal)    |              (binary)
 *   --------------------+---------------------------------------------
 *   0000 0000-0000 007F | 0xxxxxxx
 *   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 *
 * Note:
 *   The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data.
 *   This means each integer represents only 1 byte of data.
 *
 * Example 1:
 *   data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
 *
 *   Return true.
 *   It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 *
 * Example 2:
 *   data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
 *
 *   Return false.
 *   The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
 *   The next byte is a continuation byte which starts with 10 and that's correct.
 *   But the second continuation byte does not start with 10, so it is invalid.
 */
public class _393_UTF8Validation implements Medium {

  public interface Solution {
    boolean validUtf8(int[] data);
  }

  public static class Solution1 implements Solution {

    private static final int FIRST_ONE_MASK = 1 << 7;
    private static final int FIRST_TWO_MASK = FIRST_ONE_MASK | (1 << 6);

    @Override
    public boolean validUtf8(int[] data) {
      int i = 0;
      while(i < data.length) {
        int tail = readTailLength(data[i++]);
        if(tail < 0) return false;
        for(int t = 0; t < tail; t++) {
          if(i == data.length) return false;
          if(!checkHeader(data[i++])) return false;
        }
      }
      return true;
    }

    private static int readTailLength(int first) {
      int ret = 0;
      for(int i = 7; i >= 0; i--) {
        int mask = 1 << i;
        if((first & mask) != 0) ret++;
        else break;
      }
      if(ret == 0) return 0;
      if(ret == 1 || ret > 4) return -1;
      return ret - 1;
    }

    private static boolean checkHeader(int b) {
      return (b & FIRST_TWO_MASK) == FIRST_ONE_MASK;
    }

  }

  public static void main(String args[]) {
    int[] data;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      data = new int[] {0b01111111};
      result = s.validUtf8(data);
      System.out.println(result);

      data = new int[] {0b10111111};
      result = s.validUtf8(data);
      System.out.println(result);

      data = new int[] {0b11011111, 0b10111111};
      result = s.validUtf8(data);
      System.out.println(result);

      data = new int[] {0b11101111, 0b10111111, 0b10111111};
      result = s.validUtf8(data);
      System.out.println(result);
    }
  }

}
