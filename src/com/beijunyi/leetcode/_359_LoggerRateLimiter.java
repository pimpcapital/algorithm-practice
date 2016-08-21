package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if
 * and only if it is not printed in the last 10 seconds.
 *
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given
 * timestamp, otherwise returns false.
 *
 * It is possible that several messages arrive roughly at the same time.
 *
 * Example:
 *   Logger logger = new Logger();
 *
 *   // logging string "foo" at timestamp 1
 *   logger.shouldPrintMessage(1, "foo"); returns true;
 *
 *   // logging string "bar" at timestamp 2
 *   logger.shouldPrintMessage(2,"bar"); returns true;
 *
 *   // logging string "foo" at timestamp 3
 *   logger.shouldPrintMessage(3,"foo"); returns false;
 *
 *   // logging string "bar" at timestamp 8
 *   logger.shouldPrintMessage(8,"bar"); returns false;
 *
 *   // logging string "foo" at timestamp 10
 *   logger.shouldPrintMessage(10,"foo"); returns false;
 *
 *   // logging string "foo" at timestamp 11
 *   logger.shouldPrintMessage(11,"foo"); returns true;
 */
public class _359_LoggerRateLimiter implements Easy, PremiumQuestion {

  public interface Solution {

    void init();

    boolean shouldPrintMessage(int timestamp, String message);

  }

  public static class Solution1 implements Solution {

    private Map<String, Integer> last;

    @Override
    public void init() {
      last = new HashMap<>();
    }

    @Override
    public boolean shouldPrintMessage(int timestamp, String message) {
      if(!last.containsKey(message) || last.get(message) <= timestamp - 10) {
        last.put(message, timestamp);
        return true;
      }
      return false;
    }
  }

  public static void main(String args[]) {
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      s.init();
      result = s.shouldPrintMessage(1, "foo");
      System.out.println(result);
      result = s.shouldPrintMessage(2, "bar");
      System.out.println(result);
      result = s.shouldPrintMessage(3, "foo");
      System.out.println(result);
      result = s.shouldPrintMessage(8, "foo");
      System.out.println(result);
      result = s.shouldPrintMessage(10, "bar");
      System.out.println(result);
      result = s.shouldPrintMessage(11, "foo");
      System.out.println(result);

    }
  }

}
