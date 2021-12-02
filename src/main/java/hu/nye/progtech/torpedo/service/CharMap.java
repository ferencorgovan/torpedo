package hu.nye.progtech.torpedo.service;

public class CharMap {
    public char[][] generateMap(int mapLength) {
        char[][] map = new char[mapLength][mapLength];
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++) {
                map[i][j] = '_';
            }
        }
        return map;
    }

}
