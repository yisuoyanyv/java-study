package jobInterview.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangjinglong
 * @date 2020-04-18-8:38 上午
 * <p>
 * Unsafe创建工具类
 */

public class UnsafeUtil {
    public static Unsafe getInstance() {
        //通过反射获取Unsafe类对象
        Unsafe unsafe = null;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            System.out.println(unsafe);

        } catch (Exception e) {

        }
        return unsafe;
    }
}
