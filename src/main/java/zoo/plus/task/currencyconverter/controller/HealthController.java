package zoo.plus.task.currencyconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zoo.plus.task.currencyconverter.service.HealthService;

import java.util.List;

@RestController
public class HealthController {

    private static final String HEALTH_OK = "ok";

    @Autowired
    private HealthService healthService;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public void health() {
        List<String> errors = healthService.checkHealth();
        if (!errors.isEmpty()) {
            throw new RuntimeException("Server is not healthy: " + errors);
        }
    }

    @RequestMapping(value = "/healthOK", method = RequestMethod.GET, produces = "application/json")
    public HealthStatus healthOK() {
        HealthStatus status = new HealthStatus();
        try {
            health();
            status.setStatus(HEALTH_OK);
        } catch (Exception e) {
            status.setStatus(e.toString());
        }
        return status;
    }

    public static class HealthStatus {

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}
