package com.beijunyi.leetcode.category.solution;

/**
 * Tips:
 *   1) Two typical ways to solve topo sorting problems:
 *     a) Kahn's Algorithm (BFS, dynamically populate the queue by nodes whose inbound = 0)
 *     b) Depth first search (Keep state of each node e.g. 0: fresh, 1: visiting, 2: visited)
 */
public interface TopologicalSorting {
}
