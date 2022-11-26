package main.utils;

import main.contracts.Identifiable;
import main.structures.PriorityQueue;

import java.util.List;
public class PriorityQueueViewer<DataType extends Identifiable<Integer>> {

    private final PriorityQueue<DataType> priorityQueue;

    public PriorityQueueViewer(PriorityQueue<DataType> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public static int elementHeight(int order) throws IndexOutOfBoundsException {
        if (order <= 0) {
            throw new IndexOutOfBoundsException("A ordem do elemento precisa ser maior que zero.");
        }
        return (int) Math.floor(Math.log(order) / Math.log(2));
    }

    private static String spaces(int num) {
        return " ".repeat(Math.max(0, num));
    }

    private static int coefficient(int num) {
        if (num < 0) {
            return 0;
        }
        return (coefficient(num - 1) * 2 + 1);
    }

    public String toTree() {
        StringBuilder result = new StringBuilder();

        try {
            List<DataType> heap = this.priorityQueue.toList();

            final int blockSize = 5;
            final int maxHeight = elementHeight(heap.size());
            int currentHeight = elementHeight(heap.size());

            for (int i = heap.size() - 1; i >= 0; i--) {
                int newHeight = elementHeight(i + 1);
                int order = maxHeight - newHeight;
                DataType element = heap.get(i);

                if (newHeight != currentHeight) {
                    currentHeight = newHeight;
                    result.insert(0, spaces(coefficient(order - 2) * blockSize));
                    result.insert(0, "\n");
                }
                result.insert(0, "[" + String.format("%02d", element.getIdentifier()) + "s]" + spaces(coefficient(order) * blockSize));
            }
            result.insert(0, spaces(coefficient(maxHeight - 1) * blockSize));
        } catch (IndexOutOfBoundsException exception) {
            return "";
        }

        return result.toString();
    }

    public String toHeap() {
        StringBuilder result = new StringBuilder();

        for (DataType type : priorityQueue.toList()) {
            result.append(type.toString());
            result.append("\n");
        }

        return result.toString();
    }

    public String toTopics() {
        try {
            return this.toTopic(this.priorityQueue.toList(), 1);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    private String toTopic(List<DataType> list, int order) {
        StringBuilder result = new StringBuilder();

        int height = elementHeight(order);
        result.append(spaces(3 * height));
        result.append("- ");
        result.append(list.get(order - 1));

        int firstChildIndex = order * 2;
        int secondChild = firstChildIndex + 1;

        if (firstChildIndex <= list.size()) {
            result.append("\n");
            result.append(this.toTopic(list, firstChildIndex));

            if(secondChild <= list.size()) {
                result.append("\n");
                result.append(this.toTopic(list, secondChild));
            }
        }

        return result.toString();
    }
}
