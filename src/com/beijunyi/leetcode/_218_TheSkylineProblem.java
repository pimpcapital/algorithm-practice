package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.WithHeap;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a
 * distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B)
 *
 * <img src="./_218_TheSkylineProblem_1.jpg"/>
 * <img src="./_218_TheSkylineProblem_2.jpg"/>
 *
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are
 * the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect
 * rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12],
 * [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has
 * zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8],
 * [24, 0] ].
 *
 * Notes:
 *  * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 *  * The input list is already sorted in ascending order by the left x position Li.
 *  * The output list must be sorted by the x position.
 *  * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3],
 *  * [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the
 *    final output as such: [...[2 3], [4 5], [12 7], ...]
 * .
 */
public class _218_TheSkylineProblem implements Hard {

  public interface Solution {
    List<int[]> getSkyline(int[][] buildings);
  }

  public static class Solution1 implements Solution, WithHeap {

    @Override
    public List<int[]> getSkyline(int[][] buildings) {
      Queue<BuildingEvent> events = prepareBuildingEvents(buildings);
      PriorityQueue<Integer> tallest = new PriorityQueue<>(Collections.reverseOrder());

      List<int[]> ret = new ArrayList<>();
      int x = 0;
      int y = 0;
      while(!events.isEmpty()) {
        x = events.peek().pos;
        while(!events.isEmpty() && x == events.peek().pos) { // burst events
          BuildingEvent e = events.poll();
          if(e.insert) tallest.offer(e.height);
          else tallest.remove(e.height);
        }
        int newY = tallest.isEmpty() ? 0 : tallest.peek();
        if(newY != y) {
          ret.add(new int[] {x, newY});
          y = newY;
        }
      }
      return ret;
    }

    private static Queue<BuildingEvent> prepareBuildingEvents(int[][] buildings) {
      LinkedList<BuildingEvent> ret = new LinkedList<>();
      for(int[] building : buildings) {
        ret.add(new BuildingEvent(building[0], true, building[2])); // start
        ret.add(new BuildingEvent(building[1], false, building[2])); // end
      }
      Collections.sort(ret, (e1, e2) -> Integer.compare(e1.pos, e2.pos));
      return ret;
    }

    private static class BuildingEvent {
      private final int pos;
      private final boolean insert;
      private final int height;

      public BuildingEvent(int pos, boolean insert, int height) {
        this.pos = pos;
        this.insert = insert;
        this.height = height;
      }
    }

  }

  public static void main(String args[]) {
    int[][] buildings;
    List<int[]> result;

    for(Solution s : Arrays.asList(new Solution1())) {
      buildings = new int[][] {
        {0, 2, 3}, {2, 5, 3}
      };
      result = s.getSkyline(buildings);
      for(int[] endpoints : result) {
        System.out.print(Arrays.toString(endpoints));
      }
      System.out.println();

      buildings = new int[][] {
        {1, 2, 1}, {1, 2, 2}, {1, 2, 3}
      };
      result = s.getSkyline(buildings);
      for(int[] endpoints : result) {
        System.out.print(Arrays.toString(endpoints));
      }
      System.out.println();

      buildings = new int[][] {
        {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}
      };
      result = s.getSkyline(buildings);
      for(int[] endpoints : result) {
        System.out.print(Arrays.toString(endpoints));
      }
      System.out.println();
    }
  }

}
