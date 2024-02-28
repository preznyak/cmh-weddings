package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.Wedding;

import java.util.List;
import java.util.UUID;

public interface WeddingService {
    List<Wedding> findAll();

    Wedding findById(UUID weddingId);

    Wedding save(Wedding newWedding);

    Wedding update(Wedding updatedWedding);

    void delete(UUID weddingId);
}
