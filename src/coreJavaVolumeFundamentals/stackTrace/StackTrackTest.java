package coreJavaVolumeFundamentals.stackTrace;

import java.util.Scanner;

/**
 * a program that displays a trace feature of a recursive method call.
 * @author zhangjinglong
 * @date 2020-02-29-9:25 下午
 */

public class StackTrackTest {

    public static int factorial(int n){
        System.out.println("factorial("+n+"):");
        Throwable t=new Throwable();
        StackTraceElement[] frames=t.getStackTrace();
        for(StackTraceElement f:frames){
            System.out.println(f);
        }

        int r;
        if(n<=1) r=1;
        else r=n*factorial(n-1);
        System.out.println("return "+r);
        return r;
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter n:");
        int n=in.nextInt();
        factorial(n);
    }
}
