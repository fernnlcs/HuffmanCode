package main;

import main.core.Simulator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulator simulator = new Simulator();
        simulator.start(scanner);
    }
}
