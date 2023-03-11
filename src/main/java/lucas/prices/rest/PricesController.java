package lucas.prices.rest;

import lucas.prices.models.Prices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/prices")
public class PricesController {

    private final PricesMapper mapper;

    public PricesController(PricesMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<PricesDTO> findPricesByQuery(@RequestParam(value = "date") String date,
                                                       @RequestParam(value = "productId") Integer productId,
                                                        @RequestParam(value = "brandID") Integer brandID) {
        return new ResponseEntity<>(new PricesDTO(), HttpStatus.OK);
    }

}
