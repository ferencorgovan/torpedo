package hu.nye.progtech.torpedo.model;

import java.util.Arrays;
import java.util.Objects;

public final class MapVO {

    private final int mapLength;
    private final boolean[][] map;


    public MapVO(int mapLength, boolean[][] map) {
        this.mapLength = mapLength;
        this.map = deepCopy(map);
    }

    public int getMapLength() {
        return mapLength;
    }

    public boolean[][] getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapVO mapVO = (MapVO) o;
        return mapLength == mapVO.mapLength && Arrays.equals(map, mapVO.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mapLength);
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }

    @Override
    public String toString() {
        return "MapVO{" +
                "mapLength=" + mapLength +
                ", map=" + Arrays.deepToString(map) +
                '}';
    }

    private boolean[][] deepCopy(boolean[][] array) {
        boolean[][] result = null;

        if (array != null) {
            result = new boolean[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }
}

