package zoo.plus.task.currencyconverter.service;

import zoo.plus.task.currencyconverter.model.CurrencyResponse;

import java.util.List;

public interface HistoryService {

    void saveHistoryRecord(CurrencyResponse record);

    List<CurrencyResponse> getLastTenRecords(int userId);
}
