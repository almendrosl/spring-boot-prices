package lucas.prices.rest;

import lucas.prices.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PricesMapper {

    PriceDTO toPricesDTO(Price price);

}
