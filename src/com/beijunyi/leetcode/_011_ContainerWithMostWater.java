package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container.
 */
public class _011_ContainerWithMostWater implements Medium {

  public interface Solution {
    int maxArea(int[] height);
  }

  public static class Solution1 implements Solution {
    @Override
    public int maxArea(int[] heights) {
      if(heights.length == 0) return 0;

      int l = 0;
      int r = heights.length - 1;
      int lHeight, rHeight;
      int ret = 0;

      do {
        lHeight = heights[l];
        rHeight = heights[r];

        int height = Math.min(lHeight, rHeight);
        ret = Math.max(ret, height * (r - l));

        if(lHeight <= rHeight)
          do ++l; while(l < r && heights[l] < lHeight);
        else
          do --r; while(l < r && heights[r] < rHeight);

      } while(l <= r);

      return ret;
    }
  }

  public static class Solution2 implements Solution {
    @Override
    public int maxArea(int[] height) {
      int left = 0;
      int right = height.length - 1;
      int max = 0, area;
      while(left < right) {
        int l = height[left];
        int r = height[right];
        if(l > r) {
          area = (right - left) * r;
          do --right; while(height[right] <= r);
        } else {
          area = (right - left) * l;
          do ++left; while(height[left] < l) ;
        }
        if(area > max) max = area;
      }
      return max;
    }
  }

  public static void main(String args[]) {
    int[] heights;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      heights = new int[] {4, 1, 2, 3, 4, 5};
      result = s.maxArea(heights);
      System.out.println(result);


      heights = new int[15000];
      for(int i = 0; i < 15000; i++) {
        heights[i] = 15000 - i;
      }
      result = s.maxArea(heights);
      System.out.println(result);
    }
  }

}
