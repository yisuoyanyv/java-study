package coreJavaVolumeFundamentals.enums;

import java.util.Scanner;

/**
 * @author zhangjinglong
 * @date 2020-02-27-9:27 下午
 */

public class EnumTest {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter a Size:(SMALL,MEDIUM,LARGE,EXTRA_LARGE");
        String input=in.next().toUpperCase();
        Size size=Enum.valueOf(Size.class,input);
        System.out.println("Size="+size);
        System.out.println("abbreviation="+size.getAbbreviation());
        if(size==Size.EXTRA_LARGE){
            System.out.println("Good Job-- you paid attentions to the _.");
        }
    }
}


enum Size{
    SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
    private Size(String avvreviation){
        this.abbreviation=avvreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    private String abbreviation;
}