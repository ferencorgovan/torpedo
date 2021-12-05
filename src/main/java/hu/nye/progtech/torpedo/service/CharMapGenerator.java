package hu.nye.progtech.torpedo.service;

import hu.nye.progtech.torpedo.model.CharMap;

public class CharMapGenerator {
    public CharMap generateMap(int mapLength) {
        char[][] map = new char[mapLength][mapLength];
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++) {
                map[i][j] = '_';
            }
        }
        return new CharMap(mapLength, map);
    }

}
