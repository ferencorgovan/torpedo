package hu.nye.progtech.torpedo.service;

import java.util.Random;

import hu.nye.progtech.torpedo.model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapGenerator.class);

    public MapVO generateMap(int mapLength) {
        boolean[][] map = new boolean[mapLength][mapLength];
        Random r = new Random();

        LOGGER.info("Generating map");
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++) {
                map[i][j] = false;
            }
        }
        for (int i = 5; i > 0; i--) {
            boolean horizontal = r.nextBoolean();
            int rowNumber = r.nextInt(mapLength);
            int columnNumber = r.nextInt(mapLength);
            boolean free = true;
            if (horizontal) {
                if (columnNumber + i > mapLength) {
                    columnNumber -= i;
                }
            } else if (rowNumber + i > mapLength) {
                rowNumber -= i;
            }
            ShipValidator shipValidator = new ShipValidator(map, rowNumber, columnNumber);
            int index = horizontal ? columnNumber : rowNumber;

            for (int j = index; j < index + i; j++) {
                if (horizontal ? map[rowNumber][j] : map[j][columnNumber]) {
                    free = false;
                    break;
                }
            }
            if (rowNumber == 0 && columnNumber == 0) {
                if (!shipValidator.right() ||
                        !shipValidator.down() ||
                        !shipValidator.bottomRight()) {
                    free = false;
                }
            }
            if (rowNumber == 0 && (columnNumber > 0 && columnNumber < 9)) {
                if (!shipValidator.left() ||
                        !shipValidator.right() ||
                        !shipValidator.bottomLeft() ||
                        !shipValidator.down() ||
                        !shipValidator.bottomRight()) {
                    free = false;
                }
            }
            if (rowNumber == 0 && columnNumber == 9) {
                if (!shipValidator.left() ||
                        !shipValidator.bottomLeft() ||
                        !shipValidator.down()) {
                    free = false;
                }
            }

            if (rowNumber > 0 && rowNumber < 9 && columnNumber == 9) {
                if (!shipValidator.upperLeft() ||
                        !shipValidator.up() ||
                        !shipValidator.left() ||
                        !shipValidator.bottomLeft() ||
                        !shipValidator.down()) {
                    free = false;
                }
            }

            if (rowNumber == 9 && columnNumber == 9) {
                if (!shipValidator.upperLeft() ||
                        !shipValidator.up() ||
                        !shipValidator.left()) {
                    free = false;
                }
            }

            if (rowNumber == 9 && columnNumber > 0 && columnNumber < 9) {
                if (!shipValidator.upperLeft() ||
                        !shipValidator.up() ||
                        !shipValidator.upperRight() ||
                        !shipValidator.left()) {
                    free = false;
                }
            }

            if (rowNumber == 9 && columnNumber == 0) {
                if (!shipValidator.up() ||
                        !shipValidator.upperRight() ||
                        !shipValidator.right()) {
                    free = false;
                }
            }

            if (rowNumber > 0 && rowNumber < 9 && columnNumber == 0) {
                if (!shipValidator.up() ||
                        !shipValidator.upperRight() ||
                        !shipValidator.right() ||
                        !shipValidator.down() ||
                        !shipValidator.bottomRight()) {
                    free = false;
                }
            }

            if (!free) {
                i++;
            } else {
                if (horizontal) {
                    for (int j = columnNumber; j < columnNumber + i; j++) {
                        map[rowNumber][j] = true;
                    }
                } else {
                    for (int j = rowNumber; j < rowNumber + i; j++) {
                        map[j][columnNumber] = true;
                    }
                }
            }
        }

        return new MapVO(mapLength, map);
    }
}
