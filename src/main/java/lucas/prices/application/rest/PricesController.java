package lucas.prices.application.rest;

import lucas.prices.application.PricesMapper;
import lucas.prices.application.exceptions.BadTimeFormatException;
import lucas.prices.application.exceptions.PriceNotFoundException;
import lucas.prices.application.responses.PriceDTO;
import lucas.prices.domain.service.PricesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController()
@RequestMapping("/prices")
public class PricesController {

    private final PricesMapper mapper;
    private final PricesService service;

    public PricesController(PricesMapper mapper, PricesService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping()
    public PriceDTO findPricesByQuery(@RequestParam(value = "date") String date,
                                                      @RequestParam(value = "productId") Long productId,
                                                      @RequestParam(value = "brandID") Long brandID) {
        final PriceDTO priceDTO = mapper.toPricesDTO(service.findPricesByQuery(formatDate(date), productId, brandID));
        if (priceDTO == null) {
            throw new PriceNotFoundException("Price not found of productId " + productId);
        }
        return priceDTO;
    }

    /**
     * formatDate return LocalDateTime parsed by the format 'yyyy-MM-dd-HH.mm.ss' ej. 2020-06-14-15.00.01
     * @param date date to parse
     * @return LocalDateTime parsed
     */
    private LocalDateTime formatDate(String date) {
        LocalDateTime localDateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        try {
            localDateTime = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new BadTimeFormatException("The format of dat is incorrect, should be yyyy-MM-dd-HH.mm.ss");
        }
        return localDateTime;
    }

}
