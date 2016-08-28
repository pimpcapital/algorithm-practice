package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.Stack;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * Suppose we abstract our file system by a string in the following manner:
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 *   dir
 *     subdir1
 *     subdir2
 *       file.ext
 *
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 *   dir
 *     subdir1
 *       file1.ext
 *       subsubdir1
 *     subdir2
 *       subsubdir2
 *         file2.ext
 *
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty
 * second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file
 * file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For
 * example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length
 * is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format, return the length of the longest absolute path to
 * file in the abstracted file system. If there is no file in the system, return 0.
 *
 * Note:
 *   The name of a file contains at least a . and an extension.
 *   The name of a directory or sub-directory will not contain a ..
 *   Time complexity required: O(n) where n is the size of the input string.
 *
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
public class _388_LongestAbsoluteFilePath implements Medium {

  public interface Solution {
    int lengthLongestPath(String input);
  }

  public static class Solution1 implements Solution {
    @Override
    public int lengthLongestPath(String input) {
      Stack<Integer> dirLengths = new Stack<>();
      String[] names = input.split("\\n");
      int max = 0;
      for(String name : names) {
        int depth = getFileDepth(name);
        int length = name.length() - depth;
        while(depth < dirLengths.size()) dirLengths.pop();
        int total = dirLengths.size() > 0 ? dirLengths.peek() + length + 1 : length;
        if(isFile(name, depth)) max = Math.max(max, total);
        else dirLengths.push(total);
      }
      return max;
    }

    private static int getFileDepth(String name) {
      int count = 0;
      while(count < name.length()) {
        if(name.charAt(count) == '\t') count++;
        else break;
      }
      return count;
    }

    private static boolean isFile(String name, int depth) {
      return name.indexOf(".", depth) >= 0;
    }

  }

  public static class Solution2 implements Solution {

    @Override
    public int lengthLongestPath(String input) {
      String[] dirs = input.split("\n");
      int[] stack = new int[dirs.length + 1];
      int maxLen = 0;
      for(String s : dirs) {
        int lev = s.lastIndexOf("\t") + 1;
        stack[lev + 1] = stack[lev] + s.length() - lev + 1;
        if(s.contains(".")) maxLen = Math.max(maxLen, stack[lev + 1] - 1);
      }
      return maxLen;
    }
  }

  public static void main(String args[]) {
    String input;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      input = "a\n\tb.txt\n\tcd.txt";
      result = s.lengthLongestPath(input);
      System.out.println(result);

      input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
      result = s.lengthLongestPath(input);
      System.out.println(result);
    }
  }

}

