package main.factories.utils;

public class ValueFactory {
    private static int nextIndex = 0;

    public static String next() {
        return from(getNextIndex());
    }

    public static String from(int i) {
        return "value" + i;
    }

    private static int getNextIndex() {
        return nextIndex++;
    }
}
