package coreJavaVolume2AdvancedFeatures.collecting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author zhangjinglong
 * @date 2020-03-16-12:11 下午
 */

public class DownstreamCollectors {
    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public int getPopulation() {
            return population;
        }

        public String getState() {
            return state;
        }

    }

    public static Stream<City> readCites(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locals = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocalSet = locals.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocalSet: " + countryToLocalSet);

        locals = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locals.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts:" + countryToLocaleCounts);


        Stream<City> cities = readCites("resources/text/cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation:" + stateToCityPopulation);

        cities = readCites("resources/text/cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(
                groupingBy(City::getState
                        , mapping(City::getName, maxBy(Comparator.comparing(String::length))))
        );
        System.out.println("stateToLongestCityName:" + stateToLongestCityName);


        locals = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locals.collect(groupingBy(
                Locale::getDisplayCountry,
                mapping(Locale::getDisplayLanguage, toSet())
        ));
        System.out.println("countryToLanguages:" + countryToLanguages);

        cities = readCites("resources/text/cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(
                groupingBy(City::getState, summarizingInt(City::getPopulation))
        );
        System.out.println(stateToCityPopulationSummary.get("NY"));

        cities = readCites("resources/text/cities.txt");
        Map<String, String> stateToCityNames = cities.collect(
                groupingBy(City::getState, reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t))
        );

        cities = readCites("resources/text/cities.txt");
        stateToCityNames = cities.collect(groupingBy(City::getState,
                mapping(City::getName, joining(","))));
        System.out.println("stateToCityNames:" + stateToCityNames);
    }
}
