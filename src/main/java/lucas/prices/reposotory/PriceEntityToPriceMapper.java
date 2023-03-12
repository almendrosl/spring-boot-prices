package lucas.prices.reposotory;

import lucas.prices.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceEntityToPriceMapper {

    Price toPrice(PriceEntity priceEntity);

    List<Price> toPrices(List<PriceEntity> priceEntities);
}
