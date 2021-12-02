package hu.nye.progtech.torpedo.service;

import java.util.Scanner;

public class InputReader {

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
