package lucas.prices.domain.service;

import lucas.prices.domain.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PricesService {

    Optional<Price> findPricesByQuery(LocalDateTime date, Long productId, Long brandID);

    void savePrice(Price price);

    List<Price> findAllPrices();
}
