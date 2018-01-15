package zoo.plus.task.currencyconverter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper MAPPER_BUILDER = Jackson2ObjectMapperBuilder.json().build();

    private JsonUtil() {

    }

    public static String toJson(Object object) throws JsonProcessingException {
        return MAPPER_BUILDER.writeValueAsString(object);
    }

    public static <T> Object fromJson(String json, Class<T> clazz) throws IOException {
        return MAPPER_BUILDER.readValue(json, clazz);
    }

    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyClass, Class<V> valueClass) throws IOException {
        return MAPPER_BUILDER.readValue(json, MAPPER_BUILDER.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass));
    }


}
