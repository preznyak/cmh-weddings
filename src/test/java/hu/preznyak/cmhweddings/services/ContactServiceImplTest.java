package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.domain.Contact;
import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.repositories.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ContactServiceImplTest {

    @MockBean
    ContactRepository contactRepository;

    @Autowired
    ContactServiceImpl contactService;

    @Test
    void givenNotPersistedUuid_whenFindById_thenEntityNotFoundExceptionIsThrown() {
        //given
        when(contactRepository.findById(any())).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class,
                () -> contactService.findById(UUID.randomUUID()),
                "Expected to throw EntityNotFoundException, but it didn't");
    }

    @Test
    void givenPersistedUuid_whenFindById_thenValueReturned() {
        //given
        UUID id = UUID.randomUUID();
        Contact contact = Contact.builder()
                .id(id)
                .name("Test Name")
                .phoneNumber("+3655666")
                .wedding(new Wedding())
                .build();
        when(contactRepository.findById(id)).thenReturn(Optional.of(contact));

        //then
        assertDoesNotThrow(() -> contactService.findById(id));
    }

}