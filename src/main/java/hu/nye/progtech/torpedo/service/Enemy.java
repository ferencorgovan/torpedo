package hu.nye.progtech.torpedo.service;

import java.util.Random;

import hu.nye.progtech.torpedo.model.CharMap;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.ui.CharMapPrinter;

public class Enemy {
    MapGenerator mapGenerator = new MapGenerator();
    CharMapGenerator charMapGenerator = new CharMapGenerator();
    MapVO playerMap = mapGenerator.generateMap(10);
    CharMap playerCharMap = charMapGenerator.generateMap(10);
    CharMapPrinter charMapPrinter = new CharMapPrinter();
    Random random = new Random();

    public void move() {
        int row;
        int column;
        do {
            row = random.nextInt(9);
            column = random.nextInt(9);
        } while ((playerCharMap.getMap()[row][column] == '+' || playerCharMap.getMap()[row][column] == 'X'));
        System.out.println("Enemy shot at " + (char) (column + 65) + "" + row);

        if (playerMap.getMap()[row][column]) {
            playerCharMap.getMap()[row][column] = '+';
            int hit = playerMap.getHit();
            playerMap.setHit(hit + 1);
            System.out.println("A ship has been hit!\nHits: " + playerMap.getHit());
        } else {
            playerCharMap.getMap()[row][column] = 'X';
        }

        charMapPrinter.printMap(playerCharMap.getMap());
        if (playerMap.getHit() == 15) {
            System.out.println("Enemy won.");
            System.exit(0);
        }
    }
}
