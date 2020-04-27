package it.polimi.ingsw.Model.God;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

class GodTest {

    private God godUnderTest;

    @BeforeEach
    void setUp() {
        godUnderTest = new God("godName", Arrays.asList("value"));
    }
}
