package hu.nye.progtech.torpedo.configuration;

import hu.nye.progtech.torpedo.persistence.GameSaveRepository;
import hu.nye.progtech.torpedo.persistence.XmlGameSaveRepository;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    //@Bean
    //GameSaveRepository gameSaveRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
    //    return new XmlGameSaveRepository(marshaller, unmarshaller);
    //}
}
