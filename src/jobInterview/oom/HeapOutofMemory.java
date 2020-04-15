package jobInterview.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjinglong
 * @date 2020-04-14-10:10 下午
 * <p>
 * test Heap Out of Memory
 */

public class HeapOutofMemory {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        while (true) {
            list.add("zjl");
        }

    }
}
