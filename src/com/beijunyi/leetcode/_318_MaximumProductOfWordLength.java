package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.BitManipulation;
import com.beijunyi.leetcode.failure.TimeLimitExceeded;

/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
 * share common letters. You may assume that each word will contain only lower case letters. If no such two words exist,
 * return 0.
 *
 * Example 1:
 *   Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 *   Return 16
 *   The two words can be "abcw", "xtfn".
 *
 * Example 2:
 *   Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 *   Return 4
 *   The two words can be "ab", "cd".
 *
 * Example 3:
 *   Given ["a", "aa", "aaa", "aaaa"]
 *   Return 0
 *   No such pair of words.
 */
public class _318_MaximumProductOfWordLength implements Medium, NotHardButTricky {

  public interface Solution {
    int maxProduct(String[] words);
  }

  public static class Solution1 implements Solution, TimeLimitExceeded {
    @Override
    public int maxProduct(String[] words) {
      // reverse index. equivalent to Map<Character, List<String>>. mapping from char to words that have the char
      List<List<Integer>> rIndex = new ArrayList<>();
      for(int i = 0; i < 26; i++) rIndex.add(new ArrayList<>());
      for(int i = 0; i < words.length; i++) {
        String word = words[i];
        for(int j = 0; j < word.length(); j++) {
          int c = word.charAt(j) - 'a';
          rIndex.get(c).add(i);
        }
      }

      int ret = 0;
      for(String word : words) {
        Set<Integer> unmatchables = new HashSet<>();
        for(int i = 0; i < word.length(); i++) {
          int c = word.charAt(i) - 'a';
          unmatchables.addAll(rIndex.get(c));
        }
        for(int i = 0; i < words.length; i++) {
          if(!unmatchables.contains(i)) ret = Math.max(ret, word.length() * words[i].length());
        }
      }
      return ret;
    }
  }

  public static class Solution2 implements Solution, BitManipulation {

    @Override
    public int maxProduct(String[] words) {
      if (words == null || words.length < 2)
        return 0;
      int len = words.length;
      int[] codes = new int[len];
      codes[0] = getCode(words[0]);
      int max = 0;
      for(int i = 1; i < len; i++){
        codes[i] = getCode(words[i]);
        for(int j = 0; j < i; j++){
          if((codes[i] & codes[j]) == 0 && words[i].length() * words[j].length() > max){
            max = words[i].length() * words[j].length();
          }
        }
      }
      return max;
    }

    private int getCode(String word){
      int code = 0;
      for(int i = 0; i < word.length(); i++){
        int bit = 1 << (word.charAt(i) - 'a');
        code |= bit;
      }
      return code;
    }
  }

  public static void main(String args[]) {
    String[] words;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      words = new String[] {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
      result = s.maxProduct(words);
      System.out.println(result);

      words = new String[] {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
      result = s.maxProduct(words);
      System.out.println(result);

      words = new String[] {"a", "aa", "aaa", "aaaa"};
      result = s.maxProduct(words);
      System.out.println(result);
    }
  }

}
