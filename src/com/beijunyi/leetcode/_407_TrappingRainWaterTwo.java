package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute
 * the volume of water it is able to trap after raining.
 * 
 * Note:
 *   Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 * 
 * Example:
 *   Given the following 3x6 height map:
 *     [
 *       [1,4,3,1,3,2],
 *       [3,2,1,3,2,4],
 *       [2,3,3,2,3,1]
 *     ]
 *   Return 4.
 */
public class _407_TrappingRainWaterTwo implements Hard {

  public interface Solution {
    int trapRainWater(int[][] heightMap);
  }

  public static class Solution1 implements Solution {

    private static final int[][] NEIGHBOURS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int[][] tiles;
    private int rows;
    private int cols;

    @Override
    public int trapRainWater(int[][] heightMap) {
      tiles = heightMap;
      rows = tiles.length;
      cols = rows == 0 ? 0 : tiles[0].length;
      if(rows < 3 || cols < 3) return 0;

      int count = 0;
      PriorityQueue<Tile> heap = new PriorityQueue<>((t1, t2) -> t1.height - t2.height);
      addBoundaryTiles(heap);
      while(!heap.isEmpty()) {
        Tile shortest = heap.poll();
        count += addNeighboursAndCollectWater(shortest, heap);
      }
      return count;
    }

    private void addBoundaryTiles(PriorityQueue<Tile> heap) {
      for(int r = 0; r < rows; r++) {
        addTile(r, 0, heap);
        addTile(r, cols - 1, heap);
      }
      for(int c = 1; c < cols - 1; c++) {
        addTile(0, c, heap);
        addTile(rows - 1, c, heap);
      }
    }

    private Tile addTile(int r, int c, PriorityQueue<Tile> heap) {
      Tile ret = new Tile(r, c, tiles[r][c]);
      heap.offer(ret);
      tiles[r][c] = -1;
      return ret;
    }

    private int addNeighboursAndCollectWater(Tile shortest, PriorityQueue<Tile> heap) {
      int count = 0;
      for(int[] neighbour : NEIGHBOURS) {
        int r = neighbour[0] + shortest.r;
        int c = neighbour[1] + shortest.c;
        if(r < 0 || c < 0 || r == rows || c == cols) continue;
        int height = tiles[r][c];
        if(height == -1) continue;
        Tile tile = addTile(r, c, heap);
        if(height < shortest.height) {
          count += shortest.height - height;
          tile.height = shortest.height; // important
        }
      }
      return count;
    }

    private static class Tile {
      final int r;
      final int c;
      int height;

      public Tile(int r, int c, int height) {
        this.r = r;
        this.c = c;
        this.height = height;
      }
    }
  }

  public static void main(String args[]) {
    int[][] heightMap;
    int result;
    
    for(Solution s : Arrays.asList(new Solution1())) {
      heightMap = new int[][]{
        {1, 4, 3, 1, 3, 2},
        {3, 2, 1, 3, 2, 4},
        {2, 3, 3, 2, 3, 1}
      };
      result = s.trapRainWater(heightMap);
      System.out.println(result);
    }
  }

}
