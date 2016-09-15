package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.WithTrie;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * For example,
 *   Given words = ["oath","pea","eat","rain"] and board =
 *   [
 *     ['o','a','a','n'],
 *     ['e','t','a','e'],
 *     ['i','h','k','r'],
 *     ['i','f','l','v']
 *   ]
 *   Return ["eat","oath"].
 * Note:
 *   You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Hint:
 *   1) You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
 *   2) If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind
 *     of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie?
 *     If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix
 *     Tree) first.
 */
public class _212_WordSearchTwo implements Hard {

  public interface Solution {
    List<String> findWords(char[][] board, String[] words);
  }

  public static class Solution1 implements Solution, Backtracking, WithTrie {

    private char board[][];
    private int rows;
    private int cols;

    @Override
    public List<String> findWords(char[][] board, String[] words) {
      this.board = board;
      rows = board.length;
      cols = rows == 0 ? 0 : board[0].length;
      Set<String> ret = new HashSet<>();
      if(rows == 0 || cols == 0) return Collections.emptyList();

      TrieNode trie = index(words);
      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < cols; c++) {
          findWords(r, c, trie, new StringBuilder(), ret);
        }
      }
      return new ArrayList<>(ret);
    }

    private void findWords(int row, int col, TrieNode trie, StringBuilder sb, Set<String> ret) {
      char c = board[row][col];
      board[row][col] = 0;
      if(trie.hasChild(c)) {
        int length = sb.length();
        trie = trie.child(c);
        sb.append(c);
        if(trie.isEnd()) ret.add(sb.toString());
        if(row != 0) findWords(row - 1, col, trie, sb, ret);
        if(row != rows - 1) findWords(row + 1, col, trie, sb, ret);
        if(col != 0) findWords(row, col - 1, trie, sb, ret);
        if(col != cols - 1) findWords(row, col + 1, trie, sb, ret);
        sb.setLength(length);
      }
      board[row][col] = c;
    }

    private static TrieNode index(String[] words) {
      TrieNode root = new TrieNode();
      for(String word : words) {
        root.insert(word);
      }
      return root;
    }

    private static class TrieNode {

      private final TrieNode[] children = new TrieNode[26];
      private boolean end;

      public TrieNode child(char c) {
        int index = c - 'a';
        if(children[index] == null) children[index] = new TrieNode();
        return children[index];
      }

      public boolean hasChild(char c) {
        int index = c - 'a';
        return index >= 0 && children[index] != null;
      }

      public void insert(String str) {
        insert(str, 0);
      }

      public void insert(String str, int offset) {
        if(offset == str.length()) {
          markEnd();
        } else {
          char c = str.charAt(offset);
          child(c).insert(str, offset + 1);
        }
      }

      public void markEnd() {
        end = true;
      }

      public boolean isEnd() {
        return end;
      }
    }
  }

  /**
   * Faster solution. Instead of using set, this solution modifies the Trie to prevent duplication.
   */
  public static class Solution2 implements Solution, Backtracking {

    @Override
    public List<String> findWords(char[][] board, String[] words) {
      List<String> res = new ArrayList<>();
      TrieNode root = buildTrie(words);
      for(int i = 0; i < board.length; i++) {
        for(int j = 0; j < board[0].length; j++) {
          dfs(board, i, j, root, res);
        }
      }
      return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
      char c = board[i][j];
      if(c == '#' || p.next[c - 'a'] == null) return;
      p = p.next[c - 'a'];
      if(p.word != null) {   // found one
        res.add(p.word);
        p.word = null;     // de-duplicate
      }

      board[i][j] = '#';
      if(i > 0) dfs(board, i - 1, j ,p, res);
      if(j > 0) dfs(board, i, j - 1, p, res);
      if(i < board.length - 1) dfs(board, i + 1, j, p, res);
      if(j < board[0].length - 1) dfs(board, i, j + 1, p, res);
      board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
      TrieNode root = new TrieNode();
      for(String w : words) {
        TrieNode p = root;
        for(char c : w.toCharArray()) {
          int i = c - 'a';
          if(p.next[i] == null) p.next[i] = new TrieNode();
          p = p.next[i];
        }
        p.word = w;
      }
      return root;
    }

    class TrieNode {
      TrieNode[] next = new TrieNode[26];
      String word;
    }
  }

  public static void main(String args[]) {
    char[][] board;
    String[] words;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      board = new char[][]{
        {'o', 'a', 'a', 'n'},
        {'e', 't', 'a', 'e'},
        {'i', 'h', 'k', 'r'},
        {'i', 'f', 'l', 'v'}
      };
      words = new String[] {"eat", "oath"};
      result = s.findWords(board, words);
      System.out.println(result);
    }
  }

}
