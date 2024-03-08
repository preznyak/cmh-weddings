package hu.preznyak.cmhweddings.web.mappers;

import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface WeddingMapper {

    Wedding weddingDtoToWedding(WeddingDto weddingDto);

    WeddingDto weddingToWeddingDto(Wedding wedding);
}
