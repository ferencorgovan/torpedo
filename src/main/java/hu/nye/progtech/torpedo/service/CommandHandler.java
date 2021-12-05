package hu.nye.progtech.torpedo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import hu.nye.progtech.torpedo.model.CharMap;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.Player;
import hu.nye.progtech.torpedo.persistence.GameSaveRepository;
import hu.nye.progtech.torpedo.persistence.PlayerScoreRepository;
import hu.nye.progtech.torpedo.persistence.XmlGameSaveRepository;
import hu.nye.progtech.torpedo.service.exception.CoordinateException;
import hu.nye.progtech.torpedo.ui.CharMapPrinter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandler.class);
    CharMapPrinter charMapPrinter = new CharMapPrinter();
    Connection connection;
    //private GameSaveRepository gameSaveRepository;

    {
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/./progtech",
                    "sa", "password");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final PlayerScoreRepository playerScoreRepository = new PlayerScoreRepository(connection);

    JAXBContext jaxbContext;

    {
        try {
            jaxbContext = JAXBContext.newInstance(CharMap.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    Marshaller marshaller;

    {
        try {
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    Unmarshaller unmarshaller;

    {
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private final GameSaveRepository gameSaveRepository = new XmlGameSaveRepository(marshaller, unmarshaller);
    Enemy enemy = new Enemy();
    Player player = new Player("Player", 0);

    public void handleCommand(String input, MapVO map, CharMap charMap) {
        String command = input.split(" ")[0].toLowerCase();

        switch (command) {
            case "name":
                try {
                    String name = input.split(" ")[1];
                    player.setName(name);
                    System.out.println("Player name changed to " + name);
                } catch (Exception e) {
                    LOGGER.error("Incorrect name!");
                    System.out.println("Incorrect name!");
                }
                break;

            case "help":
                System.out.println("Usable commands:\n- help\n- name *name*" +
                        "\n- save\n- load" +
                        "\n- shoot *coordinate*\n- exit");
                break;

            case "shoot":
                try {
                    String coordinate = input.split(" ")[1].toUpperCase();

                    int row = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
                    int column = coordinate.charAt(0);

                    column -= 65;
                    if (input.split(" ")[1].length() != 2 ||
                            column < 0 || column > 9 || row < 0 || row > 9) {
                        throw new CoordinateException("Invalid coordinate.");
                    }
                    if (charMap.getMap()[row][column] == '+' || charMap.getMap()[row][column] == 'X') {
                        System.out.println("Already shot.");
                    } else {
                        if (map.getMap()[row][column]) {
                            charMap.getMap()[row][column] = '+';
                            int hit = map.getHit();
                            map.setHit(hit + 1);
                            System.out.println("A ship has been hit!\nHits: " + map.getHit());
                        } else {
                            charMap.getMap()[row][column] = 'X';
                        }
                        charMapPrinter.printMap(charMap.getMap());
                        if (map.getHit() == 15) {
                            System.out.println(player.getName() + " won.");
                            player.setWins(player.getWins() + 1);
                            System.exit(0);
                        }
                        enemy.move();
                    }
                } catch (CoordinateException | IndexOutOfBoundsException
                        | NumberFormatException e) {
                    LOGGER.error("Exception occurred" + e);
                    System.out.println("Invalid coordinate");
                }

                break;

            case "save":
                gameSaveRepository.save(charMap);
                LOGGER.info("Gamestate saved.");
                break;

            case "load":
                charMap.setMap(gameSaveRepository.load().getMap());
                LOGGER.info("Gamestate loaded.");
                break;

            case "rs":
                try {
                    playerScoreRepository.update(player.getName(), player.getWins());
                    System.out.println("O");
                } catch (SQLException throwables) {
                    System.out.println("x");
                    throwables.printStackTrace();
                }

                break;

            case "scoreboard":
                ArrayList<Player> scoreboard = new ArrayList<>(playerScoreRepository.players());
                for (Player player :
                scoreboard) {
                    System.out.println(player.getName() + "\tWins: " + player.getWins());
                }
                break;

            case "exit":
            System.exit(0);
            break;

            default:
                System.out.println("Unknown command");
                LOGGER.info("Unknown command");
                break;
        }

    }
}
