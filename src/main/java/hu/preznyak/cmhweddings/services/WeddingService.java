package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.WeddingDto;

import java.util.List;
import java.util.UUID;

public interface WeddingService {
    List<WeddingDto> findAll();

    WeddingDto findById(UUID weddingId);

    WeddingDto save(WeddingDto newWeddingDto);

    WeddingDto update(UUID weddingId, WeddingDto updatedWeddingDto);

    void delete(UUID weddingId);
}
