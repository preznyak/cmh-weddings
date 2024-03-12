package hu.preznyak.cmhweddings.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.services.WeddingService;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import hu.preznyak.cmhweddings.web.model.enums.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeddingController.class)
public class WeddingControllerTests {

    private static final String API_V1_WEDDING = "/api/v1/wedding";
    private static final UUID VALID_UUID = UUID.randomUUID();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    WeddingService weddingService;

    @Test
    void givenValidWeddingId_whenFindById_then200IsReceived() throws Exception {

        //Given
        WeddingDto expectedWedding = WeddingDto.builder()
                .id(VALID_UUID)
                .build();
        when(weddingService.findById(VALID_UUID)).thenReturn(expectedWedding);

        String findByIdUrl = API_V1_WEDDING +
                "/" +
                VALID_UUID;

        //Then
        mockMvc.perform(get(findByIdUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_UUID.toString()));

    }

    @Test
    void givenValidRequestBody_whenCreateNewWedding_then201IsReceived() throws Exception {

        //Given
        WeddingDto toSave = getValidWeddingDto();
        when(weddingService.save(toSave)).thenReturn(toSave);

        //Then
        mockMvc.perform(post(API_V1_WEDDING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toSave)))
                .andExpect(status().isCreated());

    }

    @Test
    void givenValidWeddingId_whenDelete_then204IsReceived() throws Exception {

        //given
        UUID validId = UUID.randomUUID();
        doNothing().when(weddingService).delete(validId);

        //then
        mockMvc.perform(delete(API_V1_WEDDING + "/" + validId))
                .andExpect(status().isNoContent());
    }

    @Test
    void givenValidRequestBodyAndWeddingId_whenUpdate_then200IsReceived() throws Exception {

        //given
        WeddingDto validWeddingDto = getValidWeddingDto();
        validWeddingDto.setLocation("Another location");

        String updateRequestBodyJson = objectMapper.writeValueAsString(validWeddingDto);

        when(weddingService.update(VALID_UUID, validWeddingDto)).thenReturn(validWeddingDto);

        //then
        mockMvc.perform(put(API_V1_WEDDING + "/" + VALID_UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBodyJson))
                .andExpect(status().isOk());

    }

    private WeddingDto getValidWeddingDto() {
        return WeddingDto.builder()
                .brideName("Test Bride")
                .groomName("Test Groom")
                .location("Testedelphia")
                .date(LocalDate.now())
                .price(new BigDecimal(250.0))
                .currency(Currency.EUR)
                .build();
    }

}
