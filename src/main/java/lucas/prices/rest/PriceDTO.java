package lucas.prices.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PriceDTO {
    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("brandId")
    private Long brandId;

    @JsonProperty("priceList")
    private Long priceList;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("price")
    private Float price;

}
