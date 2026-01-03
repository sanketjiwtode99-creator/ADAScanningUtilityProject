package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles"},
        glue = {"step.definitions"},
        monochrome = true,
        dryRun = false,
        plugin = {
                "pretty",                                      // pretty console logs
                "html:target/cucumber-reports.html",           // basic HTML report
                "timeline:target/test-output-thread/"
        }

)
public class TestRunner {


}
