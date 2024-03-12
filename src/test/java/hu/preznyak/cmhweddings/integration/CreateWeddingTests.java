package hu.preznyak.cmhweddings.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import hu.preznyak.cmhweddings.web.model.enums.Currency;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CreateWeddingTests {

    private static final String API_V1_WEDDING = "/api/v1/wedding";

    private static HttpHeaders headers;

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void givenValidRequestBody_whenSaveWedding_thenWeddingIsSavedSuccessfully() throws Exception {
        //given
        LocalDate dateOfTheWedding = LocalDate.now();
        WeddingDto weddingDto = WeddingDto.builder()
                .brideName("Rachel Green")
                .groomName("Ross Geller")
                .location("Central Perk")
                .date(dateOfTheWedding)
                .price(new BigDecimal(500))
                .currency(Currency.USD)
                .build();
        String weddingDtoJson = objectMapper.writeValueAsString(weddingDto);
        HttpEntity<String> request = new HttpEntity<>(weddingDtoJson, headers);

        //when
        WeddingDto savedWeddingDto = restTemplate.postForObject("http://localhost:" + port + API_V1_WEDDING, request, WeddingDto.class);

        //then
        assertEquals("Rachel Green", savedWeddingDto.getBrideName());
        assertEquals("Ross Geller", savedWeddingDto.getGroomName());
        assertEquals("Central Perk", savedWeddingDto.getLocation());
        assertEquals(Currency.USD, savedWeddingDto.getCurrency());
        assertEquals(new BigDecimal(500), savedWeddingDto.getPrice());
        assertEquals(dateOfTheWedding, savedWeddingDto.getDate());

        assertThat(savedWeddingDto, hasProperty("createdDate"));
        assertNotNull(savedWeddingDto.getCreatedDate());
        assertThat(savedWeddingDto, hasProperty("lastUpdatedDate"));
        assertNotNull(savedWeddingDto.getLastUpdatedDate());

    }

    @Test
    void givenInvalidRequestBody_whenSaveWedding_then400IsReturned() throws Exception {
        //given
        WeddingDto weddingDto = WeddingDto.builder()
                .brideName("Rachel Green")
                .location("Central Perk")
                .price(new BigDecimal(500))
                .build();
        String weddingDtoJson = objectMapper.writeValueAsString(weddingDto);
        HttpEntity<String> request = new HttpEntity<>(weddingDtoJson, headers);

        //when
        ResponseEntity response = restTemplate.postForEntity("http://localhost:" + port + API_V1_WEDDING, request, String.class);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

}
