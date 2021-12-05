package persistence;

import hu.nye.progtech.torpedo.model.CharMap;
import hu.nye.progtech.torpedo.persistence.XmlGameSaveRepository;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

public class XmlGameSaveRepositoryTest {
    private XmlGameSaveRepository underTest;

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @BeforeEach
    public void init() {
        marshaller = Mockito.mock(Marshaller.class);
        unmarshaller = Mockito.mock(Unmarshaller.class);
        underTest = new XmlGameSaveRepository(marshaller, unmarshaller);
    }

    @Test
    public void testSaveShouldCallMarshallerWhenThereIsNoException() throws JAXBException {

        //Given
        CharMap currentMap = Mockito.mock(CharMap.class);
        int mapLength = 10;
        Mockito.when(currentMap.getMapLength()).thenReturn(mapLength);
        char[][] map = {{'_','_'}, {'X','_'}};
        Mockito.when(currentMap.getMap()).thenReturn(map);

        //When
        underTest.save(currentMap);

        //Then
        CharMap charMap = new CharMap(mapLength, map);
        Mockito.verify(marshaller).marshal(Mockito.eq(currentMap), Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, currentMap);
    }

    @Test
    public void testSaveShouldThrowRuntimeExceptionWhenThereIsJAXBException() throws JAXBException {
        // Given
        CharMap currentMap = Mockito.mock(CharMap.class);
        int mapLength = 10;
        Mockito.when(currentMap.getMapLength()).thenReturn(mapLength);
        char[][] map = {{'_','_'}, {'X','_'}};
        Mockito.when(currentMap.getMap()).thenReturn(map);
        CharMap persistableMapVO = new CharMap(mapLength, map);
        Mockito.doThrow(JAXBException.class).when(marshaller).marshal(Mockito.eq(currentMap), Mockito.any(File.class));

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.save(currentMap));

        // Then
        Mockito.verify(marshaller).marshal(Mockito.eq(currentMap), Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, currentMap);
    }
    @Test
    public void testLoadShouldReturnCharMapWhenThereIsNoException() throws JAXBException {
        // Given
        CharMap charMap = Mockito.mock(CharMap.class);
        int mapLength = 10;
        Mockito.when(charMap.getMapLength()).thenReturn(mapLength);
        char[][] map = {{'_','_'}, {'X','_'}};
        Mockito.when(charMap.getMap()).thenReturn(map);
        CharMap expected = charMap;
        Mockito.when(unmarshaller.unmarshal(Mockito.any(File.class))).thenReturn(charMap);

        // When
        CharMap actual = underTest.load();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(unmarshaller).unmarshal(Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, charMap);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenThereIsJAXBException() throws JAXBException {
        // Given
        CharMap charMap = Mockito.mock(CharMap.class);
        int mapLength = 10;
        Mockito.when(charMap.getMapLength()).thenReturn(mapLength);
        char[][] map = {{'_','_'}, {'X','_'}};
        Mockito.when(charMap.getMap()).thenReturn(map);
        Mockito.when(unmarshaller.unmarshal(Mockito.any(File.class))).thenThrow(JAXBException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(unmarshaller).unmarshal(Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, charMap);
    }
}
