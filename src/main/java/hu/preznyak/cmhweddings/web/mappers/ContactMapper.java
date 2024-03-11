package hu.preznyak.cmhweddings.web.mappers;

import hu.preznyak.cmhweddings.domain.Contact;
import hu.preznyak.cmhweddings.web.model.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {WeddingMapper.class})
public interface ContactMapper {

    @Mapping(target = "wedding",source = "contactDto.weddingDto")
    Contact contactDtoToContact(ContactDto contactDto);

    @Mapping(target = "weddingDto",source = "contact.wedding")
    ContactDto contactToContactDto(Contact contact);
}
