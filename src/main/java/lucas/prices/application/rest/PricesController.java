package lucas.prices.application.rest;

import jakarta.validation.Valid;
import lucas.prices.application.PricesMapper;
import lucas.prices.application.exceptions.DateTimeFormatException;
import lucas.prices.application.exceptions.PriceNotFoundException;
import lucas.prices.application.PriceDTO;
import lucas.prices.domain.Price;
import lucas.prices.domain.service.PricesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController()
@RequestMapping("/prices")
public class PricesController {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
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

        final Price price = service.findPricesByQuery(formatDate(date), productId, brandID)
                .orElseThrow(() -> new PriceNotFoundException("Price not found to productId " + productId));

        return mapper.toPricesDTO(price);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDTO savePrice(@RequestBody @Valid PriceDTO priceDTO) {
        Price price = mapper.toPrice(priceDTO);
        service.savePrice(price);
        return priceDTO;
    }

    /**
     * formatDate return LocalDateTime parsed by the format 'yyyy-MM-dd-HH.mm.ss' ej. 2020-06-14-15.00.01
     * @param date date to parse
     * @return LocalDateTime parsed
     */
    private LocalDateTime formatDate(String date) {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException("The format of dat is incorrect, should be yyyy-MM-dd-HH.mm.ss");
        }
        return localDateTime;
    }

}
