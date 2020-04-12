package jobInterview.niuke.test13;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-04-12-1:56 下午
 */

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.next();
            int lineNum = scanner.nextInt();
            String[] arr = line.split("\\\\");//根据\切割
            String s = arr[arr.length - 1];
            if (s.length() > 16) {//截取超过16的文件名
                s = s.substring(s.length() - 16);
            }
            String key = s + " " + lineNum;
            map.put(key, map.getOrDefault(key, 0).intValue() + 1);

        }

        int count = 0;
        for (String s : map.keySet()) {
            count++;
            if (count > (map.keySet().size() - 8))//输出最后8个记录
                System.out.println(s + " " + map.get(s));
        }

    }
}
