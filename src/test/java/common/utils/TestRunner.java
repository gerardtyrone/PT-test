package common.utils;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = { "features" },
        glue = { "stepDefinitions" }
)

public class TestRunner {
}
