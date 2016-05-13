package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in
 * str.
 *
 * Examples:
 *  pattern = "abba", str = "dog cat cat dog" should return true.
 *  pattern = "abba", str = "dog cat cat fish" should return false.
 *  pattern = "aaaa", str = "dog cat cat dog" should return false.
 *  pattern = "abba", str = "dog dog dog dog" should return false.
 *
 * Notes:
 *  You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single
 *  space.
 */
public class _290_WordPattern implements Easy {

  public interface Solution {
    boolean wordPattern(String pattern, String str);
  }

  public static class Solution1 implements Solution {

    @Override
    public boolean wordPattern(String pattern, String str) {
      String[] split = str.split(" ");
      String[] patternMap = new String[26];
      Set<String> mapped = new HashSet<>();
      if(split.length != pattern.length())
        return false;
      for(int i = 0; i < split.length; i++) {
        int idx = pattern.charAt(i) - 'a';
        if(patternMap[idx] == null) {
          if(!mapped.add(split[i])) {
            return false;
          }
          patternMap[idx] = split[i];
        } else if(!patternMap[idx].equals(split[i])) {
          return false;
        }
      }
      return true;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public boolean wordPattern(String pattern, String str) {
      String[] arr = str.split(" ");
      HashMap<Character, String> map = new HashMap<>();
      if(arr.length != pattern.length())
        return false;
      for(int i = 0; i < arr.length; i++) {
        char c = pattern.charAt(i);
        if(map.containsKey(c)) {
          if(!map.get(c).equals(arr[i]))
            return false;
        } else {
          if(map.containsValue(arr[i]))
            return false;
          map.put(c, arr[i]);
        }
      }
      return true;
    }
  }

  public static void main(String args[]) {
    String pattern;
    String str;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      pattern = "abba";
      str = "dog cat cat dog";
      System.out.println(s.wordPattern(pattern, str));

      pattern = "abba";
      str = "dog cat cat fish";
      System.out.println(s.wordPattern(pattern, str));

      pattern = "aaaa";
      str = "dog cat cat dog";
      System.out.println(s.wordPattern(pattern, str));

      pattern = "abba";
      str = "dog dog dog dog";
      System.out.println(s.wordPattern(pattern, str));

      pattern = "ab";
      str = "b c";
      System.out.println(s.wordPattern(pattern, str));

      pattern = "e";
      str = "eukera";
      System.out.println(s.wordPattern(pattern, str));

      pattern = "jquery";
      str = "jquery";
      System.out.println(s.wordPattern(pattern, str));
    }
  }

}
