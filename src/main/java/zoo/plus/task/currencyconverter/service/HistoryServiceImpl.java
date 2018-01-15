package zoo.plus.task.currencyconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zoo.plus.task.currencyconverter.model.CurrencyResponse;
import zoo.plus.task.currencyconverter.repository.HistoryRepository;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void saveHistoryRecord(CurrencyResponse record) {
        historyRepository.save(record);
    }

    @Override
    public List<CurrencyResponse> getLastTenRecords(int userId) {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setUserId(userId);
        currencyResponse.setCreated(null);
        Example<CurrencyResponse> example = Example.of(currencyResponse);

        PageRequest request = new PageRequest(0, 10, Sort.Direction.DESC, "created");

        return historyRepository.findAll(example, request).getContent();
    }
}
