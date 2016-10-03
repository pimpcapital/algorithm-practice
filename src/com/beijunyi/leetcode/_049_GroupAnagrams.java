package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an array of strings, group anagrams together.
 *
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 *   [
 *     ["ate", "eat","tea"],
 *     ["nat","tan"],
 *     ["bat"]
 *   ]
 * Note: All inputs will be in lower-case.
 */
public class _049_GroupAnagrams implements Medium {

  public interface Solution {
    List<List<String>> groupAnagrams(String[] strs);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<List<String>> groupAnagrams(String[] strs) {
      Map<String, List<String>> groups = new HashMap<>();
      for(String word : strs) {
        String signature = makeSignature(word);
        groups.compute(signature, (key, value) -> {
          if(value == null) value = new ArrayList<>();
          value.add(word);
          return value;
        });
      }
      return new ArrayList<>(groups.values());
    }

    private static String makeSignature(String word) {
      char[] chars = word.toCharArray();
      Arrays.sort(chars);
      return new String(chars);
    }

  }

  public static void main(String args[]) {
    String[] strs;
    List<List<String>> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
      result = s.groupAnagrams(strs);
      System.out.println(result);
    }
  }

}
