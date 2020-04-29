package it.polimi.ingsw.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardCellTest {

    private BoardCell boardCellUnderTest;

    @BeforeEach
    void setUp() {
        boardCellUnderTest = new BoardCell(0, 0);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = boardCellUnderTest.toString();

        // Verify the results
        assertEquals("Row: 0 Col: 0 level: 0 Worker: null Dome: false", result);
    }
}
