package com.browserstack.cucumber.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/sample.feature",
        tags = "@Example1_with_autocomplete",
        glue = "com.browserstack.cucumber.steps"
)
public class SampleTest {
}
