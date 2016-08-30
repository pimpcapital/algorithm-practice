package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;
import com.beijunyi.leetcode.category.solution.UnionFind;

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
      if(r > 0 && board[r - 1][c] == 'O') {
        rs.add(r - 1);
        cs.add(c);
      }
      if(r < rows - 1 && board[r + 1][c] == 'O') {
        rs.add(r + 1);
        cs.add(c);
      }
      if(c > 0 && board[r][c - 1] == 'O') {
        rs.add(r);
        cs.add(c - 1);
      }
      if(c < cols - 1 && board[r][c + 1] == 'O') {
        rs.add(r);
        cs.add(c + 1);
      }
    }

    private void killSurrounded() {
      for(int r = 1; r < rows - 1; r++)
        for(int c = 1; c < cols - 1; c++)
          if(board[r][c] == 'O') board[r][c] = 'X';
    }

    private void restoreRescued() {
      for(int r = 0; r < rows; r++)
        for(int c = 0; c < cols; c++)
          if(board[r][c] == 'S') board[r][c] = 'O';
    }

  }

  public static class Solution2 implements Solution, DepthFirstSearch {

    @Override
    public void solve(char[][] board) {
      if (board.length == 0 || board[0].length == 0)
        return;
      if (board.length < 2 || board[0].length < 2)
        return;
      int m = board.length, n = board[0].length;
      //Any 'O' connected to a boundary can't be turned to 'X', so ...
      //Start from first and last column, turn 'O' to '*'.
      for (int i = 0; i < m; i++) {
        if (board[i][0] == 'O')
          boundaryDFS(board, i, 0);
        if (board[i][n-1] == 'O')
          boundaryDFS(board, i, n-1);
      }
      //Start from first and last row, turn '0' to '*'
      for (int j = 0; j < n; j++) {
        if (board[0][j] == 'O')
          boundaryDFS(board, 0, j);
        if (board[m-1][j] == 'O')
          boundaryDFS(board, m-1, j);
      }
      //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 'O')
            board[i][j] = 'X';
          else if (board[i][j] == '*')
            board[i][j] = 'O';
        }
      }
    }
    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(char[][] board, int i, int j) {
      if (i < 0 || i > board.length - 1 || j <0 || j > board[0].length - 1)
        return;
      if (board[i][j] == 'O')
        board[i][j] = '*';
      if (i > 1 && board[i-1][j] == 'O')
        boundaryDFS(board, i-1, j);
      if (i < board.length - 2 && board[i+1][j] == 'O')
        boundaryDFS(board, i+1, j);
      if (j > 1 && board[i][j-1] == 'O')
        boundaryDFS(board, i, j-1);
      if (j < board[i].length - 2 && board[i][j+1] == 'O' )
        boundaryDFS(board, i, j+1);
    }

  }

  public static class Solution3 implements Solution, UnionFind {

    private int[] unionRoot; // union find set
    private boolean[] hasEdgeO; // whether an union has an 'O' which is on the edge of the matrix

    @Override
    public void solve(char[][] board) {
      if(board.length <= 2 || board[0].length <= 2) return;

      // init, every char itself is an union
      int height = board.length, width = board[0].length;
      unionRoot = new int[height * width];
      hasEdgeO = new boolean[unionRoot.length];
      for(int i = 0; i < unionRoot.length; i++) unionRoot[i] = i;
      for(int i = 0; i < hasEdgeO.length; i++) {
        int x = i / width;
        int y = i % width;
        hasEdgeO[i] = (board[x][y] == 'O' && (x == 0 || x == height - 1 || y == 0 || y == width - 1));
      }

      // iterate the matrix, for each char, union it + its upper char + its right char if they equals to each other
      for(int i = 0; i < unionRoot.length; i++) {
        int x = i / width;
        int y = i % width;
        int up = x - 1;
        int right = y + 1;
        if(up >= 0 && board[x][y] == board[up][y]) union(i, i - width);
        if(right < width && board[x][y] == board[x][right]) union(i, i + 1);
      }

      // for each char in the matrix, if it is an 'O' and its union doesn't has an 'edge O', the whole union should be setted as 'X'
      for(int i = 0; i < unionRoot.length; i++) {
        int x = i / width;
        int y = i % width;
        if(board[x][y] == 'O' && !hasEdgeO[findRoot(i)])
          board[x][y] = 'X';
      }
    }

    private void union(int x, int y) {
      int rootX = findRoot(x);
      int rootY = findRoot(y);
      // if there is an union has an 'edge O',the union after merge should be marked too
      boolean hasEdgeO = this.hasEdgeO[rootX] || this.hasEdgeO[rootY];
      unionRoot[rootX] = rootY;
      this.hasEdgeO[rootY] = hasEdgeO;
    }

    private int findRoot(int xy) {
      if(unionRoot[xy] == xy) return xy;
      unionRoot[xy] = findRoot(unionRoot[xy]);
      return unionRoot[xy];
    }
  }

  public static void main(String args[]) {
    char[][] board;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
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
