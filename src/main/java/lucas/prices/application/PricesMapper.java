package lucas.prices.application;

import lucas.prices.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PricesMapper {

    PriceDTO toPricesDTO(Price price);

    Price toPrice(PriceDTO priceDTO);

}
