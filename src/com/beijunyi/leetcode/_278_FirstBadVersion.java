package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of
 * your product fails the quality check. Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following
 * ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 */
public class _278_FirstBadVersion implements Easy {

  public interface Solution {
    void init(int firstBadVersion);
    int firstBadVersion(int n);
    boolean isBadVersion(int n);
    int getCount();
  }

  public static abstract class AbstractSolution implements Solution {

    private int firstBadVersion;
    private int count;

    public void init(int firstBadVersion) {
      this.firstBadVersion = firstBadVersion;
      this.count = 0;
    }

    @Override
    public boolean isBadVersion(int n) {
      count++;
      return n >= firstBadVersion;
    }

    @Override
    public int getCount() {
      return count;
    }
  }

  public static class Solution1 extends AbstractSolution implements Solution {

    @Override
    public int firstBadVersion(int n) {
      int left = 1;
      int right = n;
      while(left != right) {
        int mid = (left / 2) + (right / 2);
        if(!isBadVersion(mid)) left = mid + 1;
        else right = mid;
      }
      return left;
    }

  }

  public static void main(String args[]) {
    int total;
    int firstBadNumber;
    int count;

    for(Solution s : Arrays.asList(new Solution1())) {
      total = 2126753390;
      firstBadNumber = 1702766719;
      s.init(firstBadNumber);
      if(s.firstBadVersion(total) == firstBadNumber) {
        count = s.getCount();
        System.out.println(count);
      }
    }
  }


}
