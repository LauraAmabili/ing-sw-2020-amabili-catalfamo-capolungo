package it.polimi.ingsw.Model.God;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class GodFileCreatorTest {

    private GodFileCreator godFileCreatorUnderTest;

    @BeforeEach
    void setUp() {
        godFileCreatorUnderTest = new GodFileCreator();
        godFileCreatorUnderTest.gson = new Gson();
    }

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
        final ArrayList<God> arrayGods = new ArrayList<>(Arrays.asList(new God("godName", Arrays.asList("value"))));

        // Run the test
        godFileCreatorUnderTest.write(arrayGods);

        // Verify the results
    }
}
