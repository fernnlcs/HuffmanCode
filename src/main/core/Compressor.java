package main.core;

import main.structures.HuffmanTree;
import main.utils.dictionary.Dictionary;

import java.util.NoSuchElementException;

public class Compressor {

    private final Dictionary<Character, Integer> frequencyDictionary;
    private final HuffmanTree tree;
    private final String original;

    public Compressor(String text) {
        if (text == null || text.equals("")) {
            throw new IllegalArgumentException("O texto não deve ser vazio.");
        }

        this.original = text;
        this.frequencyDictionary = new Dictionary<>();
        char[] characters = text.toCharArray();

        for (char character : characters) {
            try {
                int frequency = this.frequencyDictionary.get(character);
                this.frequencyDictionary.add(character, frequency + 1);
            } catch (NoSuchElementException e) {
                this.frequencyDictionary.add(character, 1);
            }
        }

        this.tree = new HuffmanTree(this.frequencyDictionary);
    }

    public HuffmanTree getTree() {
        return this.tree;
    }

    public Dictionary<Character, Integer> getFrequencyDictionary() {
        return this.frequencyDictionary;
    }

    public String getCode() {
        return this.getCodeWithSpaces().replace(" ", "");
    }

    public String getCodeWithSpaces() {
        StringBuilder result = new StringBuilder();
        char[] characters = this.original.toCharArray();

        for (char character : characters) {
            int path = this.getTree().getDictionary().get(character);
            result.append(Integer.toBinaryString(path)).append(" ");
        }

        return result.toString();
    }

    public int getCodeSize() {
        return this.getCode().length();
    }
}
