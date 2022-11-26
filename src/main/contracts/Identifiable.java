package main.contracts;

public interface Identifiable<Type extends Comparable<Type>> extends Comparable<Identifiable<Type>> {
    Type getIdentifier();

    @Override
    default int compareTo(Identifiable<Type> other) {
        return this.getIdentifier().compareTo(other.getIdentifier());
    }
}
