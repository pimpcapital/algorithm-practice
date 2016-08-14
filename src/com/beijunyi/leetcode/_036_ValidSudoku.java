package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Note:
 *   A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class _036_ValidSudoku implements Easy {

  public interface Solution {
    boolean isValidSudoku(char[][] board);
  }


  public static class Solution1 implements Solution {

    @Override
    public boolean isValidSudoku(char[][] board) {
      for(int i = 0; i < 9; i++) {
        if(!checkRow(board, i)) return false;
        if(!checkCol(board, i)) return false;
      }
      for(int x = 0; x < 9; x += 3) {
        for(int y = 0; y < 9; y += 3) {
          if(!checkSubBox(board, x, y)) return false;
        }
      }
      return true;
    }

    private static boolean checkSubBox(char[][] board, int x, int y) {
      boolean[] checked = new boolean[9];
      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
          if(!checkNum(checked, board, x + i, y + j)) return false;
        }
      }
      return true;
    }

    private static boolean checkRow(char[][] board, int row) {
      boolean[] checked = new boolean[9];
      for(int i = 0; i < 9 ; i++) {
        if(!checkNum(checked, board, row, i)) return false;
      }
      return true;
    }

    private static boolean checkCol(char[][] board, int col) {
      boolean[] checked = new boolean[9];
      for(int i = 0; i < 9 ; i++) {
        if(!checkNum(checked, board, i, col)) return false;
      }
      return true;
    }

    private static boolean checkNum(boolean checked[], char[][] board, int x, int y) {
      char c = board[x][y];
      if(c == '.') return true;
      int num = c - '1';
      if(checked[num]) return false;
      checked[num] = true;
      return true;
    }
  }

  public static void main(String args[]) {
    char[][] board;
    boolean result;

    for(Solution s : Arrays.asList(new Solution1())) {
      board = new char[][] {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
      };
      result = s.isValidSudoku(board);
      System.out.println(result);
    }


  }

}
