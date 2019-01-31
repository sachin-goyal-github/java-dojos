package com.sg.java8;

import java.util.function.Function;

/**
 * 1). Any interface with single method is treated as a functional interface
 * 2). Functional interfaces can be assigned a method reference or lambda expression
 * 3). There are helper methods in java.util.function so that you don't have to create too many functional interfaces
 */
public class LambdaTester {

    static interface NamePrinter {
        void print(String name);
    }

    static String appendDollar(String input) {
        return  input + "$";
    }

    static String appendPound(String input) {
        return  input + "£";
    }

    static String stripPounds(String input) {
        return input.replaceAll("£", "");
    }

    static String stripDollars(String input) {
        return input.replaceAll("\\$", "");
    }

    static String cleanValues(String input, Function<String, String> cleaningAlgo) {
        return cleaningAlgo.apply(input) + " performed cleaning";
    }

    public static void main(String[] args) {
        // lambda expression using an anonymous inner class using an interface with single method
        final NamePrinter namePrinter1 = name -> System.out.println(name);
        namePrinter1.print("Sachin");

        // Assigning lambda to a function interface
        Function<String, String> stripPoundsOffFunc = (input) -> input.replaceAll("£", "");
        System.out.println(stripPoundsOffFunc.apply("Sachin has £2000"));

        // Using method references
        Function<String, String> appendDollarFunc = LambdaTester::appendDollar;
        Function<String, String> appendPoundFunc = LambdaTester::appendPound;

        System.out.println(appendDollarFunc.apply("Sachin"));
        System.out.println(appendPoundFunc.apply("Sachin"));

        // Passing method references
        System.out.println(cleanValues("Sachin has $1000", LambdaTester::stripDollars));
        System.out.println(cleanValues("Sachin has £1000", LambdaTester::stripPounds));
    }
}
