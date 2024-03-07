package hu.preznyak.cmhweddings.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.services.WeddingService;
import hu.preznyak.cmhweddings.web.model.Wedding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeddingController.class)
public class WeddingControllerTests {

    public static final String API_V1_WEDDING = "/api/v1/wedding";
    public static final UUID VALID_UUID = UUID.randomUUID();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    WeddingService weddingService;

    @Test
    void givenValidWeddingId_whenFindById_then200IsReceived() throws Exception {

        //Given
        Wedding expectedWedding = Wedding.builder()
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
        Wedding toSave = getWedding(VALID_UUID);
        when(weddingService.save(toSave)).thenReturn(toSave);

        //Then
        mockMvc.perform(post(API_V1_WEDDING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(VALID_UUID.toString()));

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
        Wedding expectedResponse = getWedding(VALID_UUID);
        expectedResponse.setLocation("Another location");

        Wedding updateRequestBody = Wedding.builder()
                .id(VALID_UUID)
                .location("Another location")
                .build();

        String updateRequestBodyJson = objectMapper.writeValueAsString(updateRequestBody);

        when(weddingService.update(VALID_UUID, updateRequestBody)).thenReturn(expectedResponse);

        //then
        mockMvc.perform(put(API_V1_WEDDING + "/" + VALID_UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBodyJson))
                .andExpect(status().isOk());

    }

    private Wedding getWedding(UUID id) {
        return Wedding.builder()
                .id(id)
                .brideName("Test Bride")
                .groomName("Test Groom")
                .location("Testedelphia")
                .date(LocalDate.now())
                .price(250.0)
                .build();
    }

}
