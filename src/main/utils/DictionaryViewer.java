package main.utils;

import main.utils.dictionary.Dictionary;
import main.utils.dictionary.Item;

import java.util.List;
import java.util.function.Function;

public class DictionaryViewer<Key extends Comparable<Key>, Value> {

    private final Dictionary<Key, Value> dictionary;
    private final Function<Value, String> valueTransformer;

    public DictionaryViewer(Dictionary<Key, Value> dictionary) {
        this.dictionary = dictionary;
        this.valueTransformer = Object::toString;
    }

    public DictionaryViewer(Dictionary<Key, Value> dictionary, Function<Value, String> valueTransformer) {
        this.dictionary = dictionary;
        this.valueTransformer = valueTransformer;
    }

    public String view() {
        StringBuilder result = new StringBuilder();
        List<Item<Key, Value>> list = dictionary.toList();

        for (Item<Key, Value> item : list) {
            result.append(item.getKey().toString()).append(" | ")
                    .append(this.valueTransformer.apply(item.getValue()));
            result.append("\n");
        }

        return result.toString();
    }
}
