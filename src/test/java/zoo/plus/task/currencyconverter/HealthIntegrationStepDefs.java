package zoo.plus.task.currencyconverter;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HealthIntegrationStepDefs extends CurrencyConverterApplicationTests {

    private ResponseEntity<String> response; // output
    @When("^the client calls /health$")
    public void the_client_issues_GET_health() throws Throwable {
        response = template.getForEntity("/health", String.class);
    }
    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, currentStatusCode.value());
    }
}
