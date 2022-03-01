package com.java.leetcode;

public class BinarySearch {

    public int search(int[] nums, int target) {

        int midIndices = nums.length/2;
        System.out.println(midIndices);
        if(nums[midIndices] == target)
            return midIndices;

        if(nums[midIndices] < target){
            for(int i=midIndices;i<nums.length;i++){
                System.out.println("i=>"+i);
                if(nums[i] == target)
                    return i;
            }
        } else {
            for(int i=0;i<midIndices;i++){
                if(nums[i] == target)
                    return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {-1,0,3,5,9,12};
        int[] nums1 = {-1,0,3,5,9,12};
        int target = 9;
        int target1 = 2;
        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.search(nums1, target1);
        System.out.println("Target Indices  =>"+result);

    }
}
