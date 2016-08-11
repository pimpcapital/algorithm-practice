package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Write a function to generate the generalized abbreviations of a word.
 *
 * Example:
 *   Given word = "word", return the following list (order does not matter):
 *   ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class _320_GeneralizedAbbreviation implements Medium, PremiumQuestion {

  public interface Solution {
    List<String> generateAbbreviations(String word);
  }

  public static class Solution1 implements Solution {

    @Override
    public List<String> generateAbbreviations(String word) {
      int mask = 0;
      int max = maxMask(word);
      List<String> ret = new ArrayList<>();
      while(mask <= max) {
        ret.add(maskChars(word, mask));
        mask++;
      }
      return ret;
    }

    private static int maxMask(String word) {
      int ret = 0;
      for(int i = 0; i < word.length(); i++) {
        ret |= (1 << i);
      }
      return ret;
    }

    private static String maskChars(String word, int mask) {
      StringBuilder sb = new StringBuilder();
      int skipped = 0;
      for(int i = 0; i < word.length(); i++) {
        if((mask & (1 << i)) != 0) {
          skipped++;
        } else {
          if(skipped != 0) {
            sb.append(skipped);
            skipped = 0;
          }
          sb.append(word.charAt(i));
        }
      }
      if(skipped != 0) sb.append(skipped);
      return sb.toString();
    }

  }

  public static void main(String args[]) {
    String word;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      word = "word";
      result = s.generateAbbreviations(word);
      System.out.println(result);
    }
  }


}
