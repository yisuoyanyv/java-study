package coreJavaVolumeFundamentals.lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zhangjinglong
 * @date 2020-02-29-2:03 下午
 */

public class LambdaTest {
    public static void main(String[] args) {
        String[] plants=new String[]{"Mercury","Venus","Earch","Mars","Jupiter","Saturn","Uranus","Neptune"};
        System.out.println(Arrays.toString(plants));
        System.out.println("Sorted in dictionary order");
        Arrays.sort(plants);
        System.out.println(Arrays.toString(plants));
        System.out.println("Sorted by length:");
        Arrays.sort(plants,(first,second)->first.length()-second.length());
        System.out.println(Arrays.toString(plants));

        Timer t=new Timer(1000,event-> System.out.println("The time is "+new Date()));

        t.start();

        //keep program running until user select "OK'
        JOptionPane.showMessageDialog(null,"Quit Program?");
        System.exit(0);
    }
}
