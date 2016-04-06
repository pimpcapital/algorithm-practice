package com.beijunyi.leetcode;

import java.util.HashMap;
import java.util.Map;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.ds.UndirectedGraphNode;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 *

 */
public class _133_CloneGraph implements Medium {

  public static class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
      return cloneGraph(node, new HashMap<Integer, UndirectedGraphNode>());
    }

    private UndirectedGraphNode cloneGraph(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> clonedNodes) {
      if(node == null)
        return null;
      UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
      clonedNodes.put(node.label, clone);
      for(UndirectedGraphNode n : node.neighbors) {
        UndirectedGraphNode clonedNeighbour = clonedNodes.get(n.label);
        if(clonedNeighbour == null)
          clonedNeighbour = cloneGraph(n, clonedNodes);
        clone.neighbors.add(clonedNeighbour);
      }
      return clone;
    }


  }

  public static void main(String args[]) {
    UndirectedGraphNode n0 = new UndirectedGraphNode(0);
    UndirectedGraphNode n1 = new UndirectedGraphNode(1);
    UndirectedGraphNode n2 = new UndirectedGraphNode(2);
    n0.neighbors.add(n1);
    n0.neighbors.add(n2);
    n1.neighbors.add(n2);
    n2.neighbors.add(n2);
    System.out.println(UndirectedGraphNode.serializeToString(new Solution().cloneGraph(n0)));
  }
}
