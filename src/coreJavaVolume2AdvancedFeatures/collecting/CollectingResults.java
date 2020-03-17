package coreJavaVolume2AdvancedFeatures.collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangjinglong
 * @date 2020-03-16-10:58 上午
 */

public class CollectingResults {
    public static void main(String[] args) throws IOException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object Array:" + numbers);//Note it's an Object[] array

        try {
            Integer number = (Integer) numbers[0];//OK
            System.out.println("number:" + number);
            System.out.println("The following statement throws an exception");
            Integer[] numbers2 = (Integer[]) numbers;//Throws exception
        } catch (ClassCastException ex) {
            System.out.println(ex);
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);

        System.out.println("Integer array:" + numbers3);//Note it's an Integer[] array

        Set<String> noVowelSet = noVowels()
                .collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining:" + result);

        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas:" + result);

        IntSummaryStatistics summaryStatistics = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWorldLength = summaryStatistics.getAverage();
        double maxWorldLength = summaryStatistics.getMax();
        System.out.println("Average word length:" + averageWorldLength);
        System.out.println("Max word length: " + maxWorldLength);
        System.out.println("forEach:");

        noVowels().limit(10).forEach(System.out::println);

    }

    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + ":" + set.getClass().getName());
        System.out.println("[" +
                set.stream().limit(10).map(Object::toString)
                        .collect(Collectors.joining(",")) + "]");

    }

    public static Stream<String> noVowels() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("resources/text/cities.txt")),
                StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }


}
