package mutithread.concurrent.chapter21;

/**
 * 线程上下文
 * 为每个资源设置一个LocalThread
 * @author zhangjinglong
 * @date 2020-02-22-19:29
 */

public class ActionContext2 {


    //为Configuration创建ThreadLocal
    private static final ThreadLocal<Configuration> configuration=
            ThreadLocal.withInitial(Configuration::new);


    //为OtherResource创建ThreadLocal
    private static final ThreadLocal<OtherResource> otherResource=
            ThreadLocal.withInitial(OtherResource::new);





    public static Configuration getConfiguration() {
        return configuration.get();
    }

    public static void setConfiguration(Configuration conf) {
        configuration.set(conf);
    }

    public  static OtherResource getOtherResource() {
        return otherResource.get();
    }

    public static void setOtherResource(OtherResource other) {
        otherResource.set(other);
    }


    static class Configuration{



    }

    static  class OtherResource{

    }
}
