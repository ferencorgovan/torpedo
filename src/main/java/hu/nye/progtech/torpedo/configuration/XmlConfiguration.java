package hu.nye.progtech.torpedo.configuration;

import jakarta.xml.bind.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlConfiguration {

    /*@Bean
    Marshaller marshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(char[][].class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return marshaller;
    }

    @Bean
    Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(char[][].class);

        return jaxbContext.createUnmarshaller();
    }*/
}
