package hu.preznyak.cmhweddings.web.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.domain.Contact;
import hu.preznyak.cmhweddings.web.model.ContactDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactMapperTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ContactMapper contactMapper;

    @Test
    void contactDtoToContact() throws IOException {
        //given
        String contactDtoJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/contactdto.json")));
        ContactDto contactDto = objectMapper.readValue(contactDtoJson, ContactDto.class);

        //when
        Contact contact = contactMapper.contactDtoToContact(contactDto);

        //then
        assertEquals(contactDto.getId(), contact.getId());
        assertEquals(contactDto.getName(), contact.getName());
        assertEquals(contactDto.getEmailAddress(), contact.getEmailAddress());
        assertEquals(contactDto.getPhoneNumber(), contact.getPhoneNumber());
        assertEquals(contactDto.getWeddingDto().getId(), contact.getWedding().getId());

    }

    @Test
    void contactToContactDto() throws IOException {
        //given
        String contactDtoJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/contact.json")));
        Contact contact = objectMapper.readValue(contactDtoJson, Contact.class);

        //when
        ContactDto contactDto = contactMapper.contactToContactDto(contact);

        //then
        assertEquals(contact.getId(), contactDto.getId());
        assertEquals(contact.getName(), contactDto.getName());
        assertEquals(contact.getEmailAddress(), contactDto.getEmailAddress());
        assertEquals(contact.getPhoneNumber(), contactDto.getPhoneNumber());
        assertEquals(contact.getWedding().getId(), contactDto.getWeddingDto().getId());
    }
}