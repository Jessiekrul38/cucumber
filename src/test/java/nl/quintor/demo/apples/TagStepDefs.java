package nl.quintor.demo.apples;

import static org.junit.Assert.fail;

import cucumber.api.java.Before;

public class TagStepDefs {

    @Before("@fail")
    public void beforeIT() {
        fail("Used fail tag!");
    }

}
