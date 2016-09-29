package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.Backtracking;
import com.beijunyi.leetcode.category.solution.GraphTheory;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 * itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when
 * read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 *
 * You may assume all tickets form at least one valid itinerary.
 *
 * Example 1:
 *  tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *  Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *
 * Example 2:
 *  tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *  Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 *  Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */
public class _332_ReconstructItinerary implements Medium {

  public interface Solution {
    List<String> findItinerary(String[][] tickets);
  }

  public static class Solution1 implements Solution, Backtracking {
    @Override
    public List<String> findItinerary(String[][] tickets) {
      Map<String, LinkedList<String>> lookup = indexTickets(tickets);
      LinkedList<String> result = new LinkedList<>();
      travel("JFK", lookup, result, tickets.length);
      result.addFirst("JFK");
      return result;
    }

    private static Map<String, LinkedList<String>> indexTickets(String[][] tickets) {
      Map<String, LinkedList<String>> ret = new HashMap<>();
      for(String[] ticket : tickets) ret.computeIfAbsent(ticket[0], (from) -> new LinkedList<>()).add(ticket[1]);
      ret.values().forEach(Collections::sort);
      return ret;
    }

    private static boolean travel(String from, Map<String, LinkedList<String>> lookup, Deque<String> result, int remained) {
      if(remained == 0) return true;
      LinkedList<String> candidates = lookup.get(from);
      if(candidates == null || candidates.isEmpty()) return false;
      for(int i = 0; i < candidates.size(); i++) {
        String to = candidates.removeFirst();
        if(travel(to, lookup, result, remained - 1)) {
          result.addFirst(to);
          return true;
        }
        candidates.addLast(to);
      }
      return false;
    }

  }

  /**
   * Time: O(n)
   * Space: O(n)
   */
  public static class Solution2 implements Solution, GraphTheory {

    private Map<String, PriorityQueue<String>> targets;
    private List<String> route;

    @Override
    public List<String> findItinerary(String[][] tickets) {
      targets = new HashMap<>();
      route = new LinkedList<>();
      for (String[] ticket : tickets)
        targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
      visit("JFK");
      return route;
    }

    private void visit(String airport) {
      while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
        visit(targets.get(airport).poll());
      route.add(0, airport);
    }
  }

  public static void main(String args[]) {
    String[][] tickets;
    List<String> result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      tickets = new String[][] {
        {"JFK","SFO"},
        {"JFK","ATL"},
        {"SFO","ATL"},
        {"ATL","JFK"},
        {"ATL","SFO"}
      };
      result = s.findItinerary(tickets);
      System.out.println(result);
      
      tickets = new String[][] {
        {"AXA","EZE"},{"EZE","AUA"},{"ADL","JFK"},{"ADL","TIA"},{"AUA","AXA"},{"EZE","TIA"},{"EZE","TIA"},{"AXA","EZE"},{"EZE","ADL"},{"ANU","EZE"},{"TIA","EZE"},{"JFK","ADL"},{"AUA","JFK"},{"JFK","EZE"},{"EZE","ANU"},{"ADL","AUA"},{"ANU","AXA"},{"AXA","ADL"},{"AUA","JFK"},{"EZE","ADL"},{"ANU","TIA"},{"AUA","JFK"},{"TIA","JFK"},{"EZE","AUA"},{"AXA","EZE"},{"AUA","ANU"},{"ADL","AXA"},{"EZE","ADL"},{"AUA","ANU"},{"AXA","EZE"},{"TIA","AUA"},{"AXA","EZE"},{"AUA","SYD"},{"ADL","JFK"},{"EZE","AUA"},{"ADL","ANU"},{"AUA","TIA"},{"ADL","EZE"},{"TIA","JFK"},{"AXA","ANU"},{"JFK","AXA"},{"JFK","ADL"},{"ADL","EZE"},{"AXA","TIA"},{"JFK","AUA"},{"ADL","EZE"},{"JFK","ADL"},{"ADL","AXA"},{"TIA","AUA"},{"AXA","JFK"},{"ADL","AUA"},{"TIA","JFK"},{"JFK","ADL"},{"JFK","ADL"},{"ANU","AXA"},{"TIA","AXA"},{"EZE","JFK"},{"EZE","AXA"},{"ADL","TIA"},{"JFK","AUA"},{"TIA","EZE"},{"EZE","ADL"},{"JFK","ANU"},{"TIA","AUA"},{"EZE","ADL"},{"ADL","JFK"},{"ANU","AXA"},{"AUA","AXA"},{"ANU","EZE"},{"ADL","AXA"},{"ANU","AXA"},{"TIA","ADL"},{"JFK","ADL"},{"JFK","TIA"},{"AUA","ADL"},{"AUA","TIA"},{"TIA","JFK"},{"EZE","JFK"},{"AUA","ADL"},{"ADL","AUA"},{"EZE","ANU"},{"ADL","ANU"},{"AUA","AXA"},{"AXA","TIA"},{"AXA","TIA"},{"ADL","AXA"},{"EZE","AXA"},{"AXA","JFK"},{"JFK","AUA"},{"ANU","ADL"},{"AXA","TIA"},{"ANU","AUA"},{"JFK","EZE"},{"AXA","ADL"},{"TIA","EZE"},{"JFK","AXA"},{"AXA","ADL"},{"EZE","AUA"},{"AXA","ANU"},{"ADL","EZE"},{"AUA","EZE"}};
      result = s.findItinerary(tickets);
      System.out.println(result);
    }
  }

}
