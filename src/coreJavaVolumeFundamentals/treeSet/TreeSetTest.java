package coreJavaVolumeFundamentals.treeSet;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author zhangjinglong
 * @date 2020-03-08-9:29 下午
 */

public class TreeSetTest {

    public static void main(String[] args) {
        SortedSet<Item> parts=new TreeSet<>();
//        parts.add(new Item("totile",1245));
//        parts.add(new Item("zjl",1234));
//        parts.add(new Item("model",331));

        System.out.println(parts);

        NavigableSet<Item> sortByDescription=new TreeSet<>(
                Comparator.comparing(Item::getDescription));
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}
