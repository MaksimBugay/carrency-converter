package zoo.plus.task.currencyconverter.service;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MetricRegistryService {

    private static final MetricRegistry metrics = new MetricRegistry();

    public MetricRegistry getMetricRegistry() {
        return metrics;
    }

    @PostConstruct
    private void init() {
        JmxReporter reporter = JmxReporter.forRegistry(metrics).build();
        reporter.start();
    }
}
