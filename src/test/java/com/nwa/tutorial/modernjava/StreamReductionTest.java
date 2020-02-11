package com.nwa.tutorial.modernjava;

import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamReductionTest {
    @Test
    public void testSummation() {
        final var sum = IntStream.range(0, 10)
                .sum();
        System.out.println("sum of 0-9: " + sum);
    }

    @Test
    public void testSummationReduction() {
        final var sum = IntStream.range(0, 10)
                .reduce(0, (a, b) -> a + b);
        System.out.println("sum of 0-9: " + sum);
    }

    @Test
    public void testMuliplicity() {
        final var multiplicity = Stream.of(1, 2, 3, 4, 5)
                .reduce(1, (a, b) -> a * b);
        System.out.println("1 * 2 * 3 * 4 * 5 = " + multiplicity);
    }

    @Test
    public void testSummationReduce2() {
        final var sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(0, Integer::sum);
        System.out.println("1 + 2 + 3 + 4 + 5: " + sum);
    }

    @Test
    public void findMaximumReduction() {
        Stream.of(10, 30, 50, 20, 40)
                .reduce((a,b) -> a <= b ? b : a)
                .ifPresentOrElse(
                        max -> System.out.println("max: " + max),
                        () -> System.out.println("No data")
                );
    }

    @Test
    public void testMaximumReduction2() {
        Stream.of(10, 30, 50, 20, 40)
                .reduce(Integer::max)
                .ifPresentOrElse(
                        max -> System.out.println("max: " + max),
                        () -> System.out.println("No data")
                );
    }
}
