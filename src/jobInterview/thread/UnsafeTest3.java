package jobInterview.thread;

import sun.misc.Unsafe;

/**
 * @author zhangjinglong
 * @date 2020-04-18-9:06 上午
 * <p>
 * Unsafe操作数组空间
 */

public class UnsafeTest3 {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeUtil.getInstance();
        String[] names = {"卡卡西", "佐助", "鸣人"};
        //取出第一个元素数据
        Object firstName = unsafe.getObject(names, Unsafe.ARRAY_OBJECT_BASE_OFFSET);
        System.out.println("第一个数据：" + firstName);
        //修改第二个元素的值
        unsafe.putObject(names, Unsafe.ARRAY_OBJECT_BASE_OFFSET + Unsafe.ARRAY_OBJECT_INDEX_SCALE, "漩涡鸣人");
        Object secondName = unsafe.getObject(names, Unsafe.ARRAY_OBJECT_BASE_OFFSET + Unsafe.ARRAY_OBJECT_INDEX_SCALE);
        System.out.println("第二个数据：" + secondName);

    }
}
