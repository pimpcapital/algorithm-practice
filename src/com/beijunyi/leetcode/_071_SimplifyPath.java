package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 *  path = "/home/", => "/home"
 *  path = "/a/./b/../../c/", => "/c"
 *
 * Corner Cases:
 *  Did you consider the case where path = "/../"?
 *  In this case, you should return "/".
 *
 *  Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 *  In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class _071_SimplifyPath implements Medium {

  public interface Solution {
    String simplifyPath(String path);
  }

  public static class Solution1 implements Solution {

    @Override
    public String simplifyPath(String path) {
      path += "/";
      Deque<String> stack = new LinkedList<>();
      int lastSlash = 0;
      for(int i = 1; i < path.length(); i++) {
        if(path.charAt(i) == '/') {
          append(stack, path, lastSlash + 1, i);
          lastSlash = i;
        }
      }
      return join(stack);
    }

    private static void append(Deque<String> stack, String path, int start, int end) {
      int length = end - start;
      if(length == 0 || length == 1 && path.charAt(start) == '.') return;
      if(length == 2 && path.charAt(start) == '.' && path.charAt(start + 1) == '.') {
        stack.pollLast();
      } else {
        stack.offerLast(path.substring(start, end));
      }
    }

    private static String join(Collection<String> names) {
      if(names.isEmpty()) return "/";
      StringBuilder sb = new StringBuilder();
      for(String name : names) {
        sb.append("/").append(name);
      }
      return sb.toString();
    }

  }

  public static void main(String args[]) {
    String path;
    String result;

    for(Solution s : Arrays.asList(new Solution1())) {
      path = "/home/";
      result = s.simplifyPath(path);
      System.out.println(result);

      path = "/a/./b/../../c/";
      result = s.simplifyPath(path);
      System.out.println(result);

      path = "/../";
      result = s.simplifyPath(path);
      System.out.println(result);

      path = "/home//foo/";
      result = s.simplifyPath(path);
      System.out.println(result);

      path = "/...";
      result = s.simplifyPath(path);
      System.out.println(result);
    }
  }



}
