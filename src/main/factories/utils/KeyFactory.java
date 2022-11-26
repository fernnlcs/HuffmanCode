package main.factories.utils;

public class KeyFactory {
    private static int nextIndex = 0;

    public static String next() {
        return from(getNextIndex());
    }

    public static String from(int i) {
        return "key" + i;
    }

    private static int getNextIndex() {
        return nextIndex++;
    }
}
