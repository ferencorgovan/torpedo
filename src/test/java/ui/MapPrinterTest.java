package ui;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.ui.MapPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapPrinterTest {
    private static final int MAP_LENGTH = 3;
    private static final boolean[][] MAP = {
            {true, true, true},
            {false, false, false},
            {true, false, false}
    };
    private static final MapVO MAP_VO = new MapVO(MAP_LENGTH, MAP);
    private MapPrinter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapPrinter();
    }

    @Test
    public void testMapPrint() {
        // given

        // when
        underTest.printMap(MAP_VO);

        // then

    }
}
