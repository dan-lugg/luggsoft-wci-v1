package com.luggsoft.wci.app.example.tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyStepDefs
{
    private static final Logger logger = LoggerFactory.getLogger(MyStepDefs.class);

    @When("we initialize state")
    public void weInitializeState()
    {
        MyStepDefs.logger.info("When we initialize state");
    }

    @Then("the state is initialized")
    public void theStateIsInitialized()
    {
        MyStepDefs.logger.info("The state is initialized");
        throw new RuntimeException("Oopsie poopsie!");
    }
}
