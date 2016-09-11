package com.beijunyi.leetcode.category.solution;

import java.util.Arrays;

/**
 * https://interviewalgorithm.wordpress.com/sortingordering/median-of-medians-algorithm/
 *
 * MEDIAN OF MEDIANS ALGORITHM
 * Find the median of given n numbers in O(n) time.
 *
 * In this post, we will consider the problem of finding the median of an unsorted list of n elements.
 * The median of a given set of numbers is the value for which half the numbers are larger and half are smaller. If
 * there are two middle numbers, the median is the arithmetic mean of the two middle numbers.
 *
 * Naive Solution – The naive solution is to sort the given numbers and return the median. Worst case time complexity
 * for this approach is O(nlogn).
 *
 * However, this approach does more work than required, because we don’t need to know the sorted-position(order
 * statistics) of each element in the list of numbers.
 * The O(n) solution to this problem is based on quicksort algorithm. QuickSort algorithm :-
 *
 * 1. Choose a pivot.
 * 2. Rearrange numbers such that numbers to the left are smaller than pivot and numbers to the right are greater than
 * the pivot.
 * 3. Recursively sort left and right sublists.
 *
 * We can construct a similar solution to find the median of the given list of numbers, but unlike QuickSort we can
 * eliminate either of the two sublists(left or right), depending on the position of the pivot :-
 *
 * O(n) Solution
 *
 * Input – arr [0……n-1], list of unordered numbers
 * Output – Median of the given numbers
 *
 * 1. Select a pivot element based on Median Of Medians(see below).
 * 2. Rearrange numbers such that, elements to the left of pivot and smaller and elements to the right are greater than
 * the pivot.
 * 3. Let pivotIndex be the index of the pivot.
 * 4. If pivotIndex == arr.length/2,  we have found the median, return pivotIndex.
 * 5. else if pivotIndex < arr.length/2,  recursively search for Median in the left subArray, arr[start……pivotIndex-1]
 * 6. else if pivotIndex > arr.length/2,  recursively search for Median in the right subArray, arr[pivotIndex+1…end]
 *
 * Step 2-6 Is basically a divide-and-conquer approach to solving the given problem. At each iteration, we (potentially)
 * reduce the size of the list in which we are searching.
 *
 * However, if we naively choose the pivot in step1, in the worst case we could always choose the largest(or smallest)
 * element in the subarray in which we are searching.
 * There for the worst case for the algorithm(if pivot is chosen at random/naively) is O(n^2)
 *
 * Step 1 To guarantee a linear running time of our algorithm, we need need to design another method such that the
 * chosen pivot is guaranteed to be ‘close’ to the median of the given numbers. By choosing a pivot close to the
 * median, we can guarantee that, we reduce the number of elements to search-in by (almost) half in each iteration.
 *
 * The median-of-medians algorithm chooses the pivot in the following way :-
 *
 * 1. Divide the list into sublists of length five. (Note that the last sublist may have length less than five.)
 * 2. Sort each sublist and determine its median directly.
 * 3. Use the median of medians algorithm to recursively determine the median of the set of all medians from the
 * previous step.
 * 4. Use the median of the medians from step 3 as the pivot.
 *
 * This algorithm guarantees that our pivot is not too far from the median.
 *
 * Time Complexity : O(n)
 *
 * Example
 *
 * Input – Array[ 5 2 17 13 14 17 19 14 18 2 4 8 2 5 0 6 13 4 12 18 10 ]
 *
 * Step1 Choosing Pivot –
 *
 *   1st 5 elements – 5 2 17 13 14
 *   2nd 5 elements – 17 19 14 18 2
 *   3rd 5 elements – 4 8 2 5 0
 *   4th 5 elements – 6 13 4 12 18
 *   5th 5 elements – 10
 *
 *   Median1 – 13
 *   Median2 – 17
 *   Median3 – 4
 *   Median4 – 12
 *   Median5 – 10
 *
 *   MedianOfMedians – 12
 *
 * Step2 – Rearranging elements, using 12 as pivotValue
 *
 *   [ 5 2 10 4 6 0 5 2 8 2 4 12* 18 14 19 17 13 14 13 18 ]
 *
 *   12 is at the 12th index in the sorted array, and we are seating for 11th largest element array(there are 21 element
 *   in the input array)
 *   Since 12 > 11, we search in the left subArray.
 *
 *   Left SubArray – [ 5 2 10 4 6 0 5 2 8 2 4 ]
 *
 * Step1 Choosing pivot
 *
 *   [ 5 2 10 4 6 0 5 2 8 2 4 ]
 *   1st 5 elements – 5 2 10 4 6
 *   2nd 5 elements – 0 5 2 8 2
 *   3rd 5 elements – 4
 *
 *   Median1 – 5
 *   Median2 – 2
 *   Median3 – 4
 *
 *   Median of Medians – 4
 *
 * Step2 Rearranging elements, using 4 as pivotValue
 *
 *   Full Array – [ 2 2 2 4 0 4 5 6 8 10 5 12* 18 14 19 17 13 14 13 18 ]
 *
 *   Array being processed in this iteration(elements left of 12) –
 *   [ 2 2 2 4 0 4* 5 6 8 10 5 ]
 *
 *   pivotValue 4 is at the 6th index of the subarray, and we are searching for 11th largest element in the array.(from
 *   last iteration)
 *   Since 4<11, we should search in the right subtree.
 *
 *   By searching recursively in the right subtree, we are discarding some elements, that are to the left of pivotValue 4,
 *   therefore instead of 11th largest element, we should search for 11-6=5, 5th largest element in the remaining
 *   subarray. Another way to look at it is that since 4 is at index 6, we have already found 6 elements that are less
 *   than the current pivotValue 4.
 *
 *   Therefore in the next iteration, we should search :-
 *   [5 6 8 10 5]  for the 5th largest value.
 *
 * Step1 Choosing pivot
 *
 *   Full Array – [ 2 2 2 4 0 4 5 6 8 10 5 12* 18 14 19 17 13 14 13 18 ]
 *   Array after iteration1 – [ 2 2 2 4 0 4* 5 6 8 10 5 ]
 *
 *   Array being processed in this iteration :-
 *   [5 6 8 10 5]
 *   Median – 6
 *
 * Step2 Rearranging – [5 5 6 8 10]
 *   6 is at Index 3, 3<5
 *   Go right, find (5-3 = 2) , 2nd largest in [8 10]
 *
 * Step1 Choosing pivot
 *
 *   Pivot -8
 *   8 is at the 1st index
 *   1<2, go right, find (2-1) = 1st largest number in [10]
 *
 *   Median = 10
 *
 *   The Median of Medians can be generalized to find the kth largest number in a given list of unsorted numbers.
 */
public interface MedianOfMedians {

  class Example {
    public static void findMedian(int arr[]) {
      int median = findMedianUtil(arr, (arr.length) / 2 + 1, 0, arr.length - 1);
      System.out.println("Median = " + median);
    }

    private static int findMedianUtil(int arr[], int k, int low, int high) {
      // Uncomment this if you want to print the current subArray being processed/searched
      //printArray(arr, low, high);

      if(low == high) {
        return arr[low];
      }

      // sort the mth largest element in the given array
      int m = partition(arr, low, high);

      // Adjust position relative to the current subarray being processed
      int length = m - low + 1;

      // If mth element is the median, return it
      if(length == k) {
        return arr[m];
      }

      // If mth element is greater than median, search in the left subarray
      if(length > k) {
        return findMedianUtil(arr, k, low, m - 1);
      }
      // otherwise search in the right subArray
      else {
        return findMedianUtil(arr, k - length, m + 1, high);
      }

    }

    private static int partition(int arr[], int low, int high) {
      // Get pivotvalue by finding median of medians
      int pivotValue = getPivotValue(arr, low, high);

      // Find the sorted position for pivotVale and return it's index
      while(low < high) {
        while(arr[low] < pivotValue) {
          low++;
        }

        while(arr[high] > pivotValue) {
          high--;
        }

        if(arr[low] == arr[high]) {
          low++;
        } else if(low < high) {
          int temp = arr[low];
          arr[low] = arr[high];
          arr[high] = temp;
        }

      }
      return high;
    }

    // Find pivot value, such the it is always 'closer' to the actual median
    private static int getPivotValue(int arr[], int low, int high) {
      // If number of elements in the array are small, return the actual median
      if(high - low + 1 <= 9) {
        Arrays.sort(arr);
        return arr[arr.length / 2];
      }

      //Otherwise divide the array into subarray of 5 elements each, and recursively find the median

      // Array to hold '5 element' subArray, last subArray may have less than 5 elements
      int temp[] = null;

      // Array to hold the medians of all '5-element SubArrays'
      int medians[] = new int[(int)Math.ceil((double)(high - low + 1) / 5)];
      int medianIndex = 0;

      while(low <= high) {
        // get size of the next element, it can be less than 5
        temp = new int[Math.min(5, high - low + 1)];

        // copy next 5 (or less) elements, in the subarray
        for(int j = 0; j < temp.length && low <= high; j++) {
          temp[j] = arr[low];
          low++;
        }

        // sort subArray
        Arrays.sort(temp);

        // find mean and store it in median Array
        medians[medianIndex] = temp[temp.length / 2];
        medianIndex++;
      }

      // Call recursively to find median of medians
      return getPivotValue(medians, 0, medians.length - 1);
    }

    public static void main(String args[]) {
      int nElements = 10001;
      int arr[] = new int[nElements];

      for(int i = 0; i < nElements; i++) {
        arr[i] = (int)(2000 * Math.random());
      }

      System.out.print("Array");
      printArray(arr, 0, nElements);

      findMedian(arr);

      java.util.Arrays.sort(arr);
      System.out.print(" Sorted Array - ");
      printArray(arr, 0, nElements);
      System.out.println("Median using sorting - " + arr[(arr.length) / 2]);
    }

    private static void printArray(int arr[], int low, int high) {
      System.out.print("[  ");
      for(int i = low; i < high; i++) {
        System.out.print(arr[i] + "  ");
      }
      System.out.println("]");
    }

  }

}
