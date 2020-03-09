package jobInterview.singleton.test;

import jobInterview.singleton.Singleton3;

/**
 * @author zhangjinglong
 * @date 2020-03-03-11:32 下午
 */

public class TestSingleton3 {
    public static void main(String[] args) {
        Singleton3 singleton3=Singleton3.INSTANCE;
        System.out.println(singleton3);
    }
}
