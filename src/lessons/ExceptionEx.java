package com.java.lessons;

public class ExceptionEx {

    public void doSomethingElse() throws Exception{

        System.out.println("Inside doSomethingElse");
        throw new Exception("OCJP exception");
    }

    public void doSomething(){

        System.out.println("Inside doSomething");
        try {
            doSomethingElse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
