package hu.nye.progtech.torpedo.model;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "game_save")
public class CharMap {
    private int mapLength;
    private char[][] map;

    public CharMap(int mapLength, char[][] map) {
        this.mapLength = mapLength;
        this.map = map;
    }

    @Override
    public String toString() {
        return "charMap{" +
                "mapLength=" + mapLength +
                ", map=" + Arrays.toString(map) +
                '}';
    }

    public int getMapLength() {
        return mapLength;
    }

    public void setMapLength(int mapLength) {
        this.mapLength = mapLength;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public CharMap() {
    }

}
