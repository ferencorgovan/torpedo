package hu.nye.progtech.torpedo.service;

public class MapConverter {
    public String convertMap(char[][] currentMap) {
        StringBuilder builder = new StringBuilder();

        for (char[] chars : currentMap) {
            for (int j = 0; j < currentMap.length; j++) {
                builder.append(chars[j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public char[][] reConvert(String stringMap) {
        char[][] loadedMap = new char[10][10];
        String[] splitMap = stringMap.split("\n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                loadedMap[i][j] = splitMap[i].charAt(j);
            }
        }
        return loadedMap;
    }
}
