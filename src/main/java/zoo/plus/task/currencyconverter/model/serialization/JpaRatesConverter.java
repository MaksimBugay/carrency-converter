package zoo.plus.task.currencyconverter.model.serialization;

public class JpaRatesConverter extends JpaMapJsonConverter<String, Double> {

    @Override
    protected Class<String> getKeyClass() {
        return String.class;
    }

    @Override
    protected Class<Double> getValueClass() {
        return Double.class;
    }
}
