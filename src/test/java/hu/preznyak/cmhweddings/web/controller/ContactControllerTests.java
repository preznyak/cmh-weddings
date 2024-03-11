package hu.preznyak.cmhweddings.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.services.ContactService;
import hu.preznyak.cmhweddings.web.model.ContactDto;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
public class ContactControllerTests {

    public static final String API_V1_CONTACT = "/api/v1/contact";
    public static final UUID VALID_UUID = UUID.fromString("b582c17f-e445-4794-80da-87326c2d1ec9");

    private String contactDtoJson;
    private ContactDto contactDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ContactService contactService;

    @BeforeEach
    void setup() throws IOException {
        contactDtoJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/contactdto.json")));
        contactDto = objectMapper.readValue(contactDtoJson, ContactDto.class);
    }

    @Test
    void testFindById() throws Exception {

        //given
        val nameOfTheCotact = contactDto.getName();

        when(contactService.findById(VALID_UUID)).thenReturn(contactDto);

        mockMvc.perform(get(API_V1_CONTACT + "/" + VALID_UUID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_UUID.toString()))
                .andExpect(jsonPath("$.name").value(nameOfTheCotact))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testSaveContact() throws Exception {

        when(contactService.save(contactDto)).thenReturn(contactDto);

        mockMvc.perform(post(API_V1_CONTACT)
                        .content(contactDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(VALID_UUID.toString()));
    }

    @Test
    void testUpdateContact() throws Exception {

        when(contactService.update(VALID_UUID, contactDto)).thenReturn(contactDto);

        mockMvc.perform(put(API_V1_CONTACT + "/" + VALID_UUID)
                        .content(contactDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_UUID.toString()));

    }

    @Test
    void testDeleteContact() throws Exception {

        doNothing().when(contactService).delete(VALID_UUID);

        mockMvc.perform(delete(API_V1_CONTACT + "/" + VALID_UUID))
                .andExpect(status().isNoContent());
    }
}
