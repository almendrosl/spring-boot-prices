package lucas.prices.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Prices {
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long productID;
    private Integer priority;
    private Double price;
    private String currency;

}
