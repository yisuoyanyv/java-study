package coreJavaVolumeFundamentals.treeSet;

import java.util.Objects;

/**
 * @author zhangjinglong
 * @date 2020-03-08-9:30 下午
 * An item with a description and a part number
 */

public class Item  implements Comparable<Item>{
    private String description;
    private int parNumber;

    /**
     * Constructs an item
     * @param aDescription
     * @param aPartNumber
     */
    public Item(String aDescription,int aPartNumber){
        description=aDescription;
        parNumber=aPartNumber;
    }

    /**
     * Gets the description of this item.
     * @return the description
     */
    public int getParNumber() {
        return parNumber;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", parNumber=" + parNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return parNumber == item.parNumber &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, parNumber);
    }

    @Override
    public int compareTo(Item o) {
        int diff=Integer.compare(parNumber,o.parNumber);
        return diff!=0? diff:description.compareTo(o.description);
    }
}
