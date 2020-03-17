package coreJavaVolume2AdvancedFeatures.optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author zhangjinglong
 * @date 2020-03-16-10:32 上午
 */

public class OptionalTest {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("resources/text/cities.txt")),
                StandardCharsets.UTF_8);

        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("fr"))
                .findFirst();
        System.out.println(optionalValue.orElse("No word") + " contains fr");

        Optional<String> optionString = Optional.empty();
        String result = optionString.orElse("N/A");
        System.out.println("result: " + result);
        result = optionString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result:" + result);

//        try{
//            result=optionString.orElseThrow(IllegalStateException::new);
//            System.out.println("result: "+result);
//        }catch (Throwable e){
//            e.printStackTrace();
//        }

        optionalValue = wordList.stream()
                .filter(s -> s.contains("re"))
                .findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains re"));

        Set<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));

        Optional<Double> results2 = Optional.of(-4.0)
                .flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println(results2);


    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
