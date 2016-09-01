package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are
 * connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location
 * (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black
 * pixels.
 *
 * For example, given the following image:
 *   [
 *     "0010",
 *     "0110",
 *     "0100"
 *   ]
 *   and x = 0, y = 2,
 *   Return 6.
 */
public class _302_SmallestRectangleEnclosingBlackPixels implements Hard, PremiumQuestion {

  public interface Solution {
    int minArea(char[][] image, int x, int y);
  }

  public static class Solution1 implements Solution, BreadthFirstSearch {
    private char[][] image;
    private int rows;
    private int cols;

    @Override
    public int minArea(char[][] image, int x, int y) {
      this.image = image;
      rows = image.length;
      cols = rows != 0 ? image[0].length : 0;

      int left = Integer.MAX_VALUE;
      int right = Integer.MIN_VALUE;
      int top = Integer.MAX_VALUE;
      int bottom = Integer.MIN_VALUE;
      Queue<int[]> toProcess = new LinkedList<>();
      add(y, x, toProcess);

      int[] next;
      while((next = toProcess.poll()) != null) {
        int c = next[0];
        int r = next[1];
        left = Math.min(c, left);
        right = Math.max(c, right);
        top = Math.min(r, top);
        bottom = Math.max(r, bottom);
        addNeighbours(c, r, toProcess);
      }

      return (right - left + 1) * (bottom - top + 1);
    }

    private void addNeighbours(int c, int r, Queue<int[]> toProcess) {
      if(c > 0 && image[r][c - 1] == '1') add(c - 1, r, toProcess);
      if(c < cols - 1 && image[r][c + 1] == '1') add(c + 1, r, toProcess);
      if(r > 0 && image[r - 1][c] == '1') add(c , r - 1, toProcess);
      if(r < rows - 1 && image[r + 1][c] == '1') add(c, r + 1, toProcess);
    }

    private void add(int c, int r, Queue<int[]> toProcess) {
      toProcess.offer(new int[] {c , r});
      image[r][c] = '0';
    }

  }

  public static class Solution2 implements Solution, DepthFirstSearch {

    private int minX, minY, maxX, maxY;

    @Override
    public int minArea(char[][] image, int x, int y) {
      minX = minY = Integer.MAX_VALUE;
      maxX = maxY = Integer.MIN_VALUE;
      if(image == null || image.length == 0 || image[0].length == 0) return 0;
      dfs(image, x, y);
      return (maxX - minX + 1) * (maxY - minY + 1);
    }

    private void dfs(char[][] image, int x, int y) {
      int m = image.length, n = image[0].length;
      if(x < 0 || y < 0 || x >= m || y >= n || image[x][y] == '0') return;
      image[x][y] = '0';
      minX = Math.min(minX, x);
      maxX = Math.max(maxX, x);
      minY = Math.min(minY, y);
      maxY = Math.max(maxY, y);
      dfs(image, x + 1, y);
      dfs(image, x - 1, y);
      dfs(image, x, y - 1);
      dfs(image, x, y + 1);
    }

  }

  public static class Solution3 implements Solution, BinarySearch {

    private char[][] mImage;
    private int row;
    private int col;

    public int minArea(char[][] image, int x, int y) {
      this.mImage = image;
      row = image.length;
      col = image[0].length;
      int top = searchRow(0, x, true);
      int bottom = searchRow(x, row - 1, false);
      int left = searchCol(0, y, true);
      int right = searchCol(y, col - 1, false);
      return (bottom - top + 1) * (right - left + 1);
    }

    private int searchRow(int start, int end, boolean isTop) {
      while(start + 1 < end) {
        int mid = start + (end - start) / 2;
        if(isTop == findInRow(mid)) end = mid;
        else start = mid;
      }

      return isTop ? (findInRow(start) ? start : end) : (findInRow(end) ? end : start);
    }

    private int searchCol(int start, int end, boolean isLeft) {
      while(start + 1 < end) {
        int mid = start + (end - start) / 2;
        if(isLeft == findInCol(mid)) end = mid;
        else start = mid;
      }

      return isLeft ? (findInCol(start) ? start : end) : (findInCol(end) ? end : start);
    }

    private boolean findInRow(int x) {
      for(int i = 0; i < col; i++) {
        if(mImage[x][i] == '1') {
          return true;
        }
      }
      return false;
    }

    private boolean findInCol(int y) {
      for(int i = 0; i < row; i++) {
        if(mImage[i][y] == '1') {
          return true;
        }
      }
      return false;
    }
  }

  public static void main(String args[]) {
    char[][] image;
    int x;
    int y;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3())) {
      image = new char[][]{
        {'0', '0', '1', '0'},
        {'0', '1', '1', '0'},
        {'0', '1', '0', '0'},
      };
      x = 0;
      y = 2;
      result = s.minArea(image, x, y);
      System.out.println(result);

      image = new char[][]{
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '0', '1', '1', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '0', '0', '0', '0', '1', '0', '0', '0', '1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}
      };
      x = 17;
      y = 63;
      result = s.minArea(image, x, y);
      System.out.println(result);

    }
  }

}
