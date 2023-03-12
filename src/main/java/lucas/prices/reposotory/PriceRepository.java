package lucas.prices.reposotory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends Repository<PriceEntity, Long> {

    @Query("SELECT pe FROM PriceEntity pe WHERE pe.startDate <= :date AND pe.endDate >= :date AND pe.productId = :productId AND  pe.brandId = :brandId")
    List<PriceEntity> findPricesByDateProductIdAndBrandId(@Param("date") LocalDateTime date,
                                                    @Param("productId") Long productId,
                                                    @Param("brandId") Long brandId);

}
