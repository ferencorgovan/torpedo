package hu.nye.progtech.torpedo.service;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.ui.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameState {
    InputReader inputReader = new InputReader();
    CommandHandler commandHandler = new CommandHandler();
    MapGenerator mapGenerator = new MapGenerator();
    CharMap charMap = new CharMap();
    MapPrinter mapPrinter = new MapPrinter();
    MapVO playerMap = mapGenerator.generateMap(10);
    MapVO enemyMap = mapGenerator.generateMap(10);
    char[][] playerCharMap = charMap.generateMap(10);
    char[][] enemyCharMap = charMap.generateMap(10);
    private static final Logger LOGGER = LoggerFactory.getLogger(GameState.class);

    public void playGame() {
        LOGGER.info("Type help for commandlist");
        do {
            System.out.print("Enter a command: ");
            commandHandler.handleCommand(inputReader.readCommand(), enemyMap, enemyCharMap);
        } while (!inputReader.readCommand().equals("exit"));
    }
}
