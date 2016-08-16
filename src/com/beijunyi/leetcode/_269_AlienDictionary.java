package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You
 * receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new
 * language. Derive the order of letters in this language.
 *
 * For example,
 * Given the following words in dictionary,
 *   [
 *     "wrt",
 *     "wrf",
 *     "er",
 *     "ett",
 *     "rftt"
 *   ]
 * The correct order is: "wertf".
 *
 * Note:
 *   You may assume all letters are in lowercase.
 *   If the order is invalid, return an empty string.
 *   There may be multiple valid order of letters, return any one of them is fine.
 */
public class _269_AlienDictionary implements Hard, PremiumQuestion {

  public interface Solution {
    String alienOrder(String[] words);
  }

  public static class Solution1 implements Solution {

    @Override
    public String alienOrder(String[] words) {
      return null;
    }

  }
}
