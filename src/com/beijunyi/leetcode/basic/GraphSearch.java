package com.beijunyi.leetcode.basic;

import java.util.*;

import com.beijunyi.leetcode.ds.UndirectedGraphNode;

public class GraphSearch {

  public static boolean bfs(UndirectedGraphNode node, int target) {
    Queue<UndirectedGraphNode> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    q.add(node);
    visited.add(node.label);
    while(!q.isEmpty()) {
      UndirectedGraphNode current = q.poll();
      if(current.label == target)
        return true;
      for(UndirectedGraphNode neighbor : current.neighbors) {
        if(visited.contains(neighbor.label))
          continue;
        q.add(neighbor);
        visited.add(neighbor.label);
      }
    }
    return false;
  }

  public static boolean dfs(UndirectedGraphNode node, int target) {
    return dfs(node, target, new HashSet<Integer>());
  }

  private static boolean dfs(UndirectedGraphNode node, int target, Set<Integer> visited) {
    if(node.label == target)
      return true;
    visited.add(node.label);
    for(UndirectedGraphNode neighbor : node.neighbors) {
      if(visited.contains(neighbor.label))
        continue;
      if(dfs(neighbor, target, visited))
        return true;
    }
    return false;
  }

}
