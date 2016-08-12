package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 *   You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class _243_ShortestWordDistance implements Easy, PremiumQuestion {

  public interface Solution {
    int shortestDistance(String[] words, String word1, String word2);
  }

  public static class Solution1 implements Solution {

    @Override
    public int shortestDistance(String[] words, String word1, String word2) {
      if(word1.equals(word2)) return 0;
      List<Integer> indices1 = new ArrayList<>();
      List<Integer> indices2 = new ArrayList<>();
      for(int i = 0; i < words.length; i++) {
        if(words[i].equals(word1)) indices1.add(i);
        else if(words[i].equals(word2)) indices2.add(i);
      }
      int min = Integer.MAX_VALUE;
      int i = 0, j = 0;
      while(i < indices1.size() && j < indices2.size()) {
        min = Math.min(min, Math.abs(indices2.get(j) - indices1.get(i)));
        if(i + 1 == indices1.size() || indices2.get(j) < indices1.get(i)) j++;
        else i++;
      }
      return min;
    }

  }

  public static void main(String args[]) {
    String[] words;
    String word1;
    String word2;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
      word1 = "coding";
      word2 = "practice";
      result = s.shortestDistance(words, word1, word2);
      System.out.println(result);

      words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
      word1 = "makes";
      word2 = "coding";
      result = s.shortestDistance(words, word1, word2);
      System.out.println(result);
    }

  }

}
