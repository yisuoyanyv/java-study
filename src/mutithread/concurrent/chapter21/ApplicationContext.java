package mutithread.concurrent.chapter21;

/**
 *
 * 使用单例对象充当系统级别上下文的例子
 * @author zhangjinglong
 * @date 2020-02-22-16:47
 */


public class ApplicationContext {
    //在Context 中保存configuration实例
    private ApplicationConfiguration configuration;
    //在Context中保存runtimeInfo实例
    private RuntimeInfo runtimeInfo;
    //....其他

    //使用Holder的方式实现单例
    private static class Holder{
        private static ApplicationContext instance=new ApplicationContext();
    }

    public static ApplicationContext getContext(){
        return Holder.instance;
    }

    public void setConfiguration(ApplicationConfiguration configuration){
        this.configuration=configuration;
    }

    public ApplicationConfiguration getConfiguration(){
        return this.configuration;
    }
    public void setRuntimeInfo(RuntimeInfo runtimeInfo){
        this.runtimeInfo=runtimeInfo;
    }

    public RuntimeInfo getRuntimeInfo(){
        return this.runtimeInfo;
    }

    private class ApplicationConfiguration{

    }

    private class RuntimeInfo{

    }
}
