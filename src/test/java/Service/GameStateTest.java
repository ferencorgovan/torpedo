package Service;

import hu.nye.progtech.torpedo.model.CharMap;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.*;
import hu.nye.progtech.torpedo.ui.MapPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.verifyNoInteractions;

public class GameStateTest {
    private GameState underTest;

    @Mock
    private InputReader inputReader;
    @Mock
    private CommandHandler commandHandler;
    @Mock
    private MapGenerator mapGenerator;
    @Mock
    private CharMapGenerator charMapGenerator;
    @Mock
    private MapPrinter mapPrinter;
    @Mock
    private CharMap charMap;
    private int mapLength;
    @Mock
    private String input;

    @BeforeEach
    public void init() {
        underTest = new GameState();
    inputReader = Mockito.mock(InputReader.class);
    commandHandler = Mockito.mock(CommandHandler.class);
        mapGenerator = Mockito.mock(MapGenerator.class);
        charMapGenerator = Mockito.mock(CharMapGenerator.class);
        mapPrinter = Mockito.mock(MapPrinter.class);

        charMap = Mockito.mock(CharMap.class);
        mapLength = 10;
    }
    @Test
    public void testPlayGameShouldLoopTheGameUntilTheUserDoesNotExit() {
        // given
        input = "exit";
        // when
        //underTest.playGame();

        // then
        Mockito.verify(mapGenerator.generateMap(10));

        Mockito.verifyNoMoreInteractions(underTest);
    }
}
