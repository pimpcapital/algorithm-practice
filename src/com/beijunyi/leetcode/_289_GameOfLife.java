package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.InPlace;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
 * by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 *
 *  1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 *  2. Any live cell with two or three live neighbors lives on to the next generation.
 *  3. Any live cell with more than three live neighbors dies, as if by over-population..
 *  4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current state.
 *
 * Follow up:
 *  1. Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
 *     cells first and then use their updated values to update other cells.
 *  2. In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 *     problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class _289_GameOfLife implements Medium {

  public interface Solution {
    void gameOfLife(int[][] board);
  }

  /**
   * Time: O(r*c), Space: O(1) where
   *   r is the number of rows in the board
   *   c is the number of columns in the board
   */
  public static class Solution1 implements Solution, InPlace {
    @Override
    public void gameOfLife(int[][] board) {
      int rows = board.length;
      int columns = rows != 0 ? board[0].length : 0;

      for(int r = 0; r < rows; r++) {
        for(int c = 0; c < columns; c++) {
          int neighbours = countNeighbours(board, r, c);
          if(board[r][c] == 1) { // is alive
            if(neighbours == 2 || neighbours == 3) board[r][c] += 2; // continue to live if rule 2 applies
          } else { // is dead
            if(neighbours == 3) board[r][c] += 2; // live if rule 4 applies
          }
        }
      }

      for(int r = 0; r < rows; r++)
        for(int c = 0; c < columns; c++)
          board[r][c] /= 2;
    }

    private static int countNeighbours(int[][] board, int row, int column) {
      int count = 0;
      for(int r = row - 1; r <= row + 1; r++) {
        if(r < 0 || r == board.length) continue;
        for(int c = column - 1; c <= column + 1; c++) {
          if(c < 0 || c == board[r].length) continue;
          if(r == row && c == column) continue;
          if(board[r][c] % 2 == 1) count++;
        }
      }
      return count;
    }
  }

  public static void main(String[] args) {
    int[][] board = new int[][] {
      {0,1,1,0,1,1,1,0},
      {1,1,0,1,1,0,0,0},
      {0,0,0,0,0,1,1,1},
      {1,1,1,1,0,0,0,0},
      {0,1,0,0,1,1,1,0}
    };
    Solution s = new Solution1();
    s.gameOfLife(board);
    for(int[] row : board)
      System.out.println(Arrays.toString(row));
  }

}
