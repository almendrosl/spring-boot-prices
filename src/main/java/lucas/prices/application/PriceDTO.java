package lucas.prices.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceDTO {
    @JsonProperty("productId")
    @NotNull(message = "productId is mandatory")
    private Long productId;

    @JsonProperty("brandId")
    @NotNull(message = "brandId is mandatory")
    private Long brandId;

    @JsonProperty("priceList")
    @NotNull(message = "priceList is mandatory")
    private Long priceList;

    @JsonProperty("startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    @NotNull(message = "startDate is mandatory")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    @NotNull(message = "endDate is mandatory")
    private LocalDateTime endDate;

    @JsonProperty("price")
    @NotNull(message = "price is mandatory")
    @Min(0)
    private Float price;

    @NotNull(message = "priority is mandatory")
    private Integer priority;

    @NotNull(message = "currency is mandatory")
    private String currency;

}
