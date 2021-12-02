package hu.nye.progtech.torpedo.service;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.CoordinateException;
import hu.nye.progtech.torpedo.ui.CharMapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandler.class);
    CharMapPrinter charMapPrinter = new CharMapPrinter();

    public void handleCommand(String input, MapVO map, char[][] charMap) {
        String command = input.split(" ")[0].toLowerCase();

        switch (command) {
            case "name":
                try {
                    String name = input.split(" ")[1];
                    System.out.println("Welcome " + name);
                } catch (Exception e) {
                    LOGGER.error("Incorrect name!");
                }
                break;

            case "help":
                System.out.println("Usable commands: help - name *name* - shoot *coordinate* - exit");
                break;

            case "shoot":
                String coordinate = input.split(" ")[1].toUpperCase();
                int row = Integer.parseInt(String.valueOf(coordinate.charAt(1)));

                char column = coordinate.charAt(0);
                int c = column;
                try {
                    c -= 65;
                    if (c < 0 || c > 9 || row < 0 || row > 9) {
                        throw new CoordinateException("Invalid coordinate.");
                    }
                    if (map.getMap()[row][c] == true) {
                        charMap[row][c] = '+';
                    } else {
                        charMap[row][c] = 'X';
                    }
                    charMapPrinter.printMap(charMap);
                } catch (CoordinateException e) {
                    LOGGER.error("Exception occurred" + e);
                }
                break;

            default:
                LOGGER.info("Unknown command");
                break;
        }

    }
}
