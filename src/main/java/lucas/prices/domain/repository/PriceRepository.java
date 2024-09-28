package lucas.prices.domain.repository;

import lucas.prices.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findPricesByDateProductIdAndBrandId(LocalDateTime date,
                                                    Long productId,
                                                    Long brandId);

}
