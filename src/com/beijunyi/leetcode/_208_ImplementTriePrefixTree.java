package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
public class _208_ImplementTriePrefixTree implements Medium {

  public interface Solution {

    void init();

    // Inserts a word into the trie.
    void insert(String word);

    // Returns if the word is in the trie.
    boolean search(String word);

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    boolean startsWith(String prefix);
  }

  public static class Solution1 implements Solution {

    private TrieNode root;

    @Override
    public void init() {
      root = new TrieNode();
      root.markEnd();
    }

    @Override
    public void insert(String word) {
      TrieNode current = root;
      for(int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        current = current.child(c);
      }
      current.markEnd();
    }

    @Override
    public boolean search(String word) {
      TrieNode node = find(word);
      return node != null && node.isEnd();
    }

    @Override
    public boolean startsWith(String prefix) {
      TrieNode node = find(prefix);
      return node != null;
    }

    private TrieNode find(String prefix) {
      TrieNode current = root;
      for(int i = 0; i < prefix.length(); i++) {
        char c = prefix.charAt(i);
        current = current.find(c);
        if(current == null) return null;
      }
      return current;
    }

    private static class TrieNode {

      private static final int BASE = 'a' - 1;

      private TrieNode[] children;

      // Initialize your data structure here.
      private TrieNode() {
        children = new TrieNode[27];
      }

      public TrieNode child(char c) {
        int i = c - BASE;
        if(children[i] == null) children[i] = new TrieNode();
        return children[i];
      }

      public TrieNode find(char c) {
        int i = c - BASE;
        return children[i];
      }

      public void markEnd() {
        if(children[0] == null) children[0] = new TrieNode();
      }

      public boolean isEnd() {
        return children[0] != null;
      }

    }

  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1())) {
      s.init();
      s.insert("apple");
      System.out.println(s.search("app"));
      System.out.println(s.startsWith("app"));
    }

  }

}
