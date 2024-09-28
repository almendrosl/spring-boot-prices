package lucas.prices.infrastructure.repository.h2;

import lucas.prices.domain.Price;
import lucas.prices.domain.repository.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class H2PricesRepository implements PriceRepository {

    private final SpringDataPriceRepository repository;
    private final PriceEntityToPriceMapper mapper;


    public H2PricesRepository(SpringDataPriceRepository repository, PriceEntityToPriceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<Price> findPricesByDateProductIdAndBrandId(LocalDateTime date, Long productId, Long brandId) {
        return mapper.toPrices(repository.findPricesByDateProductIdAndBrandId(date, productId, brandId));
    }
}
