package com.test.enlargeimagetest;

public class FinalStaticTest {

    private final int a = 6;
    public final int b = 7;
    private final static int A = 666;
    public final static int B = 777;
    private static int sa = 213;
    public static int sb = 233;

    public static void main(String[] args) {
        FinalStaticTest test = new FinalStaticTest();
        FinalStaticTest.sa = 2;
        FinalStaticTest.sb = 3;
    }

}
