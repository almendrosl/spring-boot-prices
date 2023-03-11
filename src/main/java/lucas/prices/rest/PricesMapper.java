package lucas.prices.rest;

import lucas.prices.models.Prices;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PricesMapper {

    PricesDTO toPricesDTO(Prices prices);

}
