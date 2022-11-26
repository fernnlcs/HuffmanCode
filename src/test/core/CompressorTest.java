package test.core;

import main.core.Compressor;
import main.utils.dictionary.Dictionary;
import main.utils.dictionary.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompressorTest {

    @Test
    void create() {
        Compressor compressor = new Compressor("abaccda");

        Dictionary<Character, Integer> dictionary = new Dictionary<>();
        dictionary.add('a', Integer.parseInt("0", 2));
        dictionary.add('b', Integer.parseInt("0", 2));
        dictionary.add('c', Integer.parseInt("0", 2));
        dictionary.add('d', Integer.parseInt("0", 2));

        Assertions.assertThat(compressor.getTree().getDictionary().toList()).containsExactlyInAnyOrder(
                new Item<>('a', Integer.parseInt("0", 2))
        );
    }
}
