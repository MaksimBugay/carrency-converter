package zoo.plus.task.currencyconverter.service;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zoo.plus.task.currencyconverter.model.CurrencyResponse;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    public static final String API_DATE_FORMAT = "yyyy-MM-dd";
    private static final String GET_EXCHANGE_RATES_RESOURCE_PATH = "/latest.json?app_id=%s&symbols=EUR,USD,GBP,NZD,AUD,JPY,HUF,RUB";
    private static final String GET_EXCHANGE_RATES_HISTORICAL_RESOURCE_PATH = "/historical/%s.json?app_id=%s&symbols=EUR,USD,GBP,NZD,AUD,JPY,HUF,RUB";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MetricRegistryService metricRegistryService;

    @Value("${currency-converter.provider.api.bas-url}")
    private String baseUrl;

    @Value("${currency-converter.provider.api.app-id}")
    private String appId;

    private String getExchangeRatesUrl;
    private String getExchangeRatesHistoricalUrl;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_DATE_FORMAT);

    private AtomicInteger exchangeRatesCounter = new AtomicInteger(0);
    private AtomicInteger exchangeRatesForDateCounter = new AtomicInteger(0);

    @PostConstruct
    private void init() {
        simpleDateFormat.setLenient(false);
        getExchangeRatesUrl = baseUrl + String.format(GET_EXCHANGE_RATES_RESOURCE_PATH, appId);
        getExchangeRatesHistoricalUrl = baseUrl + String.format(GET_EXCHANGE_RATES_HISTORICAL_RESOURCE_PATH, "%s", appId);

        metricRegistryService.getMetricRegistry().register(MetricRegistry.name(this.getClass(), "exchangeRatesCounter"), (Gauge<Integer>) () -> exchangeRatesCounter.get());
        metricRegistryService.getMetricRegistry().register(MetricRegistry.name(this.getClass(), "exchangeRatesForDateCounter"), (Gauge<Integer>) () -> exchangeRatesForDateCounter.get());
    }

    @Override
    @Cacheable("exchangeRates")
    public CurrencyResponse getExchangeRates() {
        String date = simpleDateFormat.format(new Date());
        CurrencyResponse response = restTemplate.getForObject(getExchangeRatesUrl, CurrencyResponse.class);
        exchangeRatesCounter.incrementAndGet();
        response.setRequestedDate(date);
        return response;
    }

    @Override
    public CurrencyResponse getExchangeRates(long time) {
        String date = simpleDateFormat.format(new Date(time));
        return getExchangeRates(date);
    }

    @Override
    @Cacheable("exchangeRatesHistory")
    public CurrencyResponse getExchangeRates(String date) {
        String url = String.format(getExchangeRatesHistoricalUrl, date);
        CurrencyResponse response = restTemplate.getForObject(url, CurrencyResponse.class);
        exchangeRatesForDateCounter.incrementAndGet();
        response.setRequestedDate(date);
        return response;
    }
}
