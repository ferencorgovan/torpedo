package hu.nye.progtech.torpedo.persistence;

import java.io.File;

import hu.nye.progtech.torpedo.model.CharMap;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlGameSaveRepository implements GameSaveRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlGameSaveRepository.class);
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
            LOGGER.error("Error while saving gamestate to XML", e);
            throw new RuntimeException("Failer to save gamestate", e);
        }
    }

    @Override
    public CharMap load() {
        try {
            CharMap charMap = (CharMap) unmarshaller.unmarshal(new File("gamesave.xml"));
            return charMap;
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the gamesave.");
        }
    }
}
