package zoo.plus.task.currencyconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyConverterHealthService implements HealthService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<String> checkHealth() {
        List<String> errors = new ArrayList<>();

        try {
            namedParameterJdbcTemplate.query("select count(1) from user", new SingleColumnRowMapper<>());
        } catch (Exception ex) {
            errors.add("Database is unavailable: " + ex.getMessage());
        }

        return errors;
    }

}
