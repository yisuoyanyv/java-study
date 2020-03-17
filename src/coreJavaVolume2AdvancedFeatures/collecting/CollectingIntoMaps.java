package coreJavaVolume2AdvancedFeatures.collecting;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangjinglong
 * @date 2020-03-16-11:37 上午
 */

public class CollectingIntoMaps {

    public static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static Stream<Person> people() {
        return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"),
                new Person(1003, "Mary")
        );

    }

    public static void main(String[] args) throws IOException {
        Map<Integer, String> idToName = people().collect(
                Collectors.toMap(Person::getId, Person::getName)
        );
        System.out.println("idToName:" + idToName);
        Map<Integer, Person> idToPerson = people().collect(
                Collectors.toMap(Person::getId, Function.identity())
        );
        System.out.println("idToPerson:" + idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(
                Collectors.toMap(Person::getId, Function.identity(), (existingValue, newValue) -> {
                    throw new IllegalStateException();
                }, TreeMap::new)
        );
        System.out.println("idToPerson:" + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locals = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locals.collect(
                Collectors.toMap(Locale::getDisplayName,
                        l -> l.getDisplayName(l),
                        (existingValue, newValue) -> existingValue)

        );
        System.out.println("languageNames: " + languageNames);

        locals = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> contryLanguagesSets = locals.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> {
                            //union of a and b
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }
                )
        );

        System.out.println("contryLauguagesSets: " + contryLanguagesSets);


    }
}
