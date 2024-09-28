package lucas.prices.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Price {
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;

}
