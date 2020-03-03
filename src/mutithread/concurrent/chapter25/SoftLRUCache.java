package mutithread.concurrent.chapter25;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * SoftReference 修饰
 * @author zhangjinglong
 * @date 2020-02-22-23:39
 */

public class SoftLRUCache<K,V> {
    //用于记录key值的顺序
    private final LinkedList<K> keylist=new LinkedList<>();

    //Value用SoftReference 进行修饰
    private final Map<K, SoftReference<V>> cache=new HashMap<>();

    //cache的最大容量
    private final int capacity;

    //cacheLoader接口提供了一种加载数据的方式
    private  final CacheLoader<K,V> cacheLoader;

    public SoftLRUCache(int capacity, CacheLoader<K,V> cacheLoader){
        this.capacity=capacity;
        this.cacheLoader=cacheLoader;
    }

    public void put(K key,V value){
        //当元素数量超过容量时，将最老的数据清除
        if(keylist.size()>=capacity){
            K eldestKey=keylist.removeFirst();//eldest data
            cache.remove(eldestKey);
        }
        //如果数据已经存在，则从key的队列中删除
        if(keylist.contains(key)){
            keylist.remove(key);
        }
        //将key存放至队尾
        keylist.addLast(key);

        //保存 SoftReference
        cache.put(key,new SoftReference<>(value));
    }

    public V get(K key){
        V value;
        //先将key从key list中删除
        boolean success=keylist.remove(key);
        if(!success){//如果删除失败则表明该数据不存在
            //通过cacheloader对数据进行加载
            value=cacheLoader.load(key);
            //调用put方法cache数据
            this.put(key,value);

        }else{
            //如果删除成功，则总cache中返回数据，并将key再次放到队尾
            value=cache.get(key).get();
            keylist.addLast(key);
        }

        return value;

    }

    @Override
    public String toString() {
        return this.keylist.toString();
    }

    public static void main(String[] args) throws InterruptedException{
        //运行会出现内存溢出  -Xmx128M -Xms64M -XX:+PrintGCDetails
        SoftLRUCache<Integer,Reference> cache1=new SoftLRUCache<>(200, key->new Reference());
        System.out.println(cache1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cache1.get(i);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The "+i+" reference stored at cache.");
        }
    }
}
