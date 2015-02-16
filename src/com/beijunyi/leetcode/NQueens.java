package com.beijunyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beijunyi.leetcode.difficulty.Hard;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 *
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class NQueens implements Hard {

  public static class Solution {
    public List<String[]> solveNQueens(int n) {
      char[] row = new char[n];
      Arrays.fill(row, '.');
      String rowStr = new String(row);
      String[] board = new String[n];
      Arrays.fill(board, rowStr);
      List<String[]> result = new ArrayList<>();
      placeNextQueen(n, 0, board, result);
      return result;
    }

    private void placeNextQueen(int n, int r, String[] board, List<String[]> result) {
      if(r == n) {
        result.add(board.clone());
        return;
      }
      char[] row = board[r].toCharArray();
      for(int c = 0; c < n; c++) {
        if(canSurvive(board, r, c)) {
          row[c] = 'Q';
          board[r] = new String(row);
          placeNextQueen(n, r + 1, board, result);
          row[c] = '.';
          board[r] = new String(row);
        }
      }
    }

    private boolean canSurvive(String[] board, int row, int col) {
      for(int r = 0; r < row; r++) {
        String rowState = board[r];
        if(rowState.charAt(col) == 'Q')
          return false;
        int diff = row - r;
        int dl = col - diff;
        if(dl >= 0 && rowState.charAt(dl) == 'Q')
          return false;
        int dr = col + diff;
        if(dr < rowState.length() && rowState.charAt(dr) == 'Q')
          return false;
      }
      return true;
    }
  }

  public static void main(String args[]) {
    List<String[]> results = new Solution().solveNQueens(12);

    for(String[] result : results) {
      for(String row : result)
        System.out.println(row);
      System.out.println();
    }
  }

}
