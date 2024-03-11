package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.domain.Contact;
import hu.preznyak.cmhweddings.repositories.ContactRepository;
import hu.preznyak.cmhweddings.web.exception.ErrorCode;
import hu.preznyak.cmhweddings.web.mappers.ContactMapper;
import hu.preznyak.cmhweddings.web.model.ContactDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public List<ContactDto> findAll() {
        List<Contact> contactList = contactRepository.findAll();
        List<ContactDto> contactDtoList = new ArrayList<>(contactList.size());

        contactList.forEach(contact -> contactDtoList.add(contactMapper.contactToContactDto(contact)));

        return contactDtoList;
    }

    @Override
    public ContactDto findById(UUID contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        val contactDto = contact.map(contactMapper::contactToContactDto)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CONTACT_NOT_FOUND));
        return contactDto;
    }

    @Override
    public ContactDto save(ContactDto newContactDto) {
        Contact saved = contactRepository.save(contactMapper.contactDtoToContact(newContactDto));
        return contactMapper.contactToContactDto(saved);
    }

    @Override
    public ContactDto update(UUID contactId, ContactDto updatedContactDto) {
        Contact toUpdate = contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CONTACT_NOT_FOUND));

        toUpdate.setName(updatedContactDto.getName());
        toUpdate.setEmailAddress(updatedContactDto.getEmailAddress());
        toUpdate.setPhoneNumber(updatedContactDto.getPhoneNumber());

        return contactMapper.contactToContactDto(contactRepository.save(toUpdate));
    }

    @Override
    public void delete(UUID contactId) {
        contactRepository.deleteById(contactId);
    }

    @Override
    public ContactDto findByWeddingId(UUID weddingId) {
        Contact contact = contactRepository.findByWeddingId(weddingId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CONTACT_NOT_FOUND));
        return contactMapper.contactToContactDto(contact);
    }
}
