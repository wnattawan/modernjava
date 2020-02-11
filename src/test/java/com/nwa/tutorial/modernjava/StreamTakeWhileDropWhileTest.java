package com.nwa.tutorial.modernjava;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTakeWhileDropWhileTest {
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

    @Test
    /**
     * When working with an unordered data structure
     * if some of the elements in the stream match the predicate (but not all)
     * then the operation is nondeterministic and an arbitrary subset of matching elements is returned or removed.
     */
    public void testTakeWhile_withUnOrderedData() {
        Set<Integer> numbers = Set.of(2, 12, 6, 38, 10, 9, 4, 18);
        final var result = numbers.stream()
                .sequential()
                .takeWhile(n -> n % 2 == 0)
                .collect(toList());
        System.out.println("result: " + result);
    }
}
