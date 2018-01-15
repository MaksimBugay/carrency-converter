package zoo.plus.task.currencyconverter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:health.feature")
public class HealthIntegrationTest {
}
