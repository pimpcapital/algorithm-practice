package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;

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

  public interface Solution {
    List<String> wordBreak(String s, Set<String> dict);
  }

  public static class Solution1 implements Solution {
    @Override
    public List<String> wordBreak(String s, Set<String> dict) {
      ArrayList<Set<Integer>> links = new ArrayList<>(s.length() + 1);
      for(int i = 0; i <= s.length(); i++)
        links.add(i, new HashSet<>());

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

  private static class Solution2 implements Solution, Backtracking, DynamicPrograming {

    @Override
    public List<String> wordBreak(String s, Set<String> dict) {
      List<String> ret = new ArrayList<>();
      boolean[] avoid = new boolean[s.length() + 1];
      wordBreak(s, 0, dict, new StringBuilder(), ret, avoid);
      return ret;
    }

    private static boolean wordBreak(String s, int start, Set<String> dict, StringBuilder sb, List<String> result, boolean[] avoid) {
      if(start == s.length()) {
        result.add(sb.toString());
        return true;
      }
      boolean success = false;
      for(int end = start + 1; end <= s.length(); end++) {
        if(avoid[end]) continue;
        String word = s.substring(start, end);
        if(dict.contains(word)) {
          int current = sb.length();
          if(current > 0) sb.append(" ");
          sb.append(word);
          success |= wordBreak(s, end, dict, sb, result, avoid);
          sb.setLength(current);
        }
      }
      avoid[start] = !success;
      return success;
    }

  }


  public static void main(String args[]) {
    String s;
    Set<String> dict;
    List<String> result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "catsanddog";
      dict = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
      result = solution.wordBreak(s, dict);
      System.out.println(result);
    }
  }

}
