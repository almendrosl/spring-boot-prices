package lucas.prices.domain.service;

import lucas.prices.domain.Price;

import java.time.LocalDateTime;

public interface PricesService {

    Price findPricesByQuery(LocalDateTime date, Long productId, Long brandID);
}
