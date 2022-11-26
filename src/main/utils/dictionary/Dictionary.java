package main.utils.dictionary;

import main.structures.HashTable;

import java.util.List;

public class Dictionary<Key extends Comparable<Key>, Value> {

    private final HashTable<Key, Value> hashTable;

    public Dictionary() {
        hashTable = new HashTable<>();
    }

    public Dictionary(HashTable<Key, Value> table) {
        this.hashTable = table;
    }

    public void add(Key key, Value value) {
        this.hashTable.add(key, value);
    }

    public void add(Item<Key, Value> item) {
        Key key = item.getKey();
        Value value = item.getValue();
        this.hashTable.add(key, value);
    }

    public Value get(Key key) {
        return this.hashTable.find(key);
    }

    public Item<Key, Value> getItem(Key key) {
        Value value = this.hashTable.find(key);
        return new Item<>(key, value);
    }

    public void set(Key key, Value value) {
        this.add(key, value);
    }

    public void set(Item<Key, Value> item) {
        Key key = item.getKey();
        Value value = item.getValue();
        this.add(key, value);
    }

    public List<Item<Key, Value>> toList() {
        return this.hashTable.toList();
    }
}
