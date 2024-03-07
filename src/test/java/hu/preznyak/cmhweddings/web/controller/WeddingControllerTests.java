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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeddingController.class)
public class WeddingControllerTests {

    public static final String API_V1_WEDDING = "/api/v1/wedding";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    WeddingService weddingService;

    @Test
    void givenValidWeddingId_whenFindById_then200IsReceived() throws Exception {

        //Given
        UUID validId = UUID.randomUUID();
        Wedding expectedWedding = Wedding.builder()
                .id(validId)
                .build();
        when(weddingService.findById(validId)).thenReturn(expectedWedding);

        String findByIdUrl = API_V1_WEDDING +
                "/" +
                validId;

        //Then
        mockMvc.perform(get(findByIdUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(validId.toString()));

    }

    @Test
    void givenValidRequestBody_whenCreateNewWedding_then201IsReceived() throws Exception {

        //Given
        UUID validId = UUID.randomUUID();
        Wedding toSave = Wedding.builder()
                .id(validId)
                .brideName("Test Bride")
                .groomName("Test Groom")
                .location("Testedelphia")
                .date(LocalDate.now())
                .price(250.0)
                .build();
        when(weddingService.save(toSave)).thenReturn(toSave);

        //Then
        mockMvc.perform(post(API_V1_WEDDING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(validId.toString()));

    }

    @Test
    void findAll_justForFun() throws Exception {
        String findAllUrl = API_V1_WEDDING +
                "/findAll";
        mockMvc.perform(get(findAllUrl))
                .andExpect(status().isOk());
    }

}
