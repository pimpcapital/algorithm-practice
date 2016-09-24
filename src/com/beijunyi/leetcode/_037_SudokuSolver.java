package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.Backtracking;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * Empty cells are indicated by the character '.'.
 *
 * You may assume that there will be only one unique solution.
 */
public class _037_SudokuSolver implements Hard {

  public interface Solution {
    void solveSudoku(char[][] board);
  }

  public static class Solution1 implements Solution, Backtracking {

    private char[][] board;
    private boolean[][] rows = new boolean[9][9];
    private boolean[][] cols = new boolean[9][9];
    private boolean[][] squares = new boolean[9][9];
    private List<int[]> pendings = new ArrayList<>();


    @Override
    public void solveSudoku(char[][] board) {
      this.board = board;
      loadData();
      solve(0);
    }

    private void loadData() {
      for(int r = 0; r < 9; r++) {
        for(int c = 0; c < 9; c++) {
          char numChar = board[r][c];
          if(numChar == '.') pendings.add(new int[] {r, c});
          else registerCell(r, c, numChar - '1', true);
        }
      }
    }

    private void registerCell(int row, int col, int num, boolean state) {
      rows[row][num] = state;
      cols[col][num] = state;
      squares[squareIndex(row, col)][num] = state;
    }

    private boolean solve(int index) {
      if(index == pendings.size()) return true;
      int[] cell = pendings.get(index);
      int row = cell[0];
      int col = cell[1];
      for(int num = 0; num < 9; num++) {
        if(rows[row][num]) continue;
        if(cols[col][num]) continue;
        if(squares[squareIndex(row, col)][num]) continue;
        registerCell(row, col, num, true);
        if(!solve(index + 1)) registerCell(row, col, num, false);
        else {
          board[row][col] = (char) (num + '1');
          return true;
        }
      }
      return false;
    }

    private int squareIndex(int row, int col) {
      return (row / 3) * 3 + col / 3;
    }
  }

  public static void main(String args[]) {
    char[][] board;

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
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
      };
      s.solveSudoku(board);
      for(char[] row : board) {
        System.out.println(Arrays.toString(row));
      }
    }

  }

}
