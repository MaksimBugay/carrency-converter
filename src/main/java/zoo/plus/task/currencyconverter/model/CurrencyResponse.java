package zoo.plus.task.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableMap;
import zoo.plus.task.currencyconverter.model.serialization.JpaRatesConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "history")
@IdClass(HistoryId.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "requested_date")
    private String requestedDate;

    @Column(name = "requested_at")
    private Long timestamp;

    @Column(name = "base")
    private String base;

    @Column(name = "rates")
    @Convert(converter = JpaRatesConverter.class)
    private Map<String, Double> rates;

    @Column(name = "created_at")
    private Date created = new Date();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates == null ? null : ImmutableMap.copyOf(rates);
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
