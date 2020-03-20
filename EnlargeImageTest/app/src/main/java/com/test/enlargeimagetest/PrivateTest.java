package com.test.enlargeimagetest;

public class PrivateTest {

    private final int a = 6;
    public final int b = 7;
    private final static int A = 666;
    public final static int B = 777;

    public static void main(String[] args) {
        FinalStaticTest test = new FinalStaticTest();
        FinalStaticTest.sb = 2;
    }
}
