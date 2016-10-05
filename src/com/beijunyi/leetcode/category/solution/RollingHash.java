package com.beijunyi.leetcode.category.solution;

/**
 * Rolling hash at of window size K for string between i-K..i is:
 *
 *   new hash = (previous hash - (alphabet size ^ K-1) * str.charAt(i-K)) * alphabet size + str.charAt(i)
 *
 */
public interface RollingHash {
}
