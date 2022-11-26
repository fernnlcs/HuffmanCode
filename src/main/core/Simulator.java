package main.core;

import main.utils.DictionaryViewer;
import main.utils.HuffmanTreeViewer;

import java.util.Scanner;

public class Simulator {

    public String present() {
        return  """
                1 - Comprimir texto
                0 - Sair
                """;
    }

    public void start(Scanner scanner) {
        System.out.println(this.present());
        this.interact(scanner);
    }

    private void interact(Scanner scanner) {
        String input = scanner.nextLine();

        while (!input.equals("0")) {

            if (input.equals("1")) {
                this.compressText(scanner);
                System.out.println(this.present());
            } else {
                System.out.println("Opção desconhecida. Tente novamente.");
                System.out.println(this.present());
            }

            input = scanner.nextLine();
        }
    }

    private void compressText(Scanner scanner) {
        System.out.print("Digite o texto: ");
        String text = scanner.nextLine();

        System.out.println("\n\t\t ORIGINAL:");
        System.out.println("Texto: " + text);
        System.out.println("Código: " + binaryText(text));
        System.out.println("Tamanho: " + text.length() * Byte.SIZE + " bits");
        System.out.println();

        Compressor compressor = new Compressor(text);
        HuffmanTreeViewer treeViewer = new HuffmanTreeViewer(compressor.getTree());
        DictionaryViewer<Character, Integer> codeViewer =
                new DictionaryViewer<>(
                        compressor.getTree().getDictionary(),
                        Integer::toBinaryString);
        DictionaryViewer<Character, Integer> frequencyViewer =
                new DictionaryViewer<>(
                        compressor.getFrequencyDictionary(),
                        (Integer original) -> original.toString() + "x");

        System.out.println("\n\t\t COMPRIMIDO:");
        System.out.println("Código: " + compressor.getCodeWithSpaces());
        System.out.println("Tamanho: " + compressor.getCodeSize() + " bits");
        System.out.println("Taxa de compressão: " + ((float) compressor.getCodeSize() / (text.length() * Byte.SIZE)));
        System.out.println("Árvore (em formato de tópicos): \n" + treeViewer.toTopics());
        System.out.println("Tabela de codificação: \n" + codeViewer.view());
        System.out.println("Tabela de frequências: \n" + frequencyViewer.view());

        System.out.println();
    }

    private static String binaryText(String text) {
        StringBuilder result = new StringBuilder();
        char[] characters = text.toCharArray();

        for (char character : characters) {
            result.append(Integer.toBinaryString(character)).append(" ");
        }

        return result.toString();
    }
}
