package com.java.lessons.arrays;

import java.util.Arrays;
import java.util.List;

public class ReverseIntArrays {

    public static List<Integer> reverseArray(int [] intArray) {

        int i, temp;
        for (i = 0; i < intArray.length / 2; i++) {

            temp = intArray[i];
            intArray[i] = intArray[intArray.length - i - 1];
            intArray[intArray.length - i - 1] = temp;
        }

        System.out.println("Reversed Array: \n" + Arrays.toString(intArray));
        return null;
    }

    public static void main(String[] args) {

        int [] intArray = {11,22,33,44,55,66,77,88,99};

        System.out.println("Original Array: \n" + Arrays.toString(intArray));

        reverseArray(intArray);
    }
}
