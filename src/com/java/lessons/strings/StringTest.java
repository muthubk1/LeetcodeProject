package com.java.lessons.strings;

public class StringTest {

    private  static String calFun() {
        String three= "abc";
        return three;
    }

    public  static  void main(String[] args) {
        String one= "abc";
        String two= "abc";
        //Compare whether it is the same object. Both are the same string in the string constant pool,
        // so they point to the same reference in the heap.
        System.out.println(one==two);
        //This compares content, which must be equal
        System.out.println(one.equals(two));
        //  three in one and calFun functions are in two In each function, but the pointers are all the same reference
        //  in the string constant pool. All are references to strings in the heap.
        System.out.println(one==calFun() );
        String s = "His age is "+9;

        System.out.println(s);
    }

}
