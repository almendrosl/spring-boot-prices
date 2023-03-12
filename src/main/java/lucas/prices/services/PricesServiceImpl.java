package lucas.prices.services;

import lucas.prices.models.Price;
import lucas.prices.reposotory.PriceEntityToPriceMapper;
import lucas.prices.reposotory.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PricesServiceImpl implements PricesService {

    private final PriceRepository repository;
    private final PriceEntityToPriceMapper mapper;


    public PricesServiceImpl(PriceRepository repository, PriceEntityToPriceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    /**
     * findPricesByQuery gets the price of a product according to the application date, product identifier and brand identifier.
     * In addition, if two tariff match in a range of dates, the one with the highest priority (the highest numerical value) is applied.
     * @param date apication date
     * @param productId product identifier
     * @param brandID brand identifier
     * @return Price
     */
    @Override
    public Price findPricesByQuery(LocalDateTime date, Long productId, Long brandID) {
        List<Price> prices = mapper.toPrices(repository.findPricesByDateProductIdAndBrandId(date, productId, brandID));

        return prices.stream().max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }
}
