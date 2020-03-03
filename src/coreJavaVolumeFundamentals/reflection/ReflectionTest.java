package coreJavaVolumeFundamentals.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-02-27-10:01 下午
 */

public class ReflectionTest {
    public static void main(String[] args) {
        //read class name from command line args or use input
        String name;
        if(args.length>0)  name=args[0];
        else{
            Scanner in=new Scanner(System.in);
            System.out.println("Enter class name(e.g. java.util.Date):");
            name=in.next();
        }
        try{
            //print class name adn superclass name(if !=Object)
            Class cl=Class.forName(name);
            Class supercl=cl.getSuperclass();
            String mofifiers= Modifier.toString(cl.getModifiers());
            if(mofifiers.length()>0) System.out.print(mofifiers+" ");
            System.out.println("class "+name);
            if(supercl!=null &&supercl!=Object.class) System.out.println(" extends "+supercl.getName());
            System.out.println("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * Prints all constructors of a class
     * @param cl
     */
    public static void printConstructors(Class cl){
        Constructor[] constructors=cl.getConstructors();

        for(Constructor c:constructors){
            String name=c.getName();
            System.out.print("    ");
            String modifiers=Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.print(name+"(");

            //print parameter types
            Class[] paramTypes=c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if(j>0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");

        }
    }

    /**
     * Prints all methods of a class
     * @param cl
     */
    public static void printMethods(Class cl){
        Method[]  methods=cl.getDeclaredMethods();

        for(Method m:methods){
            Class retType=m.getReturnType();
            String name=m.getName();
            System.out.print("    ");
            //print modifies,return type and method name
            String modifiers=Modifier.toString(m.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.print(retType.getName()+" "+name+"(");
            //print parameter types
            Class[] paramTypes=m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if(j>0) System.out.print((", "));
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }

    }

    /**
     * prints all fields of a class
     * @param cl
     */
    public static void printFields(Class cl){
        Field[] fields=cl.getDeclaredFields();
        for (Field f:fields){
            Class type=f.getType();
            String name=f.getName();
            System.out.print("    ");
            String modifiers=Modifier.toString(f.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }
}
