package it.polimi.ingsw.Model.God;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class GodFileCreatorTest {

    private GodFileCreator godFileCreatorUnderTest;


    @Test
    void testCreate() {
        // Setup

        // Run the test
        godFileCreatorUnderTest.create();

        // Verify the results
    }

    @Test
    void testWrite() {
        // Setup
        final ArrayList<God> arrayGods = new ArrayList<>(Arrays.asList(new God("godName", Arrays.asList("value"),"descTitle","descStep","descEff")));

        // Run the test
        godFileCreatorUnderTest.write(arrayGods);

        // Verify the results
    }
}
