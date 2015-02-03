package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Medium;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * For example:
 * Given "25525511135",
 *
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class RestoreIPAddress implements Medium {

  public static class Solution1 {
    public List<String> restoreIpAddresses(String s) {
      List<String> res = new ArrayList<>();
      int len = s.length();
      for(int i = 1; i < 4 && i < len - 2; i++) {
        for(int j = i + 1; j < i + 4 && j < len - 1; j++) {
          for(int k = j + 1; k < j + 4 && k < len; k++) {
            String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k, len);
            if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
              res.add(s1 + "." + s2 + "." + s3 + "." + s4);
            }
          }
        }
      }
      return res;
    }

    public boolean isValid(String s) {
      return !(s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255);
    }
  }

  public static class Solution2 {
    public List<String> restoreIpAddresses(String s) {
      List<String> result = new ArrayList<>();
      restoreIpAddresses(s, 0, "", 4, result);
      return result;
    }

    private void restoreIpAddresses(String s, int start, String prefix, int remain, List<String> result) {
      int end = Math.min(start + 3, s.length());
      if(end < start + 1 || remain == 1 && end != s.length())
        return;
      for(int i = remain == 1 ? end : start + 1; i <= end; i++) {
        if(i - start > 1 && s.charAt(start) == '0')
          continue;
        String part = s.substring(start, i);
        if(Integer.valueOf(part) > 255)
          continue;
        String address = remain != 4 ? prefix + "." + part : part;
        if(remain == 1)
          result.add(address);
        else
          restoreIpAddresses(s, i, address, remain - 1, result);
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().restoreIpAddresses("010010"));
    System.out.println(new Solution2().restoreIpAddresses("010010"));
  }

}
