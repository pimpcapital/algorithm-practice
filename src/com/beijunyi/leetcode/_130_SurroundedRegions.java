package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * For example,
 *   X X X X
 *   X O O X
 *   X X O X
 *   X O X X
 *
 * After running your function, the board should be:
 *   X X X X
 *   X X X X
 *   X X X X
 *   X O X X
 */
public class _130_SurroundedRegions implements Medium {

  public interface Solution {
    void solve(char[][] board);
  }

  public static class Solution1 implements Solution, BreadthFirstSearch {

    private char[][] board;
    private int rows;
    private int cols;

    @Override
    public void solve(char[][] board) {
      this.board = board;
      rows = board.length;
      cols = rows == 0 ? 0 : board[0].length;
      if(rows <= 2 || cols <= 2) return;

      rescueCol(0);
      rescueCol(cols - 1);
      rescueRow(0);
      rescueRow(rows - 1);
      killSurrounded();
      restoreRescued();
    }

    private void rescueCol(int c) {
      for(int r = 0; r < rows; r++)
        if(board[r][c] == 'O') rescue(r, c);
    }

    private void rescueRow(int r) {
      for(int c = 0; c < cols; c++)
        if(board[r][c] == 'O') rescue(r, c);
    }

    private void rescue(int r, int c) {
      Stack<Integer> rs = new Stack<>();
      Stack<Integer> cs = new Stack<>();
      rs.push(r);
      cs.push(c);
      while(!rs.isEmpty()) {
        r = rs.pop();
        c = cs.pop();
        board[r][c] = 'S';
        addNeighbours(r, c, rs, cs);
      }
    }

    private void addNeighbours(int r, int c, Stack<Integer> rs, Stack<Integer> cs) {
      if(r != 0 && board[r - 1][c] == 'O') {
        rs.add(r - 1);
        cs.add(c);
      }
      if(r != rows - 1 && board[r + 1][c] == 'O') {
        rs.add(r + 1);
        cs.add(c);
      }
      if(c != 0 && board[r][c - 1] == 'O') {
        rs.add(r);
        cs.add(c - 1);
      }
      if(c != cols - 1 && board[r][c + 1] == 'O') {
        rs.add(r);
        cs.add(c + 1);
      }
    }

    private void killSurrounded() {
      for(int r = 0; r < rows; r++)
        for(int c = 0; c < cols; c++)
          if(board[r][c] == 'O') board[r][c] = 'X';
    }

    private void restoreRescued() {
      for(int r = 0; r < rows; r++)
        for(int c = 0; c < cols; c++)
          if(board[r][c] == 'S') board[r][c] = 'O';
    }

  }

  public static void main(String args[]) {
    char[][] board;

    for(Solution s : Arrays.asList(new Solution1())) {
      board = new char[][]{
        {'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'X'},
        {'X', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'X'},
      };
      s.solve(board);
      for(char[] row : board) {
        System.out.println(Arrays.toString(row));
      }
    }
  }

}
