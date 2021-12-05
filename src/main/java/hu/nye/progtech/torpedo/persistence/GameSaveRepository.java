package hu.nye.progtech.torpedo.persistence;

import hu.nye.progtech.torpedo.model.CharMap;

public interface GameSaveRepository {
    void save(CharMap currentMap);

    CharMap load();
}
