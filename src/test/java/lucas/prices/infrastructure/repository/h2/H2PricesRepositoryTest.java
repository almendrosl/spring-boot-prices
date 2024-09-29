package lucas.prices.infrastructure.repository.h2;


import lucas.prices.domain.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class H2PricesRepositoryTest {

    @Mock
    private SpringDataPriceRepository springDataPriceRepository;

    @Mock
    private PriceEntityToPriceMapper priceEntityToPriceMapper;

    @InjectMocks
    private H2PricesRepository h2PricesRepository;


    @Test
    void testFindPricesByDateProductIdAndBrandId() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        List<PriceEntity> priceEntities = Arrays.asList(mock(PriceEntity.class), mock(PriceEntity.class));
        List<Price> expectedPrices = Arrays.asList(mock(Price.class), mock(Price.class));

        when(springDataPriceRepository.findPricesByDateProductIdAndBrandId(date, productId, brandId))
                .thenReturn(priceEntities);
        when(priceEntityToPriceMapper.toPrices(priceEntities)).thenReturn(expectedPrices);

        List<Price> actualPrices = h2PricesRepository.findPricesByDateProductIdAndBrandId(date, productId, brandId);

        assertEquals(expectedPrices, actualPrices);
        verify(springDataPriceRepository, times(1)).findPricesByDateProductIdAndBrandId(date, productId, brandId);
        verify(priceEntityToPriceMapper, times(1)).toPrices(priceEntities);
    }

    @Test
    void testSavePrice() {
        Price price = mock(Price.class);
        PriceEntity priceEntity = mock(PriceEntity.class);

        when(priceEntityToPriceMapper.toPriceEntity(price)).thenReturn(priceEntity);

        h2PricesRepository.savePrice(price);

        verify(priceEntityToPriceMapper, times(1)).toPriceEntity(price);
        verify(springDataPriceRepository, times(1)).save(priceEntity);
    }

    @Test
    void testFindAllPrices() {
        List<PriceEntity> priceEntities = Arrays.asList(mock(PriceEntity.class), mock(PriceEntity.class));
        List<Price> expectedPrices = Arrays.asList(mock(Price.class), mock(Price.class));

        when(springDataPriceRepository.findAll()).thenReturn(priceEntities);
        when(priceEntityToPriceMapper.toPrices(priceEntities)).thenReturn(expectedPrices);

        List<Price> actualPrices = h2PricesRepository.findAllPrices();

        assertEquals(expectedPrices, actualPrices);
        verify(springDataPriceRepository, times(1)).findAll();
        verify(priceEntityToPriceMapper, times(1)).toPrices(priceEntities);
    }

}