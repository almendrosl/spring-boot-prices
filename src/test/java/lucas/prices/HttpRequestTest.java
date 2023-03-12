package lucas.prices;

import lucas.prices.rest.PriceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest(name = "Test {index} petición del día y hora {0} del producto {1} para la brand {2} (ZARA)")
    @CsvSource({
            "2020-06-14-10.00.01, 35455, 1, 1, 2020-06-14T00:00, 2020-12-31T23:59:59, 35.5",
            "2020-06-14-16.00.01, 35455, 1, 2, 2020-06-14T15:00, 2020-06-14T18:10, 25.45",
            "2020-06-14-21.00.00, 35455, 1, 1, 2020-06-14T00:00, 2020-12-31T23:59:59, 35.5",
            "2020-06-15-10.00.00, 35455, 1, 3, 2020-06-15T00:00, 2020-06-15T11:00, 30.5",
            "2020-06-16-21.00.00, 35455, 1, 4, 2020-06-15T16:00, 2020-12-31T23:59:59, 38.95",
    })
    public void getPricesTest(String date, Long productId, Long brandID, Long priceList, String startDate, String endDate, Float price) throws Exception {
        String url = String.format("http://localhost:%s/api/v1/prices?date=%s&productId=%s&brandID=%s", port, date, productId, brandID);
        ResponseEntity<PriceDTO> response =  restTemplate.getForEntity(url, PriceDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getProductId(), productId);
        Assertions.assertEquals(response.getBody().getPriceList(), priceList);
        Assertions.assertEquals(response.getBody().getStartDate().toString(), startDate);
        Assertions.assertEquals(response.getBody().getEndDate().toString(), endDate);
        Assertions.assertEquals(response.getBody().getPrice(), price);
    }

    @Test
    public void getPricesTestGetBadRequest() throws Exception {
        String url = String.format("http://localhost:%s/api/v1/prices", port);
        ResponseEntity<PriceDTO> response =  restTemplate.getForEntity(url, PriceDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getPricesTestWhitBadDateFormatGetBadRequest() throws Exception {
        String url = String.format("http://localhost:%s/api/v1/prices?date=%s&productId=%s&brandID=%s", port, "2020-06-16-21.0000", 35455, 1);
        ResponseEntity<PriceDTO> response =  restTemplate.getForEntity(url, PriceDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getPricesTestGetNotFound() throws Exception {
        String url = String.format("http://localhost:%s/api/v1/prices?date=%s&productId=%s&brandID=%s", port, "2020-06-16-21.00.00", 354554, 1);
        ResponseEntity<PriceDTO> response =  restTemplate.getForEntity(url, PriceDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}