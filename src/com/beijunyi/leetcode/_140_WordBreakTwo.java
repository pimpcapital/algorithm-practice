package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class _140_WordBreakTwo implements Hard {

  public static class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
      ArrayList<Set<Integer>> links = new ArrayList<>(s.length() + 1);
      for(int i = 0; i <= s.length(); i++)
        links.add(i, new HashSet<Integer>());

      links.get(0).add(-1);

      for(int r = 1; r <= s.length(); r++) {
        for(int l = 0; l < r; l++) {
          String substr = s.substring(l, r);
          if(!links.get(l).isEmpty() && dict.contains(substr)) {
            links.get(r).add(l);
          }
        }
      }

      return backTrace(s, s.length(), links);
    }

    private List<String> backTrace(String s, int pos, ArrayList<Set<Integer>> links) {
      List<String> result = new ArrayList<>();
      if(pos == 0)
        result.add("");
      else {
        Set<Integer> parents = links.get(pos);
        for(int parent : parents) {
          String substr = s.substring(parent, pos);
          for(String parentStr : backTrace(s, parent, links))
            result.add((parent != 0 ? parentStr + " " : "")+ substr);
        }
      }
      return result;
    }
  }



  public static void main(String args[]) {
    System.out.println(new Solution().wordBreak("catsanddog", new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"))));
  }

}
