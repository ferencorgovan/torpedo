package hu.nye.progtech.torpedo.persistence;

import java.io.File;

import hu.nye.progtech.torpedo.model.CharMap;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlGameSaveRepository implements GameSaveRepository {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlGameSaveRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public void save(CharMap currentMap) {
        try {
            marshaller.marshal(currentMap, new File("gamesave.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CharMap load() {
        try {
            return new CharMap(10, (char[][]) unmarshaller.unmarshal(new File("gamesave.xml")));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the gamesave.");
        }

    }
}
