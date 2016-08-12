package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your
 * method will be called repeatedly many times with different parameters. How would you optimize it?
 *
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 * and word2 and return the shortest distance between these two words in the list.
 *
 * For example,
 *   Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 *   Given word1 = “coding”, word2 = “practice”, return 3.
 *   Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 *   You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class _244_ShortestWordDistanceTwo implements Medium, PremiumQuestion {

  public interface Solution {

    void init(String[] words);

    int shortest(String word1, String word2);

  }

  public static class Solution1 implements Solution {

    private final Map<String, List<Integer>> indices = new HashMap<>();

    @Override
    public void init(String[] words) {
      for(int i = 0; i < words.length; i++) {
        String word = words[i];
        List<Integer> indicesForWord = indices.get(word);
        if(indicesForWord == null) {
          indicesForWord = new ArrayList<>();
          indices.put(word, indicesForWord);
        }
        indicesForWord.add(i);
      }
    }

    @Override
    public int shortest(String word1, String word2) {
      int min = Integer.MAX_VALUE;
      int i = 0, j = 0;
      List<Integer> indices1 = indices.get(word1);
      List<Integer> indices2 = indices.get(word2);
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
      s.init(words);
      word1 = "coding";
      word2 = "practice";
      result = s.shortest(word1, word2);
      System.out.println(result);

      word1 = "makes";
      word2 = "coding";
      result = s.shortest(word1, word2);
      System.out.println(result);
    }
  }

}
