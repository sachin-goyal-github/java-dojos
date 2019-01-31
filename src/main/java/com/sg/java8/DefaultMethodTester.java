package com.sg.java8;

/**
 * Created by sachin on 08/10/14.
 */
public class DefaultMethodTester {

    static interface NameJoiner {
        String join(String first, String second);
        default String joinWithComma(String first, String second) {
            return first + ", " + second;
        }
    }

    static class NameJoinerImpl implements NameJoiner {
        @Override
        public String join(String first, String second) {
            return first + " " + second;
        }
    }

    public static void main(String[] args) {
        final NameJoinerImpl nameJoiner = new NameJoinerImpl();
        System.out.println(nameJoiner.join("Sachin", "Goyal"));
        System.out.println(nameJoiner.joinWithComma("Sachin", "Goyal"));
    }
}
