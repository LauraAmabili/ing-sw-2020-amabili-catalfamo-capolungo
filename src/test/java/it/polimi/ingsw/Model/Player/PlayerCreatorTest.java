package it.polimi.ingsw.Model.Player;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PlayerCreatorTest {

    private PlayerCreator playerCreatorUnderTest;

    @BeforeEach
    void setUp() {
        playerCreatorUnderTest = new PlayerCreator();
        playerCreatorUnderTest.gson = new Gson();
        playerCreatorUnderTest.arrayGods = new ArrayList<>(Arrays.asList(new God("godName", Arrays.asList("value"))));
    }

    @Test
    void testCreatePlayer() {
        // Setup
        final PlayerInterface p = null;

        // Run the test
        final PlayerInterface result = playerCreatorUnderTest.createPlayer("godName", p);

        // Verify the results
    }

    @Test
    void testAddEffects() {
        // Setup
        final PlayerInterface p = null;
        final List<String> effects = Arrays.asList("value");

        // Run the test
        final PlayerInterface result = playerCreatorUnderTest.addEffects(p, effects);

        // Verify the results
    }

    @Test
    void testRead() {
        // Setup

        // Run the test
        playerCreatorUnderTest.read();

        // Verify the results
    }

    @Test
    void testFind() {
        // Setup

        // Run the test
        final God result = playerCreatorUnderTest.find("g");

        // Verify the results
    }

    void testFindWithMockString () {
        // Setup

        // Run the test
        final God result = playerCreatorUnderTest.find("g");

        // Verify the results
    }


}
