package com.nwa.tutorial.modernjava;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamFlatmapTest {
    @Test
    public void findAllIndividualLetters() {
        List<String> individualLetters = Stream.of("Hello", "World")
                .map( word -> word.split(""))
                .flatMap(Arrays::stream)
                .map(letter -> letter.toLowerCase())
                .collect(toList());
        System.out.println("All individual letters (Hello World): " + individualLetters);
    }

    @Test
    public void findAllUniqueLetters() {
        List<String> uniqueLetters = Stream.of("Hello", "World")
                .map( word -> word.split(""))
                .flatMap(Arrays::stream)
                .map(letter -> letter.toLowerCase())
                .distinct()
                .collect(toList());
        System.out.println("All unique letters (Hello World): " + uniqueLetters);
    }

    @Test
    public void findSquareNumbers() {
        final var squareNumbers = Stream.of(1, 2, 3, 4, 5)
                .map(n -> n * n)
                .collect(toList());
        System.out.println("Square numbers of 1, 2, 3, 4, 5: " + squareNumbers);
    }

    @Test
    public void findPairOfNumbers() {
        final var list1 = List.of(1, 2, 3);
        final var list2 = List.of(3, 4);
        list1.stream()
                .flatMap(i -> list2.stream().map(j -> new int[]{i, j}))
                .forEach( pair -> {
                    System.out.print("(" + pair[0] + "," + pair[1] + ")");
                });
    }

    @Test
    public void findPairOfNumbersDivisibleByThree() {
        final var list1 = List.of(1, 2, 3);
        final var list2 = List.of(3, 4);
        list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i+j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .forEach( pair -> {
                    System.out.print("(" + pair[0] + "," + pair[1] + ")");
                });
    }

}
