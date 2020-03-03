package mutithread.concurrent.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhangjinglong
 * @date 2020-02-18-23:12
 */

public class BrokeDelegateClassLoader extends ClassLoader {
    //定义默认的class存放路径
    private final static Path DEFAULT_CLASS_DIR= Paths.get("/Users/zhangjinglong/test");

    private final Path classDir;

    //使用默认的class路径
    public BrokeDelegateClassLoader(){
        super();
        this.classDir=DEFAULT_CLASS_DIR;
    }

    //允许传入指定路径的class路径
    public BrokeDelegateClassLoader(String classDir){
        super();
        this.classDir=Paths.get(classDir);
    }

    //指定class路径的同时，指定父类加载器
    public BrokeDelegateClassLoader(String classDir,ClassLoader parent){
        super(parent);
        this.classDir=Paths.get(classDir);

    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){
            Class<?> klass=findLoadedClass(name);

            if(klass==null){
                if(name.startsWith("java.")||name.startsWith("javax")){
                    try{
                        klass=getSystemClassLoader().loadClass(name);
                    }catch (Exception e){
                        //ignore
                    }
                }else {
                    try{
                        klass=this.findClass(name);
                    }catch (ClassNotFoundException e){
                        //ignore
                    }
                    if (klass==null){
                        if(getParent()!=null){
                            klass=getParent().loadClass(name);
                        }else {
                            klass=getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }

            if(null==klass){
                throw new ClassNotFoundException("The class "+name+" not found. ");
            }
            if(resolve){
                resolveClass(klass);
            }
            return klass;
        }
    }

    //重写父类的findClass方法，这是至关重要的步骤
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取class的二进制数据
        byte[] classBytes=this.readClassBytes(name);
        //如果数据为null，或者没有读到任何信息，则抛出ClassnotFoundException异常
        if(null == classBytes || classBytes.length==0){
            throw new ClassNotFoundException("Can not load the class "+name);
        }
        //调用defineClass方法定义class
        return this.defineClass(name,classBytes,0,classBytes.length);
    }


    //将class文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException{
        //将包名分隔符转换为文件路径分隔符
        String classPath=name.replace(".","/");
        Path classFullPath=classDir.resolve(Paths.get(classPath+".class"));
        System.out.println(classFullPath.toString());
        if(!classFullPath.toFile().exists()){

            throw new ClassNotFoundException("The class "+name+" not found.");

        }
        try(ByteArrayOutputStream baos=new ByteArrayOutputStream()){
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        }catch (IOException e){
            throw new ClassNotFoundException("load the class "+name+" occur error,", e);
        }

    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
