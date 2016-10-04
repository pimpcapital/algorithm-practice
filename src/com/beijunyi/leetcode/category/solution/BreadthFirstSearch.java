package com.beijunyi.leetcode.category.solution;

/**
 * Tips:
 *   1) if depth information is needed. Use 2 loops to do the search:
 *     while(!q.isEmpty()) {
 *       int count = q.size(); // the number of elements at this depth
 *       for(int i = 0; i < count; i++) {...}
 *     }
 *     _102_BinaryTreeLevelOrderTraversal
 *
 *   2) If path is required, use a hash map to record the parent of each node
 *     _126_WordLadderTwo
 */
public interface BreadthFirstSearch extends Iterative {
}
