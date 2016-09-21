package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the
 * minutes (0-59).
 *
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 *     08 04 02 01
 *            *  *
 *  32 16 08 04 02 01
 *      *  *        *
 *
 * For example, the above binary watch reads "3:25".
 *
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times
 * the watch could represent.
 *
 * Example:
 *   Input: n = 1
 *   Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *
 * Note:
 *   1) The order of output does not matter.
 *   2) The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 *   3) The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it
 *      should be "10:02".
 */
public class _401_BinaryWatch implements Easy {

  public interface Solution {
    List<String> readBinaryWatch(int num);
  }

  public static class Solution1 implements Solution, BitManipulation {
    @Override
    public List<String> readBinaryWatch(int num) {
      List<String> ret = new ArrayList<>();
      for(int left = 0; left <= Math.min(4, num); left++) {
        List<Integer> leftInts = intsWithBits(12, left);
        if(leftInts.isEmpty()) continue;

        int right = num - left;
        List<Integer> rightInts = intsWithBits(60, right);
        if(rightInts.isEmpty()) continue;

        for(int leftInt : leftInts) {
          for(int rightInt : rightInts) {
            ret.add(leftInt + ":" + (rightInt < 10 ? "0" : "") + rightInt);
          }
        }
      }
      return ret;
    }

    private static List<Integer> intsWithBits(int max, int bits) {
      List<Integer> ret = new ArrayList<>();
      for(int i = 0; i < max; i++) {
        int num = i;
        int count = 0;
        while(num != 0) {
          num -= num & -num;
          if(++count > bits) break;
        }
        if(count == bits) ret.add(i);
      }
      return ret;
    }


  }

  public static class Solution2 implements Solution, BitManipulation {
    @Override
    public List<String> readBinaryWatch(int num) {
      List<String> times = new ArrayList<>();
      for(int h = 0; h < 12; h++)
        for(int m = 0; m < 60; m++)
          if(Integer.bitCount(h * 64 + m) == num)
            times.add(String.format("%d:%02d", h, m));
      return times;
    }
  }

  public static void main(String args[]) {
    int num;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      num = 0;
      result = s.readBinaryWatch(num);
      System.out.println(result);

      num = 1;
      result = s.readBinaryWatch(num);
      System.out.println(result);

      num = 4;
      result = s.readBinaryWatch(num);
      System.out.println(result);

      System.out.println();
    }

  }

}
