package lucas.prices.application;

import lucas.prices.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PricesMapper {

    PriceDTO toPriceDTO(Price price);

    Price toPrice(PriceDTO priceDTO);

    List<PriceDTO> toPricesDTO(List<Price> prices);

}
