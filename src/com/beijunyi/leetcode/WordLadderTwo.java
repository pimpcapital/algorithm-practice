package com.beijunyi.leetcode;

import java.util.*;

/**
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
 *
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * Note:
 *   All words have the same length.
 *   All words contain only lowercase alphabetic characters.
 */
public class WordLadderTwo implements Hard {

  public static class Solution2 {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {

      Queue<String> nodeQueue = new LinkedList<>();
      nodeQueue.add(start);
      nodeQueue.add(null);
      int depth = 1;

      Map<String, Set<String>> parents = new HashMap<>();
      Map<String, Integer> depths = new HashMap<>();
      Set<String> nextToEnds = new HashSet<>();

      while(!nodeQueue.isEmpty()) {
        String node = nodeQueue.remove();
        if(node == null) {
          if(nodeQueue.isEmpty())
            break;
          if(!nextToEnds.isEmpty())
            break;
          depth++;
          nodeQueue.add(null);
          continue;
        }
        for(int i = 0; i < node.length(); i++) {
          StringBuilder builder = new StringBuilder(node);
          for(char j = 'a'; j <= 'z'; j++) {
            builder.setCharAt(i, j);
            String mutation = builder.toString();
            if(mutation.equals(node))
              continue;

            if(mutation.equals(end)) {
              nextToEnds.add(node);
              continue;
            }

            if(!dict.contains(mutation))
              continue;

            if(depths.get(mutation) != null) {
              if(depths.get(mutation) < depth)
                continue;
            } else
              depths.put(mutation, depth);

            Set<String> mutationParents = parents.get(mutation);
            if(mutationParents == null) {
              mutationParents = new HashSet<>();
              parents.put(mutation, mutationParents);
            }
            mutationParents.add(node);

            nodeQueue.add(mutation);
          }
        }
      }

      parents.put(end, nextToEnds);

      return traceBack(end, start, parents);
    }

    private List<List<String>> traceBack(String start, String end, Map<String, Set<String>> links) {
      List<List<String>> results = new ArrayList<>();
      if(start.equals(end)) {
        List<String> result = new ArrayList<>();
        result.add(end);
        results.add(result);
      } else {
        Set<String> parents = links.get(start);
        for(String parent : parents) {
          List<List<String>> subTraces = traceBack(parent, end, links);
          for(List<String> subTrace : subTraces) {
            List<String> result = new ArrayList<>();
            result.addAll(subTrace);
            result.add(start);
            results.add(result);
          }
        }
      }
      return results;
    }
  }

  public static class Solution1 {
    Map<String, List<String>> map;
    List<List<String>> results;

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
      results = new ArrayList<>();
      if(dict.size() == 0)
        return results;

      int min = Integer.MAX_VALUE;

      Queue<String> queue = new ArrayDeque<>();
      queue.add(start);

      map = new HashMap<>();

      Map<String, Integer> ladder = new HashMap<>();
      for(String string : dict)
        ladder.put(string, Integer.MAX_VALUE);
      ladder.put(start, 0);

      dict.add(end);
      //BFS: Dijisktra search
      while(!queue.isEmpty()) {

        String word = queue.poll();

        int step = ladder.get(word) + 1;//'step' indicates how many steps are needed to travel to one word.

        if(step > min) break;

        for(int i = 0; i < word.length(); i++) {
          StringBuilder builder = new StringBuilder(word);
          for(char ch = 'a'; ch <= 'z'; ch++) {
            builder.setCharAt(i, ch);
            String newWord = builder.toString();
            if(ladder.containsKey(newWord)) {

              if(step > ladder.get(newWord)) //Check if it is the shortest path to one word.
                continue;
              else if(step < ladder.get(newWord)) {
                queue.add(newWord);
                ladder.put(newWord, step);
              }
              // It is a KEY line. If one word already appeared in one ladder,
              // Do not insert the same word inside the queue twice. Otherwise it gets TLE.

              if(map.containsKey(newWord)) //Build adjacent Graph
                map.get(newWord).add(word);
              else {
                List<String> list = new LinkedList<>();
                list.add(word);
                map.put(newWord, list);
                //It is possible to write three lines in one:
                //map.put(new_word,new LinkedList<String>(Arrays.asList(new String[]{word})));
                //Which one is better?
              }

              if(newWord.equals(end))
                min = step;

            }//End if dict contains new_word
          }//End:Iteration from 'a' to 'z'
        }//End:Iteration from the first to the last
      }//End While

      //BackTracking
      LinkedList<String> result = new LinkedList<>();
      backTrace(end, start, result);

      return results;
    }

    private void backTrace(String word, String start, List<String> list) {
      if(word.equals(start)) {
        list.add(0, start);
        results.add(new ArrayList<>(list));
        list.remove(0);
        return;
      }
      list.add(0, word);
      if(map.get(word) != null)
        for(String s : map.get(word))
          backTrace(s, start, list);
      list.remove(0);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution1().findLadders("hit", "cog", new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    System.out.println(new Solution2().findLadders("hit", "cog", new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
  }

}
