package com.nwa.tutorial.modernjava;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class Stream9Test {
    @Test
    public void testTakeWhile() {
        System.out.println("data: 2, 4, 5, 6, 8");
        System.out.println("Include while n % 2 == 0");
        Stream.of(2, 4, 5, 6, 8)
                .takeWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    @Test
    public void testDropWhile() {
        System.out.println("data: 2, 4, 5, 6, 8");
        System.out.println("Exclude while n % 2 == 0");
        Stream.of(2, 4, 5, 6, 8)
                .dropWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}
