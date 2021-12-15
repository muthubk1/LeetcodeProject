package com.java;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/kth-largest-element-in-an-array/
    215. Kth Largest Element in an Array - Medium
    Given an integer array nums and an integer k, return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.

    Example 1:
    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5

    Example 2:
    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4

    Constraints:
    1 <= k <= nums.length <= 104
    -104 <= nums[i] <= 104
*/
public class ArrayKLargestElement {

    public static int findKthLargestHeap(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }

    public static int findKthLargest(int[] nums, int k) {

        Arrays.sort(nums);
        System.out.println(nums);
        System.out.println(nums.length);
        System.out.println(nums.length-k+1);
        int index =nums.length-k;
        int res = nums[index];
        return res;
    }

    public static void main(String arg[]) {

        System.out.println("Raja Maha ganapathy namah");
        int nums[] = {3,2,1,5,6,4};
        int k =2;
        int result = findKthLargest(nums, k);
        System.out.println("Result=>"+result);

    }
}
