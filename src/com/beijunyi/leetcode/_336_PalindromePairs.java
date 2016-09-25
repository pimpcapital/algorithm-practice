package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.WithTrie;

/**
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation
 * of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *   Given words = ["bat", "tab", "cat"]
 *   Return [[0, 1], [1, 0]]
 *   The palindromes are ["battab", "tabbat"]
 *
 * Example 2:
 *   Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 *   Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 *   The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class _336_PalindromePairs implements Hard {

  public interface Solution {
    List<List<Integer>> palindromePairs(String[] words);
  }

  public static class Solution1 implements Solution, WithTrie {

    @Override
    public List<List<Integer>> palindromePairs(String[] words) {
      TrieNode trie = indexWordsReversely(words);
      List<List<Integer>> result = new ArrayList<>();
      for(int i = 0; i < words.length; i++) {
        String word = words[i];
        for(int matched : findWordsWithPrefix(word, trie)) {
          if(matched == i) continue;
          String candidate = words[matched];
          if(candidate.length() > word.length()) {
            if(isPalindrome(candidate, 0, candidate.length() - word.length() - 1)) result.add(Arrays.asList(i, matched));
          } else {
            if(isPalindrome(word, candidate.length(), word.length() - 1)) result.add(Arrays.asList(i, matched));
          }
        }
      }
      return result;
    }

    private static TrieNode indexWordsReversely(String[] words) {
      TrieNode root = new TrieNode();
      for(int i = 0; i < words.length; i++) {
        String word = words[i];
        indexWordReversely(word, word.length() - 1, root).index = i;
      }
      return root;
    }

    private static TrieNode indexWordReversely(String word, int offset, TrieNode node) {
      if(offset == -1) return node;
      int c = word.charAt(offset) - 'a';
      if(node.children[c] == null) node.children[c] = new TrieNode();
      return indexWordReversely(word, offset - 1, node.children[c]);
    }

    private static List<Integer> findWordsWithPrefix(String prefix, TrieNode root) {
      List<Integer> ret = new ArrayList<>();
      search(prefix, 0, root, ret);
      return ret;
    }

    private static void search(String word, int offset, TrieNode node, List<Integer> result) {
      if(node.index != -1)
        result.add(node.index);
      if(offset == word.length()) {
        for(TrieNode child : node.children) {
          if(child != null) search(word, offset, child, result);
        }
      } else {
        int c = word.charAt(offset) - 'a';
        TrieNode child = node.children[c];
        if(child != null) search(word, offset + 1, child, result);
      }
    }

    private static boolean isPalindrome(String word, int start, int end) {
      while(start < end) {
        if(word.charAt(start) != word.charAt(end)) return false;
        start++;
        end--;
      }
      return true;
    }

    private static class TrieNode {
      private TrieNode[] children = new TrieNode[26];
      private int index = -1;
    }

  }

  /**
   * There are several cases to be considered that isPalindrome(s1 + s2):
   *
   * Case 1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
   *
   * Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
   *
   * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is
   *         palindrome.
   *
   * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut],
   *         then s1+s2 is palindrome.
   *
   * To make the search faster, build a HashMap to store the String-idx pairs.
   */
  public static class Solution2 implements Solution {
    @Override
    public List<List<Integer>> palindromePairs(String[] words) {
      List<List<Integer>> res = new ArrayList<>();
      if(words == null || words.length == 0){
        return res;
      }
      //build the map save the key-val pairs: String - idx
      HashMap<String, Integer> map = new HashMap<>();
      for(int i = 0; i < words.length; i++){
        map.put(words[i], i);
      }

      //special cases: "" can be combine with any palindrome string
      if(map.containsKey("")){
        int blankIdx = map.get("");
        for(int i = 0; i < words.length; i++){
          if(isPalindrome(words[i])){
            if(i == blankIdx) continue;
            res.add(Arrays.asList(blankIdx, i));
            res.add(Arrays.asList(i, blankIdx));
          }
        }
      }

      //find all string and reverse string pairs
      for(int i = 0; i < words.length; i++){
        String cur_r = reverseStr(words[i]);
        if(map.containsKey(cur_r)){
          int found = map.get(cur_r);
          if(found == i) continue;
          res.add(Arrays.asList(i, found));
        }
      }

      //find the pair s1, s2 that
      //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
      //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
      for(int i = 0; i < words.length; i++){
        String cur = words[i];
        for(int cut = 1; cut < cur.length(); cut++){
          if(isPalindrome(cur.substring(0, cut))){
            String cut_r = reverseStr(cur.substring(cut));
            if(map.containsKey(cut_r)){
              int found = map.get(cut_r);
              if(found == i) continue;
              res.add(Arrays.asList(found, i));
            }
          }
          if(isPalindrome(cur.substring(cut))){
            String cut_r = reverseStr(cur.substring(0, cut));
            if(map.containsKey(cut_r)){
              int found = map.get(cut_r);
              if(found == i) continue;
              res.add(Arrays.asList(i, found));
            }
          }
        }
      }

      return res;
    }

    public String reverseStr(String str){
      StringBuilder sb= new StringBuilder(str);
      return sb.reverse().toString();
    }

    public boolean isPalindrome(String s){
      int i = 0;
      int j = s.length() - 1;
      while(i <= j){
        if(s.charAt(i) != s.charAt(j)){
          return false;
        }
        i++;
        j--;
      }
      return true;
    }

  }

  /**
   * 3 Cases:
   * +---s1---+---s2--+     +---s1---+-s2-+    +-s1-+---s2---+
   * |abcdefgh|hgfedcba|    |abcdxyyx|dcba|    |abcd|xyyxdcba|
   */
  public static class Solution3 implements Solution {
    @Override
    public List<List<Integer>> palindromePairs(String[] words) {
      Map<String, Integer> wordsLookup = indexWords(words);
      reverseWords(words);
      Map<String, Integer> reverseLookup = indexWords(words);
      List<List<Integer>> ret = new ArrayList<>();
      pairUp(wordsLookup, reverseLookup, ret, false); // covers case 1 and 2
      pairUp(reverseLookup, wordsLookup, ret, true);  // covers case 3
      return ret;
    }

    private static Map<String, Integer> indexWords(String[] words) {
      Map<String, Integer> ret = new HashMap<>();
      for(int i = 0; i < words.length; i++) ret.put(words[i], i);
      return ret;
    }

    private static void reverseWords(String[] words) {
      for(int i = 0; i < words.length; i++) words[i] = new StringBuilder(words[i]).reverse().toString();
    }

    private void pairUp(Map<String, Integer> left, Map<String, Integer> right, List<List<Integer>> ret, boolean flipOrder) {
      for(Map.Entry<String, Integer> e : left.entrySet()) {
        String word = e.getKey();
        int index = e.getValue();
        int substringLimit = flipOrder ? word.length() - 1 : word.length();
        for(int i = 0; i <= substringLimit; i++) {
          String prefix = word.substring(0, i);
          Integer match = right.get(prefix);
          if(match != null && index != match && isPalindrome(word, i)) {
            if(flipOrder) ret.add(Arrays.asList(match, index));
            else ret.add(Arrays.asList(index, match));
          }
        }
      }
    }

    private static boolean isPalindrome(String word, int i) {
      int left = i;
      int right = word.length() - 1;
      while(left < right) {
        if(word.charAt(left++) != word.charAt(right--)) return false;
      }
      return true;
    }

  }

  public static void main(String args[]) {
    String[] words;
    List<List<Integer>> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      words = new String[] {"a", ""};
      result = s.palindromePairs(words);
      System.out.println(result);

      words = new String[] {"a","abc","aba",""};
      result = s.palindromePairs(words);
      System.out.println(result);

      words = new String[] {"abcd", "dcba", "lls", "s", "sssll"};
      result = s.palindromePairs(words);
      System.out.println(result);

      System.out.println();
    }
  }

}
