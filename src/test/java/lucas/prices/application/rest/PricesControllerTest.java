package lucas.prices.application.rest;

import lucas.prices.application.PriceDTO;
import lucas.prices.application.PricesMapper;
import lucas.prices.application.exceptions.DateTimeFormatException;
import lucas.prices.application.exceptions.PriceNotFoundException;
import lucas.prices.domain.Price;
import lucas.prices.domain.service.PricesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

    @Mock
    private PricesService pricesService;

    @Mock
    private PricesMapper pricesMapper;

    @InjectMocks
    private PricesController pricesController;

    @Test
    void testFindPricesByQuery_Success() {
        String date = "2020-06-14-15.00.01";
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime parsedDate = LocalDateTime.of(2020, 6, 14, 15, 0, 1);

        Price price = mock(Price.class);
        PriceDTO priceDTO = mock(PriceDTO.class);

        when(pricesService.findPricesByQuery(parsedDate, productId, brandId)).thenReturn(Optional.of(price));
        when(pricesMapper.toPriceDTO(price)).thenReturn(priceDTO);

        PriceDTO result = pricesController.findPricesByQuery(date, productId, brandId);

        assertNotNull(result);
        assertEquals(priceDTO, result);
        verify(pricesService, times(1)).findPricesByQuery(parsedDate, productId, brandId);
        verify(pricesMapper, times(1)).toPriceDTO(price);
    }

    @Test
    void testFindPricesByQuery_PriceNotFound() {
        String date = "2020-06-14-15.00.01";
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime parsedDate = LocalDateTime.of(2020, 6, 14, 15, 0, 1);

        when(pricesService.findPricesByQuery(parsedDate, productId, brandId)).thenReturn(Optional.empty());

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () ->
                pricesController.findPricesByQuery(date, productId, brandId));

        assertEquals("Price not found to productId " + productId, exception.getMessage());
        verify(pricesService, times(1)).findPricesByQuery(parsedDate, productId, brandId);
    }

    @Test
    void testFindPricesByQuery_InvalidDateFormat() {
        String invalidDate = "2020-06-14-invalid";

        DateTimeFormatException exception = assertThrows(DateTimeFormatException.class, () ->
                pricesController.findPricesByQuery(invalidDate, 1L, 1L));

        assertEquals("The format of dat is incorrect, should be yyyy-MM-dd-HH.mm.ss", exception.getMessage());
    }

    @Test
    void testSavePrice_Success() {
        PriceDTO priceDTO = mock(PriceDTO.class);
        Price price = mock(Price.class);

        when(pricesMapper.toPrice(priceDTO)).thenReturn(price);

        PriceDTO result = pricesController.savePrice(priceDTO);

        assertNotNull(result);
        assertEquals(priceDTO, result);
        verify(pricesService, times(1)).savePrice(price);
        verify(pricesMapper, times(1)).toPrice(priceDTO);
    }

    @Test
    void testFindAllPrices_Success() {
        List<Price> prices = Arrays.asList(mock(Price.class), mock(Price.class));
        List<PriceDTO> priceDTOs = Arrays.asList(mock(PriceDTO.class), mock(PriceDTO.class));

        when(pricesService.findAllPrices()).thenReturn(prices);
        when(pricesMapper.toPricesDTO(prices)).thenReturn(priceDTOs);

        List<PriceDTO> result = pricesController.findAllPrices();

        assertNotNull(result);
        assertEquals(priceDTOs, result);
        verify(pricesService, times(1)).findAllPrices();
        verify(pricesMapper, times(1)).toPricesDTO(prices);
    }
}