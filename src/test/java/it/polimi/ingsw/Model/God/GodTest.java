package it.polimi.ingsw.Model.God;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GodTest {

    @Test
    void setUp() {
        God godUnderTest = new God("godName", Arrays.asList("value"), "descTitle", "descStep", "descEff");
        godUnderTest.setGodName("Butterfly");
        godUnderTest.setEffects(Arrays.asList("value"));
        Assert.assertEquals("Butterfly", godUnderTest.getGodName());
        Assert.assertEquals("descTitle", godUnderTest.getDescriptionTitle());
        Assert.assertEquals("descEff",godUnderTest.getDescriptionEffect());
        Assert.assertEquals(Arrays.asList("value"), godUnderTest.getEffects());
    }




}
