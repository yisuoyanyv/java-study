package mutithread.concurrent.chapter21;

/**
 * @author zhangjinglong
 * @date 2020-02-22-19:29
 */

public class ActionContext {
    //定义ThreadLoacal,并且使用Supplier的方式重写initValue
    private static final ThreadLocal<Context> contex=ThreadLocal.withInitial(Context::new);

    //每一个线程都有一个独立的Context实例
    static class Context{
        //在Context中的其他成员
        private Configuration configuration;
        private OtherResource otherResource;



        public static Context get(){
            return contex.get();
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public OtherResource getOtherResource() {
            return otherResource;
        }

        public void setOtherResource(OtherResource otherResource) {
            this.otherResource = otherResource;
        }
    }

    private class Configuration{

    }

    private class OtherResource{

    }
}
