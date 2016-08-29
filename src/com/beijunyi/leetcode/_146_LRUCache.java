package com.beijunyi.leetcode;

import java.util.*;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and set.
 *
 * get(key):
 *   Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value):
 *   Set or insert the value if the key is not already present. When the cache reached its capacity, it should
 *   invalidate the least recently used item before inserting a new item.
 */
public class _146_LRUCache {

  public interface Solution {

    void init(int capacity);

    int get(int key);

    void set(int key, int value);
  }

  public static class Solution1 implements Solution {

    private Map<Integer, Integer> map;
    private int[][] nodes; // [prev, next, key, value]
    private int count;
    private int head;
    private int tail;

    @Override
    public void init(int capacity) {
      map = new HashMap<>(capacity);
      nodes = new int[capacity][4];
      count = 0;
      head = -1;
      tail = -1;
    }

    @Override
    public int get(int key) {
      if(!map.containsKey(key)) return -1;
      int nodeIndex = map.get(key);
      int[] node = nodes[nodeIndex];
      int value = node[3];
      updateTail(key, value);
      return value;
    }

    @Override
    public void set(int key, int value) {
      if(head == -1) {
        head = 0;
        tail = 0;
        nodes[0] = new int[] {-1, -1, key, value};
        count++;
      } else {
        updateTail(key, value);
      }
      map.put(key, tail);
    }

    private void updateTail(int key, int value) {
      int pos;
      if(map.containsKey(key)) {
        pos = map.get(key);
        int[] node = nodes[pos];
        if(node[0] != -1) nodes[node[0]][1] = node[1]; // link prev's next
        if(node[1] != -1) nodes[node[1]][0] = node[0]; // link next's prev
        if(head == pos) head = node[1]; // fix head
        if(tail == pos) tail = node[0]; // fix tail
      } else if(count < nodes.length) {
        pos = count;
        count++;
      } else {
        int[] headNode = nodes[head];
        if(headNode[1] != -1) nodes[headNode[1]][0] = -1;
        pos = head;
        head = headNode[1];
        map.remove(headNode[2]);
      }
      nodes[pos] = new int[] {tail, -1, key, value};
      if(tail != -1) nodes[tail][1] = pos;
      tail = pos;
      if(head == -1) head = tail;
    }
  }

  public static class Solution2 implements Solution {

    private Map<Integer, Integer> map;

    @Override
    public void init(int capacity) {
      map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
          return size() > capacity;
        }
      };
    }

    @Override
    public int get(int key) {
      return map.getOrDefault(key, -1);
    }

    @Override
    public void set(int key, int value) {
      map.put(key,value);
    }

  }

  public static class Solution3 implements Solution {

    private LinkedHashMap<Integer,Integer> map;
    private int capacity;

    public void init(int capacity) {
      this.capacity = capacity;
      map = new LinkedHashMap<>(capacity + 1);
    }

    public int get(int key) {
      Integer val = map.get(key);
      if(val == null) {
        return -1;
      } else {
        map.remove(key); // reorder
        map.put(key, val);
        return val;
      }
    }

    public void set(int key, int value) {
      map.remove(key); // reorder
      map.put(key, value);
      if(map.size() > capacity) {
        map.remove(map.entrySet().iterator().next().getKey());
      }
    }
  }

  public static class Solution4 implements Solution {

    private static class Node {
      int key;
      int value;
      Node pre;
      Node next;

      private Node(int key, int value) {
        this.key = key;
        this.value = value;
      }
    }

    HashMap<Integer, Node> map;
    int capicity, count;
    Node head, tail;

    @Override
    public void init(int capacity) {
      this.capicity = capacity;
      map = new HashMap<>();
      head = new Node(0, 0); // dummy head node
      tail = new Node(0, 0); // dummy tail node
      head.next = tail;
      tail.pre = head;
      head.pre = null;
      tail.next = null;
      count = 0;
    }

    private void deleteNode(Node node) {
      node.pre.next = node.next;
      node.next.pre = node.pre;
    }

    private void addToHead(Node node) {
      node.next = head.next;
      node.next.pre = node;
      node.pre = head;
      head.next = node;
    }

    @Override
    public int get(int key) {
      if (map.get(key) != null) {
        Node node = map.get(key);
        int result = node.value;
        deleteNode(node);
        addToHead(node);
        return result;
      }
      return -1;
    }

    @Override
    public void set(int key, int value) {
      if (map.get(key) != null) {
        Node node = map.get(key);
        node.value = value;
        deleteNode(node);
        addToHead(node);
      } else {
        Node node = new Node(key, value);
        map.put(key, node);
        if (count < capicity) {
          count++;
          addToHead(node);
        } else {
          map.remove(tail.pre.key);
          deleteNode(tail.pre);
          addToHead(node);
        }
      }
    }
  }

  public static void main(String args[]) {

    for(Solution s : Arrays.asList(new Solution1(), new Solution2(), new Solution3(), new Solution4())) {
      s.init(2);
      s.set(1, 1);
      s.set(100, 100);
      s.set(1000, 1000);
      System.out.println(s.get(1));
      System.out.println(s.get(100));
      System.out.println(s.get(1000));
      System.out.println();

      s.init(2);
      s.set(1, 1);
      s.set(100, 100);
      System.out.println(s.get(1));
      s.set(1000, 1000);
      System.out.println(s.get(100));
      System.out.println(s.get(1000));
      System.out.println();

      s.init(1);
      s.set(2, 1);
      System.out.println(s.get(2));
      s.set(3, 2);
      System.out.println(s.get(2));
      System.out.println(s.get(3));
      System.out.println();

      s.init(10);
      s.set(10, 13);
      s.set(3, 17);
      s.set(6, 11);
      s.set(10, 5);
      s.set(9, 10);
      System.out.println(s.get(13));
      s.set(2, 19);
      System.out.println(s.get(2));
      System.out.println(s.get(3));
      System.out.println();

      System.out.println("------------------------------" + s.getClass().getSimpleName());
    }

  }

}
