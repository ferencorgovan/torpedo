package hu.nye.progtech.torpedo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class InputReaderTest {
    private InputReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new InputReader();
    }

    Scanner scanner = new Scanner(System.in);
    private static final String INPUT = "shoot a5";

    @Test
    public void testReadInputShouldReturnTheInputReadByScanner() {
        // given
        given(scanner.nextLine()).willReturn(INPUT);
        // when
        String result = underTest.readCommand();

        // then
        assertEquals(INPUT, result);
    }
}
