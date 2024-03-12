package hu.preznyak.cmhweddings.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.web.model.ContactDto;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CreateContactTests {

    private static final String API_V1_CONTACT = "/api/v1/contact";
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
    void givenValidRequestBody_whenSaveContact_thenContactIsSavedSuccessfully() throws JsonProcessingException {
        //given
        UUID weddingDtoId = getIdFromAWeddingDto();
        ContactDto newContact = ContactDto.builder()
                .name("Robert Downey Jr.")
                .emailAddress("iamironman@marvel.com")
                .weddingDtoId(weddingDtoId)
                .build();

        String contactDtoJson = objectMapper.writeValueAsString(newContact);
        HttpEntity<String> request = new HttpEntity<>(contactDtoJson, headers);

        //when
        ContactDto savedContactDto = restTemplate.postForObject("http://localhost:" + port + API_V1_CONTACT, request, ContactDto.class);

        //then
        assertEquals("Robert Downey Jr.", savedContactDto.getName());
        assertEquals("iamironman@marvel.com", savedContactDto.getEmailAddress());
        assertEquals(weddingDtoId, savedContactDto.getWeddingDtoId());
        assertNotNull(savedContactDto.getId());

    }

    @Test
    void givenInvalidRequestBody_whenSaveContact_then400IsReturned() throws JsonProcessingException {

        //given
        ContactDto newContact = ContactDto.builder()
                .name("Robert Downey Jr.")
                .emailAddress("iamironman@marvel.com")
                .build();

        String contactDtoJson = objectMapper.writeValueAsString(newContact);
        HttpEntity<String> request = new HttpEntity<>(contactDtoJson, headers);

        //when
        ResponseEntity response = restTemplate.postForEntity("http://localhost:" + port + API_V1_CONTACT, request, String.class);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    private UUID getIdFromAWeddingDto() throws JsonProcessingException {
        WeddingDto newWedding = WeddingDto.builder()
                .brideName("Zendaya")
                .groomName("Tom Holland")
                .location("Los Angeles")
                .date(LocalDate.now())
                .price(new BigDecimal(1000))
                .currency(Currency.USD)
                .build();

        String weddingDtoJson = objectMapper.writeValueAsString(newWedding);
        HttpEntity<String> request = new HttpEntity<>(weddingDtoJson, headers);

        WeddingDto savedWeddingDto = restTemplate.postForObject("http://localhost:" + port + API_V1_WEDDING, request, WeddingDto.class);

        return savedWeddingDto.getId();
    }
}
