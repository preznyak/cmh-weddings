package hu.preznyak.cmhweddings.web.mappers;

import hu.preznyak.cmhweddings.domain.Contact;
import hu.preznyak.cmhweddings.web.model.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ContactMapper {

    @Mapping(target = "wedding.id",source = "contactDto.weddingDtoId")
    Contact contactDtoToContact(ContactDto contactDto);

    @Mapping(target = "weddingDtoId",source = "contact.wedding.id")
    ContactDto contactToContactDto(Contact contact);
}
