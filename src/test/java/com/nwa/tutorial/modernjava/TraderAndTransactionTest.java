package com.nwa.tutorial.modernjava;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class TraderAndTransactionTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void findAllTransactionsIn2011OrderByValueIncreasingly() {
        List<Transaction> transactions = this.transactions.stream()
                .filter(tx -> tx.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        assertThat(transactions)
                .hasSize(2)
                .containsExactly(new Transaction(brian, 2011, 300), new Transaction(raoul, 2011, 400));
    }

    @Test
    public void findAllTraderWorkingCities() {
        Stream<String> workingCities = Stream.of(raoul, mario, alan, brian)
                .map(Trader::getCity)
                .distinct();

        assertThat(workingCities).hasSize(2)
                .contains("Cambridge", "Milan");
    }

    @Test
    public void findAllTradersFromCambridgeAndSortByName() {
        List<Trader> traders = Stream.of(raoul, mario, alan, brian)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        assertThat(traders).hasSize(3)
                .containsExactly(alan, brian, raoul);
    }

    @Test
    public void returnAStringOfAllTraderNamesAndSortAlphabetically() {
        String nameStr = Stream.of(raoul, mario, alan, brian)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (a,b) -> a + b);
        assertThat(nameStr).isEqualTo("AlanBrianMarioRaoul");
    }

    @Test
    public void areAnyTradersBasedInMilan() {
        boolean milanBased = Stream.of(raoul, mario, alan, brian)
                .anyMatch(t -> t.getCity().equals("Milan"));
        assertThat(milanBased).isTrue();
    }

    private class Trader{
        private final String name;
        private final String city;
        public Trader(String n, String c){
            this.name = n;
            this.city = c;
        }
        public String getName(){
            return this.name;
        }
        public String getCity(){
            return this.city;
        }
        public String toString(){
            return "Trader:"+this.name + " in " + this.city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Trader trader = (Trader) o;
            return name.equals(trader.name) &&
                    city.equals(trader.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, city);
        }
    }

    private class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;
        public Transaction(Trader trader, int year, int value){
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
        public Trader getTrader(){
            return this.trader;
        }
        public int getYear(){
            return this.year;
        }
        public int getValue(){
            return this.value;
        }
        public String toString(){
            return "{" + this.trader + ", " +
                    "year: "+this.year+", " +
                    "value:" + this.value +"}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Transaction)) return false;
            Transaction that = (Transaction) o;
            return getYear() == that.getYear() &&
                    getValue() == that.getValue() &&
                    getTrader().equals(that.getTrader());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getTrader(), getYear(), getValue());
        }
    }
}
