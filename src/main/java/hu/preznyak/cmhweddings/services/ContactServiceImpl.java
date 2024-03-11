package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.ContactDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public List<ContactDto> findAll() {
        return null;
    }

    @Override
    public ContactDto findById(UUID contactId) {
        return null;
    }

    @Override
    public ContactDto save(ContactDto newContactDto) {
        return null;
    }

    @Override
    public ContactDto update(UUID contactId, ContactDto updatedContactDto) {
        return null;
    }

    @Override
    public void delete(UUID contactId) {

    }
}
