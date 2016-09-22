package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.WithTrie;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Design a data structure that supports the following two operations:
 *   void addWord(word)
 *   bool search(word)
 *
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it
 * can represent any one letter.
 *
 * For example:
 *   addWord("bad")
 *   addWord("dad")
 *   addWord("mad")
 *   search("pad") -> false
 *   search("bad") -> true
 *   search(".ad") -> true
 *   search("b..") -> true
 *
 * Note:
 *   You may assume that all words are consist of lowercase letters a-z.
 *
 * Hint:
 *   You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree)
 *   first.
 */
public class _211_AddAndSearchWordDataStructureDesign implements Medium {

  public interface Solution {

    // Adds a word into the data structure.
    void addWord(String word);

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    boolean search(String word);

  }

  public static class Solution1 implements Solution, WithTrie {

    private final TrieNode root = new TrieNode(true);

    @Override
    public void addWord(String word) {
      root.addString(word, 0);
    }

    @Override
    public boolean search(String word) {
      return root.findString(word, 0);
    }

    private static class TrieNode {

      TrieNode[] children = new TrieNode[26];
      boolean end = false;

      TrieNode(boolean end) {
        this.end = end;
      }

      TrieNode() {
        this(false);
      }

      void addString(String str, int offset) {
        if(offset == str.length()) end = true;
        else {
          int cIndex = str.charAt(offset) - 'a';
          if(children[cIndex] == null) children[cIndex] = new TrieNode();
          children[cIndex].addString(str, offset + 1);
        }
      }

      boolean findString(String str, int offset) {
        if(offset == str.length()) return end;
        else {
          char c = str.charAt(offset);
          if(c == '.') {
            for(TrieNode child : children) {
              if(child != null && child.findString(str, offset + 1)) return true;
            }
            return false;
          } else {
            int cIndex = c - 'a';
            return children[cIndex] != null && children[cIndex].findString(str, offset + 1);
          }
        }
      }

    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1())) {
      s.addWord("bad");
      s.addWord("dad");
      s.addWord("mad");
      System.out.println(s.search("pad"));
      System.out.println(s.search("bad"));
      System.out.println(s.search(".ad"));
      System.out.println(s.search("b.."));
    }

  }

}
