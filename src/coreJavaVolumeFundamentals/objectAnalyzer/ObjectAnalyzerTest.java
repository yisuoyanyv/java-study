package coreJavaVolumeFundamentals.objectAnalyzer;

import java.util.ArrayList;

/**
 * @author zhangjinglong
 * @date 2020-02-27-11:09 下午
 */

public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares=new ArrayList<>();
        for(int i=1;i<=5;i++){
            squares.add(i*i);

        }
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
