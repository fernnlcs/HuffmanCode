package main.structures;

import main.utils.dictionary.Item;

import java.util.*;

public class HashTable<Key extends Comparable<Key>, Value> {

    private class Node {
        private final Item<Key, Value> item;
        private int frequency = 0;

        public Node(Key key, Value value) {
            this.item = new Item<>(key, value);
        }

        // TODO: verificar se o retorno deveria ser um clone
        public Item<Key, Value> getItem() {
            return this.item;
        }

        public Key getKey() {
            return this.item.getKey();
        }

        public Value getValue() {
            this.frequency++;
            return this.item.getValue();
        }

        public void setValue(Value value) {
            this.item.setValue(value);
            this.frequency++;
        }

        public int getFrequency() {
            return this.frequency;
        }

        public String toString() {
            return this.getKey().toString();
        }
    }

    private class Table {
        private final LinkedList<Node>[] data;
        private final Comparator<Node> comparator =
                (o1, o2) -> Integer.compare(o1.getFrequency(), o2.getFrequency());

        public Table(int size) {
            this.data = new LinkedList[size];

            for (int i = 0; i < size; i++) {
                this.data[i] = new LinkedList<>();
            }
        }

        public int size() {
            return data.length;
        }

        public void add(Node node) {
            this.getPageOf(node.getKey()).add(node);
        }

        private Node findNode(Key key) {
            return this.findInPage(this.getPageOf(key), key);
        }

        public Value find(Key key) throws NoSuchElementException {
            return this.findNode(key).getValue();
        }

        public Node findInPage(LinkedList<Node> page, Key key) throws NoSuchElementException {
            for (Node node : page) {
                if (node.getKey().equals(key)) {
                    return node;
                }
            }
            throw new NoSuchElementException("Chave não encontrada.");
        }

        public int pageOf(Key key) {
            int identifier = Math.abs(key.hashCode());
            int size = this.size();
            return identifier % size;
        }

        public LinkedList<Node> getPageOf(Key key) {
            int page = this.pageOf(key);
            return this.data[page];
        }

        public LinkedList<Node> getPage(int index) {
            return this.data[index];
        }

        public void sortPage(LinkedList<Node> page) {
            page.sort(this.comparator);
        }

        public void sortPageOf(Key key) {
            this.sortPage(this.getPageOf(key));
        }
    }

    private Table table = new Table(1);
    private int size = 0;

    public static final int MIN_ELEMENTS_PER_TABLE_ROW = 5;
    public static final int MAX_ELEMENTS_PER_TABLE_ROW = 10;

    public HashTable() {
    }

    public int getQuantityOfElements() {
        return this.size;
    }

    public int getTableSize() {
        return this.table.size();
    }

    public float getElementsPerTableRow() {
        return (float) this.getQuantityOfElements() / this.getTableSize();
    }

    private void adjustIfNecessary() {
        int size = this.getQuantityOfElements();
        if (size == 0 || size > MIN_ELEMENTS_PER_TABLE_ROW) {
            float average = this.getElementsPerTableRow();
            if (average < MIN_ELEMENTS_PER_TABLE_ROW || average > MAX_ELEMENTS_PER_TABLE_ROW) {
                this.adjust();
            }
        }
    }

    private void adjust() {
        int newSize = (int) Math.ceil((float)this.getQuantityOfElements() / MAX_ELEMENTS_PER_TABLE_ROW);
        // TODO encontrar número primo mais próximo

        Table newTable = new Table(newSize);

        for (int i = 0; i < this.getTableSize(); i++) {
            LinkedList<Node> currentPage = this.table.getPage(i);
            for (Node currentNode : currentPage) {
                newTable.add(currentNode);
            }
        }

        this.table = newTable;
    }

    private void add(Node node) {
        // Descobrir onde vai ficar o valor
        LinkedList<Node> page = this.table.getPageOf(node.getKey());

        if (page.add(new Node(node.getKey(), node.getValue()))) {
            // Incrementar o tamanho geral (N)
            this.size++;
        }
    }

    public void add(Key key, Value value) {
        try {
            // Atualizar valor existente (incrementando a frequência)
            Node node = this.table.findNode(key);
            node.setValue(value);

            // Reordenar página
            this.table.sortPageOf(key);
        } catch (NoSuchElementException e) {
            this.add(new Node(key, value));
            this.adjustIfNecessary();
        }
    }

    public Value find(Key key) throws NoSuchElementException {
        Value value = this.table.find(key);
        this.table.sortPageOf(key);
        return value;
    }

    public void remove(Key key) throws NoSuchElementException {
        Node node = this.table.findNode(key);
        this.table.getPageOf(key).remove(node);

        // Decrementar tamanho
        this.size--;

        // Reordenar página
        this.table.sortPageOf(key);

        // Ajustar tabela, se necessário
        this.adjustIfNecessary();
    }

    public LinkedList<Node> getPage(int index) {
        return this.table.getPage(index);
    }

    public List<Item<Key, Value>> toList() {
        List<Item<Key, Value>> result = new ArrayList<>();
        for (LinkedList<Node> list : this.table.data) {
            for (Node node : list) {
                result.add(node.getItem());
            }
        }
        return result;
    }
}
