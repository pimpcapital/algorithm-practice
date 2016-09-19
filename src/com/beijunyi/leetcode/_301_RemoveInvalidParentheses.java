package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.difficulty.ReallyHard;
import com.beijunyi.leetcode.category.solution.BreadthFirstSearch;
import com.beijunyi.leetcode.category.solution.DepthFirstSearch;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Examples:
 *   "()())()" -> ["()()()", "(())()"]
 *   "(a)())()" -> ["(a)()()", "(a())()"]
 *   ")(" -> [""]
 */
public class _301_RemoveInvalidParentheses implements Hard, ReallyHard {

  public interface Solution {
    List<String> removeInvalidParentheses(String s);
  }

  public static class Solution1 implements Solution, DepthFirstSearch {

    @Override
    public List<String> removeInvalidParentheses(String s) {
      List<String> result = new ArrayList<>();
      leftToRight(s, 0,0, result);
      return result;
    }

    private static void leftToRight(String s, int offset, int lastRemove, List<String> result) {
      int balance = 0;
      for(int i = offset; i < s.length(); i++) {
        if(s.charAt(i) == '(') balance++;
        else if(s.charAt(i) == ')') {
          balance--;
          if(balance < 0) {
            for(int j = lastRemove; j <= i; j++)
              if(s.charAt(j) == ')' && (j == lastRemove || s.charAt(j - 1) != ')')) {
                String sub = s.substring(0, j) + s.substring(j + 1);
                leftToRight(sub, i, j, result);
              }
            return;
          }
        }
      }
      if(balance == 0) result.add(s);
      else rightToLeft(s, s.length() - 1, s.length() - 1, result);
    }

    private static void rightToLeft(String s, int offset, int lastRemove, List<String> result) {
      int balance = 0;
      for(int i = offset; i >= 0; i--) {
        if(s.charAt(i) == ')') balance++;
        else if(s.charAt(i) == '(') {
          balance--;
          if(balance < 0) {
            for(int j = lastRemove; j >= i; j--)
              if(s.charAt(j) == '(' && (j == lastRemove || s.charAt(j + 1) != '(')) {
                String sub = s.substring(0, j) + s.substring(j + 1);
                rightToLeft(sub, i - 1, j - 1, result);
              }
            return;
          }
        }
      }
      result.add(s);
    }

  }

  /**
   * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
   * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more
   * ‘)’ than ‘(‘ in the prefix.
   *
   * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix.
   * However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2]
   * but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
   *
   * After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string.
   * However, we need to keep another information: the last removal position. If we do not have this position, we will
   * generate duplicate by removing two ‘)’ in two steps only with a different order.
   * For this, we keep tracking the last removal position and only remove ‘)’ after that.
   *
   * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
   * The answer is: do the same from right to left.
   * However a cleverer idea is: reverse the string and reuse the code!
   * Here is the final implement in Java.
   */
  public static class Solution2 implements Solution, DepthFirstSearch {
    @Override
    public List<String> removeInvalidParentheses(String s) {
      List<String> ans = new ArrayList<>();
      remove(s, ans, 0, 0, new char[]{'(', ')'});
      return ans;
    }

    private static void remove(String s, List<String> ans, int offset, int lastRemove, char[] par) {
      int balance = 0;
      for(int i = offset; i < s.length(); ++i) {
        if(s.charAt(i) == par[0]) balance++;
        else if(s.charAt(i) == par[1]) balance--;

        if(balance >= 0) continue; // it is ok

        for(int j = lastRemove; j <= i; ++j)
          if(s.charAt(j) == par[1] && (j == lastRemove || s.charAt(j - 1) != par[1])) {
            String sub = s.substring(0, j) + s.substring(j + 1);
            remove(sub, ans, i, j, par);
          }
        return;
      }
      String reversed = new StringBuilder(s).reverse().toString();
      if(par[0] == '(') // finished left to right
        remove(reversed, ans, 0, 0, new char[]{')', '('});
      else // finished right to left
        ans.add(reversed);
    }
  }

  public static class Solution3 implements Solution, BreadthFirstSearch {
    @Override
    public List<String> removeInvalidParentheses(String s) {
      List<String> res = new ArrayList<>();

      // sanity check
      if(s == null) return res;

      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();

      // initialize
      queue.add(s);
      visited.add(s);

      boolean found = false;

      while(!queue.isEmpty()) {
        s = queue.poll();

        if(isValid(s)) {
          // found an answer, add to the result
          res.add(s);
          found = true;
        }

        if(found) continue;

        // generate all possible states
        for(int i = 0; i < s.length(); i++) {
          // we only try to remove left or right paren
          if(s.charAt(i) != '(' && s.charAt(i) != ')') continue;

          String t = s.substring(0, i) + s.substring(i + 1);

          if(!visited.contains(t)) {
            // for each state, if it's not visited, add it to the queue
            queue.add(t);
            visited.add(t);
          }
        }
      }

      return res;
    }

    // helper function checks if string s contains valid parantheses
    private static boolean isValid(String s) {
      int count = 0;

      for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if(c == '(') count++;
        if(c == ')' && count-- == 0) return false;
      }

      return count == 0;
    }
  }

  public static void main(String args[]) {
    String s;
    List<String> result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "(r(()()(";
      result = solution.removeInvalidParentheses(s);
      System.out.println(result);

      s = "(()";
      result = solution.removeInvalidParentheses(s);
      System.out.println(result);

      s = "((";
      result = solution.removeInvalidParentheses(s);
      System.out.println(result);

      s = "(a)())()";
      result = solution.removeInvalidParentheses(s);
      System.out.println(result);

      s = "(((k()";
      result = solution.removeInvalidParentheses(s);
      System.out.println(result);
    }
  }

}
