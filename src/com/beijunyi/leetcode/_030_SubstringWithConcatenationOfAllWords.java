package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
 *
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).
 */
public class _030_SubstringWithConcatenationOfAllWords implements Hard {

  public static class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
      List<Integer> result = new ArrayList<>();

      int len = L.length;
      int wl = L[0].length();

      if(len == 0 || wl == 0 || S.length() < len * wl)
        return result;

      Map<String, Integer> dict = new HashMap<>();

      for(String str : L)
        dict.put(str, (dict.containsKey(str) ? dict.get(str) : 0) + 1);

      // We only start from 0 ~ len - 1.
      for(int i = 0; i < wl; ++i) {
        // This map is used to store the remained word count in the directory.
        Map<String, Integer> map = new HashMap<>(dict);
        // Use queue to store current sequence. All the words in queue also should be in map.
        Queue<String> queue = new LinkedList<>();
        // Every time add one word.
        for(int j = i; j + wl <= S.length(); j += wl) {
          String str = S.substring(j, j + wl);
          // If this word is in directory.
          if(dict.containsKey(str)) {
            // Add the word into the sequence.
            queue.add(str);
            // We already have enough such word in the sequence so we need to move the starting point to next such word.
            if(map.get(str) == 0) {
              while(!str.equals(queue.element())) {
                String item = queue.remove();
                map.put(item, map.get(item) + 1);
              }
              queue.remove();
            } else
              map.put(str, map.get(str) - 1);

            // There are enough words in the sequence.
            if(queue.size() == L.length)
              result.add(j - wl * (L.length - 1));
          } else {
            queue.clear();
            map = new HashMap<>(dict);
          }
        }
      }

      return result;
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}));
  }
}
