package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.PremiumQuestion;
import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values
 * 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance,
 * where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * For example, given three people living at (0,0), (0,4), and (2,2):
 *   1 - 0 - 0 - 0 - 1
 *   |   |   |   |   |
 *   0 - 0 - 0 - 0 - 0
 *   |   |   |   |   |
 *   0 - 0 - 1 - 0 - 0
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 *
 * Hint:
 *   Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 */
public class _296_BestMeetingPoint implements Hard, PremiumQuestion {

  public interface Solution {
    int minTotalDistance(int[][] grid);
  }

  public static class Solution1 implements Solution {

    @Override
    public int minTotalDistance(int[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;

      List<Integer> I = new ArrayList<>();
      List<Integer> J = new ArrayList<>();

      for(int r = 0; r < rows; r++){
        for(int c = 0; c < cols; c++){
          if(grid[r][c] == 1){
            I.add(r);
            J.add(c);
          }
        }
      }

      return minDistanceInOneDimension(I) + minDistanceInOneDimension(J);
    }

    private static int minDistanceInOneDimension(List<Integer> list){
      int ret = 0;
      Collections.sort(list);
      int start = 0;
      int end = list.size() - 1;
      while(start < end) {
        ret += list.get(end--) - list.get(start++);
      }

      return ret;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public int minTotalDistance(int[][] grid) {
      int rows = grid.length, cols = grid[0].length;
      int[] countAtRow = new int[rows], countAtCol = new int[cols];
      for(int i = 0; i < rows; ++i)
        for(int j = 0; j < cols; ++j)
          if(grid[i][j] == 1) {
            ++countAtRow[i];
            ++countAtCol[j];
          }
      int total = 0;
      for(int[] dimension : new int[][]{countAtRow, countAtCol}) {
        int start = 0, end = dimension.length - 1;
        while(start < end) {
          int k = Math.min(dimension[start], dimension[end]);
          total += k * (end - start);
          if((dimension[start] -= k) == 0) ++start;
          if((dimension[end] -= k) == 0) --end;
        }
      }
      return total;
    }
  }

  /**
   * This problem is converted to find the median value on x-axis and y-axis.
   */
  public static class Solution3 implements Solution {
    @Override
    public int minTotalDistance(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;

      ArrayList<Integer> cols = new ArrayList<>();
      ArrayList<Integer> rows = new ArrayList<>();
      for(int i = 0; i < m; i++) {
        for(int j = 0; j < n; j++) {
          if(grid[i][j] == 1) {
            cols.add(j);
            rows.add(i);
          }
        }
      }

      int sum = 0;
      for(Integer i : rows) {
        sum += Math.abs(i - rows.get(rows.size() / 2));
      }
      Collections.sort(cols);
      for(Integer i : cols) {
        sum += Math.abs(i - cols.get(cols.size() / 2));
      }

      return sum;
    }
  }

  public static class Solution4 implements Solution {
    @Override
    public int minTotalDistance(int[][] grid) {
      return vDistance(grid) + hDistance(grid);
    }

    private static int vDistance(int[][] grid) {
      int rows = grid.length;
      int cols = rows != 0 ? grid[0].length : 0;

      int count = 0;

      int startRow = 0;
      int endRow = rows - 1;
      int startCol = 0;
      int endCol = 0;
      while(startRow < endRow) {
        while(startCol < cols) {
          if(grid[startRow][startCol] == 0) startCol++;
          else break;
        }
        while(endCol < cols) {
          if(grid[endRow][endCol] == 0) endCol++;
          else break;
        }
        if(startCol < cols && endCol < cols && grid[startRow][startCol] == 1 && grid[endRow][endCol] == 1) {
          count += (endRow - startRow);
          startCol++;
          endCol++;
        }
        if(startCol == cols) {
          startRow++;
          startCol = 0;
        }
        if(endCol == cols) {
          endRow--;
          endCol = 0;
        }
      }
      return count;
    }

    private static int hDistance(int[][] grid) {
      int rows = grid.length;
      int cols = rows != 0 ? grid[0].length : 0;

      int count = 0;

      int startCol = 0;
      int endCol = cols - 1;
      int startRow = 0;
      int endRow = 0;
      while(startCol < endCol) {
        while(startRow < rows) {
          if(grid[startRow][startCol] == 0) startRow++;
          else break;
        }
        while(endRow < rows) {
          if(grid[endRow][endCol] == 0) endRow++;
          else break;
        }
        if(startRow < rows && endRow < rows && grid[startRow][startCol] == 1 && grid[endRow][endCol] == 1) {
          count += (endCol - startCol);
          startRow++;
          endRow++;
        }
        if(startRow == rows) {
          startCol++;
          startRow = 0;
        }
        if(endRow == rows) {
          endCol--;
          endRow = 0;
        }
      }
      return count;
    }
  }

  public static class Solution5 implements Solution {
    @Override
    public int minTotalDistance(int[][] grid) {
      return distance(grid, true) + distance(grid, false);
    }

    private static int distance(int[][] grid, boolean vertical) {
      int rows = grid.length;
      int cols = rows != 0 ? grid[0].length : 0;

      int majorMax = vertical ? rows : cols;
      int minorMax = vertical ? cols : rows;

      int count = 0;

      int startMajor = 0;
      int endMajor = majorMax - 1;
      int startMinor = 0;
      int endMinor = 0;
      while(startMajor < endMajor) {
        while(startMinor < minorMax) {
          if(grid[vertical ? startMajor : startMinor][vertical ? startMinor : startMajor] == 0) startMinor++;
          else break;
        }
        while(endMinor < minorMax) {
          if(grid[vertical ? endMajor : endMinor][vertical ? endMinor : endMajor] == 0) endMinor++;
          else break;
        }
        if(startMinor < minorMax && endMinor < minorMax
             && grid[vertical ? startMajor : startMinor][vertical ? startMinor : startMajor] == 1
             && grid[vertical ? endMajor : endMinor][vertical ? endMinor : endMajor] == 1) {
          count += (endMajor - startMajor);
          startMinor++;
          endMinor++;
        }
        if(startMinor == minorMax) {
          startMajor++;
          startMinor = 0;
        }
        if(endMinor == minorMax) {
          endMajor--;
          endMinor = 0;
        }
      }
      return count;
    }
  }

  public static class Solution6 implements Solution {
    @Override
    public int minTotalDistance(int[][] grid) {
      return distance(grid, true) + distance(grid, false);
    }

    private static int distance(int[][] grid, boolean vertical) {
      int rows = grid.length;
      int cols = rows != 0 ? grid[0].length : 0;

      int majorMax = vertical ? rows : cols;
      int minorMax = vertical ? cols : rows;

      int count = 0;

      int startMajor = 0;
      int endMajor = majorMax - 1;
      int startMinor = 0;
      int endMinor = 0;
      while(startMajor < endMajor) {
        while(startMajor < endMajor && grid[vertical ? startMajor : startMinor][vertical ? startMinor : startMajor] == 0) {
          if(++startMinor == minorMax) {
            startMajor++; startMinor = 0;
          }
        }
        while(startMajor < endMajor && grid[vertical ? endMajor : endMinor][vertical ? endMinor : endMajor] == 0) {
          if(++endMinor == minorMax) {
            endMajor--; endMinor = 0;
          }
        }
        count += (endMajor - startMajor);
        if(++startMinor == minorMax) {
          startMajor++; startMinor = 0;
        }
        if(++endMinor == minorMax) {
          endMajor--; endMinor = 0;
        }
      }
      return count;
    }
  }

  public static void main(String args[]) {
    int[][] grid;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3(), new Solution4(), new Solution5(), new Solution6())) {
      grid = new int[][]{
        {1, 0, 0, 0, 1},
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0}
      };
      result = s.minTotalDistance(grid);
      System.out.println(result);

      grid = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 1, 0},
        {1, 1, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0}
      };
      result = s.minTotalDistance(grid);
      System.out.println(result);

    }

  }

}
