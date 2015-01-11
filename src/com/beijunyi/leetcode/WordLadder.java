package com.beijunyi.leetcode;

import java.util.*;

/**
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
 *
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log", "cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Note:
 *   Return 0 if there is no such transformation sequence.
 *   All words have the same length.
 *   All words contain only lowercase alphabetic characters.
 */
public class WordLadder {

  public static class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
      if(start == null || end == null) return 0;
      Queue<String> queue = new LinkedList<>();
      Set<String> visited = new HashSet<>();
      queue.add(start);
      queue.add(null);
      visited.add(start);
      int len = 1;
      while(true) {
        String str = queue.remove();
        if(str == null) {
          if(queue.isEmpty()) {
            return 0;
          }
          queue.add(null);
          len++;
          continue;
        } else if(str.equals(end)) {
          return len;
        }
        for(int i = 0; i < str.length(); i++) {
          char[] charArray = str.toCharArray();
          for(char c = 'a'; c <= 'z'; c++) { // optional if (c != charArray[i]) condition check here
            charArray[i] = c;
            String newStr = new String(charArray);
            if(dict.contains(newStr) && !visited.contains(newStr)) {
              queue.add(newStr);
              visited.add(newStr);
            }
          }
        }
      }
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().ladderLength("hit", "cog", new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
  }

}
