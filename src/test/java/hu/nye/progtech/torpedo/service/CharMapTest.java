package hu.nye.progtech.torpedo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CharMapTest {

    private static final int MAP_LENGTH = 2;
    private static final char[][] EXPECTED_MAP = {
            {'_', '_'},
            {'_', '_'}
    };
    private CharMap underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CharMap();
    }

    @Test
    public void testGeneratingCharMap() {
        // given

        // when
        char[][] result = underTest.generateMap(MAP_LENGTH);

        // then
        assertEquals(EXPECTED_MAP, result);
    }
}