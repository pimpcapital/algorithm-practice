package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.category.solution.WithBinaryIndexedTree;
import com.beijunyi.leetcode.category.solution.WithBinarySearchTree;

/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 *   A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 * Example:
 *   Given nums = [-2, 5, -1], lower = -2, upper = 2,
 *   Return 3.
 *   The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class _327_CountOfRangeSum implements Hard {

  public interface Solution {
    int countRangeSum(int[] nums, int lower, int upper);
  }

  public static class Solution1 implements Solution, WithBinarySearchTree {

    @Override
    public int countRangeSum(int[] nums, int lower, int upper) {
      if(nums.length == 0) return 0;

      long[] sums = new long[nums.length]; // prefix sums: sum[I] = nums[0] + nums[1] + ... + nums[I]
      sums[0] = nums[0];
      for(int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i];
      int count = 0;

      SortedMap<Long, Integer> sumsCount = new TreeMap<>();
      for(int i = sums.length - 1; i >= 0; i--) {
        sumsCount.compute(sums[i], (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
        long sumBeforeI = i == 0 ? 0 : sums[i - 1]; // for every I we find J such that: lower <= sum(J)-sum(I-1) <= upper
        long min = sumBeforeI + lower;
        long max = sumBeforeI + upper + 1; // exclusive
        for(int value : sumsCount.subMap(min, max).values()) count += value;
      }

      return count;
    }

  }

  /**
   * First of all, let's look at the naive solution. Preprocess to calculate the prefix sums S[i] = S(0, i), then
   * S(i, j) = S[j] - S[i]. Note that here we define S(i, j) as the sum of range [i, j) where j exclusive and j > i.
   * With these prefix sums, it is trivial to see that with O(n^2) time we can find all S(i, j) in the range [lower,
   * upper]
   *
   * Java - Naive Solution
   *   public int countRangeSum(int[] nums, int lower, int upper) {
   *     int n = nums.length;
   *     long[] sums = new long[n + 1];
   *     for (int i = 0; i < n; ++i)
   *       sums[i + 1] = sums[i] + nums[i];
   *     int ans = 0;
   *     for (int i = 0; i < n; ++i)
   *       for (int j = i + 1; j <= n; ++j)
   *         if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
   *     ans++;
   *     return ans;
   *   }
   *
   * However the naive solution is set to TLE intentionally
   * Now let's do better than this.
   *
   * Recall count smaller number after self where we encountered the problem
   *   * count[i] = count of nums[j] - nums[i] < 0 with j > i
   *
   * Here, after we did the preprocess, we need to solve the problem
   *   * count[i] = count of a <= S[j] - S[i] <= b with j > i
   *   * ans = sum(count[:])
   *
   * Therefore the two problems are almost the same. We can use the same technique used in that problem to solve this
   * problem. One solution is merge sort based; another one is Balanced BST based. The time complexity are both
   * O(n log n).
   *
   * The merge sort based solution counts the answer while doing the merge. During the merge stage, we have already
   * sorted the left half [start, mid) and right half [mid, end). We then iterate through the left half with index i.
   * For each i, we need to find two indices k and j in the right half where
   *   * j is the first index satisfy sums[j] - sums[i] > upper and
   *   * k is the first index satisfy sums[k] - sums[i] >= lower.
   * Then the number of sums in [lower, upper] is j-k. We also use another index t to copy the elements satisfy
   * sums[t] < sums[i] to a cache in order to complete the merge sort.
   *
   * Despite the nested loops, the time complexity of the "merge & count" stage is still linear. Because the indices k,
   * j, t will only increase but not decrease, each of them will only traversal the right half once at most. The total
   * time complexity of this divide and conquer solution is then O(n log n).
   *
   * One other concern is that the sums may overflow integer. So we use long instead.
   */
  public static class Solution2 implements Solution, WithBinaryIndexedTree {

    @Override
    public int countRangeSum(int[] nums, int lower, int upper) {
      int n = nums.length;
      long[] sums = new long[n + 1];
      for (int i = 0; i < n; ++i)
        sums[i + 1] = sums[i] + nums[i]; // sum[i] is sum up to i (exclusive)
      return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    /**
     * Key notes to myself.
     *
     * After solving sub-problem [1, mid) and [mid, N), all pairs (i, j) where lower <= sums[j] - sums[i] <= upper
     * fall into these 3 categories:
     *   1) i, j both in [1, mid)
     *   2) i, j both in [mid, N)
     *   3) i in [1, mid) and j in [mid, N)
     * The count in case 1 and case 2 are both calculated and returned by solving the sub problems
     * Case 3 is unsolved. Case 3 can be solved by linear time:
     *   for every possible i in [1, mid), find range [j, k) such that every element X in this range satisfies
     *   lower <= X - sums[i] <= upper. This requires j to be the index of the first element in [1, mid) who is equal to
     *   or greater than lower + sums[i], and k to be the index of the first element in [1, mid) who is greater than
     *   upper + sums[j]
     *
     * Note that [mid, N) is sorted, so that everything outside [j, k) will not satisfy the condition.
     */
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
      if (end - start <= 1) return 0;
      int mid = (start + end) / 2;
      int count = countWhileMergeSort(sums, start, mid, lower, upper)
                    + countWhileMergeSort(sums, mid, end, lower, upper);
      int k, j = mid, t = mid;
      long[] cache = new long[end - start];
      for(int i = start, r = 0; i < mid; ++i, ++r) {
        while(j < end && sums[j] - sums[i] < lower) j++;
        k = j;
        while(k < end && sums[k] - sums[i] <= upper) k++;
        while(t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
        cache[r] = sums[i];
        count += k - j;
      }
      System.arraycopy(cache, 0, sums, start, t - start);
      return count;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int lower;
    int upper;
    int result;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      nums = new int[]{-2, 5, -1};
      lower = -2;
      upper = 2;
      result = s.countRangeSum(nums, lower, upper);
      System.out.println(result); // 3

      nums = new int[]{-2147483647, 0, -2147483647, 2147483647};
      lower = -564;
      upper = 3864;
      result = s.countRangeSum(nums, lower, upper);
      System.out.println(result); // 3

      System.out.println();
    }
  }

}
