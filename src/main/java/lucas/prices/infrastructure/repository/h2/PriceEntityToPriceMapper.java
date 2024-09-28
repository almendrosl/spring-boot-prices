package lucas.prices.infrastructure.repository.h2;

import lucas.prices.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceEntityToPriceMapper {

    Price toPrice(PriceEntity priceEntity);

    List<Price> toPrices(List<PriceEntity> priceEntities);
}
