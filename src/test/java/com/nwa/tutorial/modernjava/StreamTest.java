package com.nwa.tutorial.modernjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {
    List<Dish> menu = new ArrayList<>();

    private class Dish {
        private String name;
        private int calories;
        private boolean vego;
        private Type type;

        public Dish(String name, boolean vego, int calories, Type type) {
            this.name = name;
            this.vego = vego;
            this.calories = calories;
            this.type = type;
        }

        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }

        public boolean isVego() {
            return vego;
        }

        public Type getType() {
            return type;
        }
    }

    private enum Type {
        MEAT, FISH, OTHER;
    }

    @BeforeEach
    public void setUp() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH) );
    }

    @Test
    public void listLowCaloricDishes() {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish dish: menu) {
            if(dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish dish1, Dish dish2) {
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish: lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }

        assertThat(lowCaloricDishesName).containsExactly("season fruit", "prawns", "rice");
    }

    @Test
    public void listLowCaloricDishes_solution1() {
        final var lowCaloricDishesName = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted((dish1, dish2) -> dish1.getCalories() <= dish2.getCalories() ? -1 : 1)
                .map(Dish::getName)
                .collect(toList());
        assertThat(lowCaloricDishesName).containsExactly("season fruit", "prawns", "rice");
    }

    @Test
    public void listLowCaloricDishes_solution2() {
        final var lowCaloricDishesName = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        assertThat(lowCaloricDishesName).containsExactly("season fruit", "prawns", "rice");
    }

    @Test
    public void listLowCaloricDishesGroupedByType() {
        final var lowCaloricDishesNameByType = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .collect(groupingBy(Dish::getType));

        assertThat(lowCaloricDishesNameByType).containsOnlyKeys(Type.FISH, Type.OTHER);
        lowCaloricDishesNameByType.get(Type.FISH)
                .stream()
                .map(Dish::getName)
                .collect(toList())
                .containsAll(List.of("prawns"));
        lowCaloricDishesNameByType.get(Type.OTHER)
                .stream()
                .map(Dish::getName)
                .collect(toList())
                .containsAll(List.of("season fruit", "rice"));
    }
}
