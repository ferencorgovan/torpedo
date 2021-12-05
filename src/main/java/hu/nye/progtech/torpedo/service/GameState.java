package hu.nye.progtech.torpedo.service;

import hu.nye.progtech.torpedo.model.CharMap;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.ui.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameState {
    InputReader inputReader = new InputReader();
    CommandHandler commandHandler = new CommandHandler();
    MapGenerator mapGenerator = new MapGenerator();
    CharMapGenerator charMapGenerator = new CharMapGenerator();
    MapPrinter mapPrinter = new MapPrinter();

    MapVO enemyMap = mapGenerator.generateMap(10);

    CharMap enemyCharMap = charMapGenerator.generateMap(10);
    private static final Logger LOGGER = LoggerFactory.getLogger(GameState.class);

    public void playGame() {
        String input;
        System.out.println("Game started! Type 'help' for commandlist.");
        do {
            System.out.print("Enter a command: ");
            input = inputReader.readCommand();
            commandHandler.handleCommand(input, enemyMap, enemyCharMap);
        } while (!input.equals("exit"));
    }
}
