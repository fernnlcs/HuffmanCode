package test.structures;

import main.factories.utils.KeyFactory;
import main.factories.utils.ValueFactory;
import main.structures.HashTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashTableTest {
    @Test
    void addFirstValue() {
        String googleURI = "www.google.com";
        String googleIP = "4.4.4.4";

        HashTable<String, String> table = new HashTable<>();
        table.add(googleURI, googleIP);

        Assertions.assertThat(table.find(("www.google.com")))
                .isEqualTo(("4.4.4.4"));
        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(1);
        Assertions.assertThat(table.getTableSize()).isEqualTo(1);
    }

    @Test
    void add4Values() {
        final int quantity = 4;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getTableSize()).isEqualTo(1);
    }

    @Test
    void add5Values() {
        final int quantity = 5;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add6Values() {
        final int quantity = 6;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add10Values() {
        final int quantity = 10;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add11Values() {
        final int quantity = 11;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add15Values() {
        final int quantity = 15;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add21Values() {
        final int quantity = 21;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add31Values() {
        final int quantity = 31;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add75Values() {
        final int quantity = 75;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void add100Values() {
        final int quantity = 100;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.next(), ValueFactory.next());
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

    @Test
    void update() {
        HashTable<String, String> table = new HashTable<>();

        table.add(KeyFactory.from(0), ValueFactory.from(0));
        Assertions.assertThat(table.find(KeyFactory.from(0))).isEqualTo(ValueFactory.from(0));

        table.add(KeyFactory.from(0), ValueFactory.from(1));
        Assertions.assertThat(table.find(KeyFactory.from(0))).isEqualTo(ValueFactory.from(1));
    }

    @Test
    void remove() {
        final int quantity = 11;
        HashTable<String, String> table = new HashTable<>();

        for (int i = 0; i < quantity; i++) {
            table.add(KeyFactory.from(i), ValueFactory.from(i));
        }

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);

        table.remove(KeyFactory.from(7));
        table.remove(KeyFactory.from(4));

        Assertions.assertThat(table.getQuantityOfElements()).isEqualTo(quantity - 2);
        Assertions.assertThat(table.getElementsPerTableRow())
                .isGreaterThanOrEqualTo(HashTable.MIN_ELEMENTS_PER_TABLE_ROW)
                .isLessThanOrEqualTo(HashTable.MAX_ELEMENTS_PER_TABLE_ROW);
    }

}
