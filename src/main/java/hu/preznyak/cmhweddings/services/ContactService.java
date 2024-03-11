package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.ContactDto;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    List<ContactDto> findAll();

    ContactDto findById(UUID contactId);

    ContactDto save(ContactDto newContactDto);

    ContactDto update(UUID contactId, ContactDto updatedContactDto);

    void delete(UUID contactId);

    ContactDto findByWeddingId(UUID weddingId);
}
