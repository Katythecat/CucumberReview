package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/",
        glue = "steps",
        tags="@login",
        dryRun = false,
        plugin = {"pretty","html:target/cucumber/report.html","json:target/cucumber/report.json",
                "rerun:target/failed.txt"})

public class SmokeRunner {
}
