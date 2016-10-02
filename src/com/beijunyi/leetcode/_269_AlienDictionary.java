package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.KahnsAlgorithm;
import com.beijunyi.leetcode.category.solution.TopologicalSorting;

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

  public static class Solution1 implements Solution, KahnsAlgorithm, TopologicalSorting {

    @Override
    public String alienOrder(String[] words) {
      if(words.length == 0) return "";
      if(words.length == 1) return words[0];
      boolean[][] matrix = new boolean[26][26];
      boolean[] present = new boolean[26];
      for(int i = 1; i < words.length; i++) {
        if(!processWord(words[i - 1], words[i], matrix, present)) return "";
      }
      return findOrder(matrix, present);
    }

    private static boolean processWord(String prev, String curr, boolean[][] matrix, boolean[] present) {
      int length = Math.min(prev.length(), curr.length());
      int compared;
      for(compared = 0; compared < length; compared++) {
        int prevIdx = prev.charAt(compared)  - 'a';
        int currIdx = curr.charAt(compared)  - 'a';
        present[prevIdx] = true;
        present[currIdx] = true;
        if(prev.charAt(compared) != curr.charAt(compared)) {
          if(matrix[currIdx][prevIdx]) return false;
          matrix[prevIdx][currIdx] = true;
          break;
        }
      }
      for(int i = compared; i < prev.length(); i++) present[prev.charAt(i) - 'a'] = true;
      for(int i = compared; i < curr.length(); i++) present[curr.charAt(i) - 'a'] = true;
      return compared < length
               || curr.length() <= prev.length(); // in the case that the compared parts of both are the same, check if the current word is the longer one
    }

    private static String findOrder(boolean[][] matrix, boolean[] present) {
      char[] ret = new char[26];
      int length = 0;
      Queue<Integer> q = new LinkedList<>(findNodesWithNoIncomingEdge(matrix));
      while(!q.isEmpty()) {
        int from = q.poll();
        if(!present[from]) continue;
        ret[length++] = (char) ('a' + from);
        for(int to = 0; to < 26; to++) {
          if(matrix[from][to]) {
            matrix[from][to] = false;
            if(!hasIncomingEdge(to, matrix)) q.offer(to);
          }
        }
      }
      for(int i = 0; i < 26; i++) {
        if(hasIncomingEdge(i, matrix)) return ""; // if there is any edge remained, there must be a cycle
      }
      return new String(ret, 0, length);
    }

    private static boolean hasIncomingEdge(int i, boolean matrix[][]) {
      for(boolean[] to : matrix) {
        if(to[i]) return true;
      }
      return false;
    }

    private static Set<Integer> findNodesWithNoIncomingEdge(boolean matrix[][]) {
      Set<Integer> ret = new HashSet<>();
      for(int i = 0; i < 26; i++) {
        if(!hasIncomingEdge(i, matrix)) ret.add(i);
      }
      return ret;
    }

  }

  private static class Solution2 implements Solution, TopologicalSorting {
    @Override
    public String alienOrder(String[] words) {
      Map<Character, Set<Character>> inbounds = processWords(words);
      return inbounds == null ? "" : establishOrder(inbounds);
    }

    private static Map<Character, Set<Character>> processWords(String[] words) {
      Map<Character, Set<Character>> ret = new HashMap<>();
      for(int i = 0; i < words.length; i++) {
        if(!processWord(i == 0 ? "" : words[i - 1], words[i], ret)) return null;
      }
      return ret;
    }

    private static boolean processWord(String word1, String word2, Map<Character, Set<Character>> ret) {
      int length = Math.min(word1.length(), word2.length());
      int i = 0;
      boolean isPrefix = true;
      for(; i < length; i++) {
        char c1 = word1.charAt(i);
        char c2 = word2.charAt(i);
        if(!ret.containsKey(c1)) ret.put(c1, new HashSet<>());
        if(c1 != c2) {
          ret.compute(c2, (key, inbounds) -> {
            if(inbounds == null) inbounds = new HashSet<>();
            inbounds.add(c1);
            return inbounds;
          });
          isPrefix = false;
          break;
        }
      }
      if(isPrefix && word2.length() < word1.length()) return false;
      for(int j = i; j < word1.length(); j++) {
        char c = word1.charAt(j);
        if(!ret.containsKey(c)) ret.put(c, new HashSet<>());
      }
      for(int j = i; j < word2.length(); j++) {
        char c = word2.charAt(j);
        if(!ret.containsKey(c)) ret.put(c, new HashSet<>());
      }
      return true;
    }

    private static String establishOrder(Map<Character, Set<Character>> inbounds) {
      StringBuilder sb = new StringBuilder();
      Queue<Character> q = new LinkedList<>(pickCharactersWithNoInbound(inbounds));
      while(!q.isEmpty()) {
        char next = q.poll();
        for(Set<Character> inboundSet : inbounds.values()) inboundSet.remove(next);
        q.addAll(pickCharactersWithNoInbound(inbounds));
        sb.append(next);
      }
      return inbounds.isEmpty() ? sb.toString() : "";
    }

    private static Set<Character> pickCharactersWithNoInbound(Map<Character, Set<Character>> inbound) {
      Set<Character> ret = new HashSet<>();
      Iterator<Map.Entry<Character, Set<Character>>> iterator = inbound.entrySet().iterator();
      while(iterator.hasNext()) {
        Map.Entry<Character, Set<Character>> entry = iterator.next();
        if(entry.getValue().isEmpty()) {
          ret.add(entry.getKey());
          iterator.remove();
        }
      }
      return ret;
    }

  }

  public static void main(String args[]) {
    String[] words;
    String order;
    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      words = new String[] {
        "wrt"
      };
      order = s.alienOrder(words);
      System.out.println(order);

      words = new String[] {
        "wrt",
        "wrf",
        "er",
        "ett",
        "rftt"
      };
      order = s.alienOrder(words);
      System.out.println(order);

      words = new String[] {
        "ab",
        "adc"
      };
      order = s.alienOrder(words);
      System.out.println(order);

      words = new String[] {
        "wrtkj",
        "wrt"
      };
      order = s.alienOrder(words);
      System.out.println(order);

      System.out.println();
    }
  }

}
