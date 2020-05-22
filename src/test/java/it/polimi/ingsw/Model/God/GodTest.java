package it.polimi.ingsw.Model.God;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

class GodTest {

    @BeforeEach
    void setUp() {
        God godUnderTest = new God("godName", Arrays.asList("value"), "descTitle", "descStep", "descEff");
    }
}
