package main.utils;

import main.structures.HuffmanTree;

public class HuffmanTreeViewer {

    private final HuffmanTree tree;
    private static final int TOPIC_TAB_DEFAULT = 2;

    public HuffmanTreeViewer(HuffmanTree tree) {
        this.tree = tree;
    }

    public String toTopics() {
        return toTopic(tree.root, 0);
    }

    private static String toTopic(HuffmanTree.Node node, int level) {
        if (node == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        Character character;
        if (node.getCharacter() != null) {
            character = node.getCharacter();
        } else {
            character = '~';
        }

        result.append(spaces(level * TOPIC_TAB_DEFAULT))
                .append("- ").append(node.getFrequency())
                .append(" | ").append(character)
                .append("\n");

        result.append(toTopic(node.getLeft(), level + 1));
        result.append(toTopic(node.getRight(), level + 1));

        return result.toString();
    }

    private static String spaces(int num) {
        return " ".repeat(Math.max(0, num));
    }
}
