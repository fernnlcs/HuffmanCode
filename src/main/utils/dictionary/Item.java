package main.utils.dictionary;

public class Item<Key extends Comparable<Key>, Value> {
    private final Key key;
    private Value value;

    public Item(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
