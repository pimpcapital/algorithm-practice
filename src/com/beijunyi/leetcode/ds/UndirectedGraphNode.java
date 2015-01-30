package com.beijunyi.leetcode.ds;

import java.util.*;

/**
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 *
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 *
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *
 *     1
 *    / \
 *   /   \
 *  0 --- 2
 *       / \
 *       \_/
 */
public class UndirectedGraphNode {
  public int label;
  public List<UndirectedGraphNode> neighbors;

  public UndirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<>();
  }

  public static String serializeToString(UndirectedGraphNode node) {
    return serializeToString(node, new StringBuilder(), new HashSet<Integer>()).toString();
  }

  private static StringBuilder serializeToString(UndirectedGraphNode node, StringBuilder sb, Set<Integer> serialized) {
    if(node == null)
      return sb;
    serialized.add(node.label);
    sb.append(node.label).append(" ");
    for(UndirectedGraphNode n : node.neighbors)
      sb.append(n.label).append(" ");
    sb.append("# ");
    for(UndirectedGraphNode n : node.neighbors)
      if(!serialized.contains(n.label))
        serializeToString(n, sb, serialized);
    return sb;
  }
}