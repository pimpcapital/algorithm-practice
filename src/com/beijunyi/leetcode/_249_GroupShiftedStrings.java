package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep
 * "shifting" which forms the sequence:
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting
 * sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * A solution is:
 *   [
 *     ["abc","bcd","xyz"],
 *     ["az","ba"],
 *     ["acef"],
 *     ["a","z"]
 *   ]
 */
public class _249_GroupShiftedStrings implements Easy, PremiumQuestion {

  public interface Solution {
    List<List<String>> groupStrings(String[] strings);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<List<String>> groupStrings(String[] strings) {
      Map<String, List<String>> hashLookup = new HashMap<>();
      for(String str : strings) {
        StringBuilder sb = new StringBuilder(str.length() - 1);
        for(int i = 1; i < str.length(); i++) {
          sb.append((char) ('a' + ((str.charAt(i) - str.charAt(i - 1) + 26) % 26)));
        }
        String pattern = sb.toString();
        List<String> strWithSamePattern = hashLookup.get(pattern);
        if(strWithSamePattern == null) {
          strWithSamePattern = new ArrayList<>();
          hashLookup.put(pattern, strWithSamePattern);
        }
        strWithSamePattern.add(str);
      }
      return new ArrayList<>(hashLookup.values());
    }
  }

  public static void main(String args[]) {
    String[] strings;
    List<List<String>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      strings = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
      result = s.groupStrings(strings);
      for(List<String> group : result) {
        System.out.println(group);
      }
    }
  }

}
