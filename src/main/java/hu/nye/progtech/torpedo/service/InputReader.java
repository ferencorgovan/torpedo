package hu.nye.progtech.torpedo.service;

import java.util.Scanner;

public class InputReader {
    public Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }
}
