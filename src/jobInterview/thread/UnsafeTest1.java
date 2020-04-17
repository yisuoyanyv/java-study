package jobInterview.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangjinglong
 * @date 2020-04-17-10:44 下午
 * <p>
 * Unsafe类测试
 * <p>
 * Unsafe可直接操作内存空间，但不是java规范推荐的
 */

public class UnsafeTest1 {
    public static void main(String[] args) {
        //获取类对象
//        Unsafe unsafe=Unsafe.getUnsafe();
//        System.out.println(unsafe);

        //通过反射获取Unsafe类对象
        Unsafe unsafe = null;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            System.out.println(unsafe);

        } catch (Exception e) {

        }
    }

}
