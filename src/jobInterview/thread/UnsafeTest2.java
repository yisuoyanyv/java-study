package jobInterview.thread;

import sun.misc.Unsafe;

/**
 * @author zhangjinglong
 * @date 2020-04-18-8:37 上午
 * <p>
 * Unsafe类操作对象的属性
 */

public class UnsafeTest2 {
    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeUtil.getInstance();

        //获取指定属性在内存中的偏移量

        //静态成员偏移量
        long nameOffset = unsafe.staticFieldOffset(User2.class.getDeclaredField("name"));
        System.out.println("nameOffset:" + nameOffset);
        //非静态成员偏移量
        long telOffset = unsafe.objectFieldOffset(User2.class.getDeclaredField("tel"));
        System.out.println("telOffset:" + telOffset);

        //创建对象 开辟空间
        User2 user = new User2();

        //给属性赋值（给指定的内存空间赋值）
        unsafe.putObject(user, nameOffset, "mingren");
        unsafe.putObject(user, telOffset, "9920012012");

        // 取出属性值（取出指定空间的数据）
        String name = unsafe.getObject(user, nameOffset).toString();
        String tel = unsafe.getObject(user, telOffset).toString();
        System.out.println("name=" + name + ",tel=" + tel);


    }

}

class User2 {
    //静态成员变量
    static String name = "kaka";
    //非静态成员变量
    String tel = "18899990000";


}