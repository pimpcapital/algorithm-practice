package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word
 * abbreviations:
 *
 * a) it                      --> it    (no abbreviation)
 *
 *      1
 * b) d|o|g                   --> d1g
 *
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 *
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 *
 * Example:
 *   Given dictionary = [ "deer", "door", "cake", "card" ]
 *
 *   isUnique("dear") -> false
 *   isUnique("cart") -> true
 *   isUnique("cane") -> false
 *   isUnique("make") -> true
 */
public class _288_UniqueWordAbbreviation implements Easy, PremiumQuestion {

  public interface Solution {

    void init(String[] dictionary);

    boolean isUnique(String word);

  }

  public static class Solution1 implements Solution {

    private Map<String, Set<String>> index;

    @Override
    public void init(String[] dictionary) {
      index = new HashMap<>();
      for(String word : dictionary) {
        String abbr = toAbbr(word);
        Set<String> sameAbbrWords = index.get(abbr);
        if(sameAbbrWords == null) {
          sameAbbrWords = new HashSet<>();
          index.put(abbr, sameAbbrWords);
        }
        sameAbbrWords.add(word);
      }
    }

    @Override
    public boolean isUnique(String word) {
      String abbr = toAbbr(word);
      Set<String> sameAbbrWords = index.get(abbr);
      return sameAbbrWords == null || sameAbbrWords.size() == 1 && sameAbbrWords.contains(word);
    }

    private static String toAbbr(String word) {
      if(word.length() <= 2) return word;
      return word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
    }
  }

  public static class Solution2 implements Solution {

    private Map<String, String> index;

    @Override
    public void init(String[] dictionary) {
      index = new HashMap<>();
      for(String word : dictionary) {
        String abbr = toAbbr(word);
        String existing = index.get(abbr);
        if(existing == null) {
          index.put(abbr, word);
        } else if(!word.equals(existing)) {
          index.put(abbr, "");
        }
      }
    }

    @Override
    public boolean isUnique(String word) {
      String abbr = toAbbr(word);
      return !index.containsKey(abbr) || word.equals(index.get(abbr));
    }

    private static String toAbbr(String word) {
      if(word.length() <= 2) return word;
      return word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
    }
  }

  public static void main(String args[]) {
    String[] dictionary;
    String word;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      dictionary = new String[] {"dig"};
      s.init(dictionary);
      word = "dog";
      result = s.isUnique(word);
      System.out.println(result);
    }
  }


}
