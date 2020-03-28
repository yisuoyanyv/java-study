package coreJavaVolumeFundamentals.current;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangjinglong
 * @date 2020-02-14-19:15
 */

public class ThreadLocalTest {
    public static final ThreadLocal<SimpleDateFormat> dateFormat=
            ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        System.out.printf(dateFormat.get().format(new Date()));
    }
}
