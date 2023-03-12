package lucas.prices.services;

import lucas.prices.models.Price;

import java.time.LocalDateTime;

public interface PricesService {

    Price findPricesByQuery(LocalDateTime date, Long productId, Long brandID);
}
