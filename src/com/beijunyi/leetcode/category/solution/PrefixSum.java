package com.beijunyi.leetcode.category.solution;

/**
 * Prefix sum in 1D:
 *   sums[i] = nums[i] + sums[i - 1]
 *
 * Prefix sum in 2D:
 *   sums[r][c] = nums[r][c] + nums[r-1][c] + nums[r][c-1] - nums[r-1][c-1]
 */
public interface PrefixSum {
}
