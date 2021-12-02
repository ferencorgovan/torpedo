package ui;

import hu.nye.progtech.torpedo.ui.CharMapPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CharMapPrinterTest {
    private static final int MAP_LENGTH = 3;

    private static final char[][] MAP = {
            {'_', '_', '_'},
            {'+', '+', 'X'},
            {'_', 'X', '_'}
    };
    private static final char[][] CHAR_MAP = new char[MAP_LENGTH][MAP_LENGTH];
    private CharMapPrinter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CharMapPrinter();
    }

    @Test
    public void testCharMapPrint() {
        // given

        // when
        underTest.printMap(CHAR_MAP);

        // then

    }
}

