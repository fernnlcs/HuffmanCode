package main.structures;

import main.contracts.Identifiable;
import main.utils.SortMode;
import main.utils.dictionary.Dictionary;
import main.utils.dictionary.Item;

public class HuffmanTree {

    public static class Node implements Identifiable<Integer> {
        private final Item<Character, Integer> item;
        private Node left = null;
        private Node right = null;

        public Node(Item<Character, Integer> item) {
            this.item = item;
        }

        public Node(Character character, int frequency) {
            this.item = new Item<>(character, frequency);
        }

        public Character getCharacter() {
            return this.item.getKey();
        }

        public int getFrequency() {
            return this.item.getValue();
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public Integer getIdentifier() {
            return this.item.getValue();
        }
    }

    public final Node root;
    public final Dictionary<Character, Integer> dictionary;

    public HuffmanTree(Dictionary<Character, Integer> frequencyDictionary) {
        PriorityQueue<Node> queue = new PriorityQueue<>(SortMode.DESCENDING);

        for (Item<Character, Integer> item : frequencyDictionary.toList()) {
            Node node = new Node(item);
            queue.add(node);
        }

        if (queue.size() == 1) {
            Node firstChild = queue.remove();
            Node z = new Node(null, firstChild.getFrequency());

            z.setLeft(firstChild);

            queue.add(z);
        }

        while (queue.size() > 1) {
            Node firstChild = queue.remove();
            Node secondChild = queue.remove();
            Node z = new Node(null, firstChild.getFrequency() + secondChild.getFrequency());

            z.setLeft(firstChild);
            z.setRight(secondChild);

            queue.add(z);
        }

        this.root = queue.remove();
        this.dictionary = new Dictionary<>();
        this.buildDictionary();
    }

    public Dictionary<Character, Integer> getDictionary() {
        return this.dictionary;
    }

    private void buildDictionary() {
        this.buildDictionary(this.root, "");
    }

    private void buildDictionary(Node node, String path) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null && node.getCharacter() != null) {
            this.getDictionary().add(node.getCharacter(), Integer.parseInt(path, 2));
        }

        this.buildDictionary(node.getLeft(), path + "0");
        this.buildDictionary(node.getRight(), path + "1");
    }
}
