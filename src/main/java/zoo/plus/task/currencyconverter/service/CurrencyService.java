package zoo.plus.task.currencyconverter.service;

import zoo.plus.task.currencyconverter.model.CurrencyResponse;

public interface CurrencyService {

    CurrencyResponse getExchangeRates();

    CurrencyResponse getExchangeRates(long time);

    CurrencyResponse getExchangeRates(String date);
}
