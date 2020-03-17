package coreJavaVolume2AdvancedFeatures.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangjinglong
 * @date 2020-03-16-9:08 上午
 */

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("resources/text/cities.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 5) count++;
        }

        System.out.println(count);

        count = words.stream().filter(w -> w.length() > 5).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > 5).count();
        System.out.println(count);
    }
}
