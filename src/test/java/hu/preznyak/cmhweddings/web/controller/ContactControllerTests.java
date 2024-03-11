package hu.preznyak.cmhweddings.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
public class ContactControllerTests {

    public static final String API_V1_CONTACT = "/api/v1/contact";
    public static final UUID VALID_UUID = UUID.fromString("b582c17f-e445-4794-80da-87326c2d1ec9");

    @Autowired
    MockMvc mockMvc;

    @Test
    void testFindById() throws Exception {
        mockMvc.perform(get(API_V1_CONTACT + "/" + VALID_UUID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VALID_UUID.toString()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testSaveContact() throws Exception {
        String contactDtoJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/contactdto.json")));

        mockMvc.perform(post(API_V1_CONTACT)
                        .content(contactDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateContact() throws Exception {
        String contactDtoJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/contactdto.json")));

        mockMvc.perform(put(API_V1_CONTACT + "/" + VALID_UUID)
                        .content(contactDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    void testDeleteContact() throws Exception {
        mockMvc.perform(delete(API_V1_CONTACT + "/" + VALID_UUID))
                .andExpect(status().isNoContent());
    }
}
