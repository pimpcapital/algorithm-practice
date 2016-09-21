package com.beijunyi.leetcode;

import java.util.List;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the
 * minutes (0-59).
 *
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 *      8 04 02 01
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

  public static class Solution1 implements Solution {
    @Override
    public List<String> readBinaryWatch(int num) {
      return null;
    }
  }

}
