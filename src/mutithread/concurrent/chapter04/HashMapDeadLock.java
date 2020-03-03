package mutithread.concurrent.chapter04;

import java.util.HashMap;

/**
 * @author zhangjinglong
 * @date 2020-02-16-22:01
 */

public class HashMapDeadLock {
    private final HashMap<String,String> map=new HashMap<>();

    public void add(String key,String value){
        this.map.put(key,value);
    }

    public static void main(String[] args) {
        final HashMapDeadLock hmdl=new HashMapDeadLock();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 1; j < Integer.MAX_VALUE; j++) {
                    hmdl.add(String.valueOf(j),String.valueOf(j));
                }
            }).start();
        }
    }
}
