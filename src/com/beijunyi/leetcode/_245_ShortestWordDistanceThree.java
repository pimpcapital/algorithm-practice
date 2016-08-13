package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 * Note:
 * You may assume word1 and word2 are both in the list.
 */
public class _245_ShortestWordDistanceThree implements Medium, PremiumQuestion {

  public interface Solution {
    int shortestWordDistance(String[] words, String word1, String word2);
  }

  public static class Solution1 implements Solution {

    @Override
    public int shortestWordDistance(String[] words, String word1, String word2) {
      List<Integer> indices1 = new ArrayList<>();
      List<Integer> indices2 = new ArrayList<>();
      for(int i = 0; i < words.length; i++) {
        if(words[i].equals(word1)) indices1.add(i);
        if(words[i].equals(word2)) indices2.add(i);
      }
      int min = Integer.MAX_VALUE;
      int i = 0, j = 0;
      while(i < indices1.size() && j < indices2.size()) {
        int diff = Math.abs(indices2.get(j) - indices1.get(i));
        if(diff != 0) min = Math.min(min, diff);
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
      result = s.shortestWordDistance(words, word1, word2);
      System.out.println(result);

      words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
      word1 = "makes";
      word2 = "makes";
      result = s.shortestWordDistance(words, word1, word2);
      System.out.println(result);
    }

  }

}
