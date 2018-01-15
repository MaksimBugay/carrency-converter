package zoo.plus.task.currencyconverter.model.serialization;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.MapUtils;
import zoo.plus.task.currencyconverter.util.JsonUtil;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;


public abstract class JpaMapJsonConverter<K, V> implements AttributeConverter<Map<K, V>, String> {

    protected abstract Class<K> getKeyClass();

    protected abstract Class<V> getValueClass();

    @Override
    public String convertToDatabaseColumn(Map<K, V> mapObject) {
        if (MapUtils.isEmpty(mapObject)){
            return null;
        }
        try {
            return JsonUtil.toJson(mapObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<K, V> convertToEntityAttribute(String dbData) {
        if(StringUtils.isEmpty(dbData)){
            return null;
        }
        try {
            return JsonUtil.fromJsonToMap(dbData, getKeyClass(), getValueClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
