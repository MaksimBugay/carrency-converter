package zoo.plus.task.currencyconverter.model;

import zoo.plus.task.currencyconverter.validation.FormattedDate;

import static zoo.plus.task.currencyconverter.service.CurrencyServiceImpl.API_DATE_FORMAT;

public class CurrencyRequest {

    @FormattedDate(pattern = API_DATE_FORMAT, message = "*Please provide a valid request date " + API_DATE_FORMAT)
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
