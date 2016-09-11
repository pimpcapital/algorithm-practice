package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.difficulty.NotHardButTricky;
import com.beijunyi.leetcode.category.solution.BinarySearch;
import com.beijunyi.leetcode.category.solution.MedianOfMedians;
import com.beijunyi.leetcode.category.solution.WithHeap;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element
 * in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 *   matrix = [
 *     [ 1,  5,  9],
 *     [10, 11, 13],
 *     [12, 13, 15]
 *     ],
 *   k = 8,
 *   return 13.
 *
 * Note:
 *   You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class _378_KthSmallestElementInASortedMatrix implements Medium, NotHardButTricky {

  public interface Solution {
    int kthSmallest(int[][] matrix, int k);
  }

  public static class Solution1 implements Solution, WithHeap {

    private int[][] matrix;
    private int rows;
    private int cols;
    private boolean[][] processed;

    @Override
    public int kthSmallest(int[][] matrix, int k) {
      this.matrix = matrix;
      rows = matrix.length;
      cols = rows == 0 ? 0 : matrix[0].length;
      if(rows == 0 || cols == 0) return -1; // error
      processed = new boolean[rows][cols];

      PriorityQueue<Cell> heap = new PriorityQueue<>();
      Cell start = new Cell(matrix[0][0], 0, 0);
      heap.offer(start);
      while(true) {
        Cell next = heap.poll();
        if(--k == 0) return next.value;
        addNeighbours(next, heap);
      }
    }

    private void addNeighbours(Cell cell, PriorityQueue<Cell> heap) {
      if(cell.r < rows - 1 && !processed[cell.r + 1][cell.c]) {
        processed[cell.r + 1][cell.c] = true;
        heap.offer(new Cell(matrix[cell.r + 1][cell.c], cell.r + 1, cell.c));
      }
      if(cell.c < cols - 1 && !processed[cell.r][cell.c + 1]) {
        processed[cell.r][cell.c + 1] = true;
        heap.offer(new Cell(matrix[cell.r][cell.c + 1], cell.r, cell.c + 1));
      }
    }

    private static class Cell implements Comparable<Cell> {
      private final int value;
      private final int r;
      private final int c;

      public Cell(int value, int r, int c) {
        this.value = value;
        this.r = r;
        this.c = c;
      }

      @Override
      public int compareTo(Cell o) {
        return Integer.compare(value, o.value);
      }
    }

  }

  /**
   * Much cleaner solution. Initializing the queue with left most column
   */
  public static class Solution2 implements Solution, WithHeap {

    @Override
    public int kthSmallest(int[][] matrix, int k) {
      int n = matrix.length;
      PriorityQueue<Tuple> pq = new PriorityQueue<>();
      for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
      for(int i = 0; i < k-1; i++) {
        Tuple t = pq.poll();
        if(t.x == n-1) continue;
        pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
      }
      return pq.poll().val;
    }
  }

  private static class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
      this.x = x;
      this.y = y;
      this.val = val;
    }

    @Override
    public int compareTo (Tuple that) {
      return this.val - that.val;
    }
  }

  public static class Solution3 implements Solution, BinarySearch, MedianOfMedians {
    @Override
    public int kthSmallest(int[][] matrix, int k) {
      int n = matrix.length;
      int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
      while(lo < hi) {
        int count = 0, mid = (lo + hi) / 2, nextBiggerMid = Integer.MAX_VALUE;
        for(int[] row : matrix) {
          int j = 0;
          while(j < n) {
            if(row[j] > mid) {
              nextBiggerMid = (nextBiggerMid < row[j]) ? nextBiggerMid : row[j];
              break;
            }
            j++;
          }
          if(j == 0) {
            break;
          }
          count += j;
        }
        if(count < k) {
          lo = nextBiggerMid;
        }
        else {
          hi = mid;
        }
      }
      return lo;
    }
  }

  public static class Solution4 implements Solution, BinarySearch, MedianOfMedians {
    @Override
    public int kthSmallest(int[][] matrix, int k) {
      int n = matrix.length;
      int low = matrix[0][0], high = matrix[n-1][n-1];
      while(low < high) {
        int mid = (low + high) / 2, count = 0, j = n;
        for(int[] row : matrix) {
          while(j >= 1 && row[j-1] > mid)
            j--;
          count += j;
        }
        if(count < k)
          low = mid + 1;
        else
          high = mid;
      }
      return low;
    }
  }

  public static void main(String args[]) {
    int[][] matrix;
    int k;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3(), new Solution4())) {
      matrix = new int[][] {
        { 1,  5,  9},
        {10, 11, 13},
        {12, 13, 15}
      };
      k = 8;
      System.out.println(s.kthSmallest(matrix, k));

      matrix = new int[][] {
        { 1,  3,  5},
        { 6,  7, 12},
        {11, 14, 14}
      };
      k = 6;
      System.out.println(s.kthSmallest(matrix, k));
    }
  }

}
