package zoo.plus.task.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zoo.plus.task.currencyconverter.model.CurrencyResponse;

@Repository
public interface HistoryRepository extends JpaRepository<CurrencyResponse, Long> {
}
