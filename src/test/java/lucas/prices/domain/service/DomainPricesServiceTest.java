package lucas.prices.domain.service;


import lucas.prices.domain.Price;
import lucas.prices.domain.repository.PriceRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DomainPricesServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private DomainPricesService domainPricesService;

    @Test
    void testFindPricesByQuery() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Price price1 = mock(Price.class);
        Price price2 = mock(Price.class);
        when(price1.getPriority()).thenReturn(1);
        when(price2.getPriority()).thenReturn(2);

        List<Price> prices = Arrays.asList(price1, price2);

        when(priceRepository.findPricesByDateProductIdAndBrandId(date, productId, brandId)).thenReturn(prices);

        Optional<Price> result = domainPricesService.findPricesByQuery(date, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(price2, result.get());
        verify(priceRepository, times(1)).findPricesByDateProductIdAndBrandId(date, productId, brandId);
    }

    @Test
    void testFindPricesByQuery_NoPricesFound() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(priceRepository.findPricesByDateProductIdAndBrandId(date, productId, brandId)).thenReturn(Arrays.asList());

        Optional<Price> result = domainPricesService.findPricesByQuery(date, productId, brandId);

        assertTrue(result.isEmpty());
        verify(priceRepository, times(1)).findPricesByDateProductIdAndBrandId(date, productId, brandId);
    }

    @Test
    void testSavePrice() {
        Price price = mock(Price.class);

        domainPricesService.savePrice(price);

        verify(priceRepository, times(1)).savePrice(price);
    }

    @Test
    void testFindAllPrices() {
        List<Price> expectedPrices = Arrays.asList(mock(Price.class), mock(Price.class));
        when(priceRepository.findAllPrices()).thenReturn(expectedPrices);

        List<Price> actualPrices = domainPricesService.findAllPrices();

        assertEquals(expectedPrices, actualPrices);
        verify(priceRepository, times(1)).findAllPrices();
    }

}