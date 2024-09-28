package lucas.prices.application.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceDTO {
    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("brandId")
    private Long brandId;

    @JsonProperty("priceList")
    private Long priceList;

    @JsonProperty("startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDate;

    @JsonProperty("price")
    private Float price;

}
