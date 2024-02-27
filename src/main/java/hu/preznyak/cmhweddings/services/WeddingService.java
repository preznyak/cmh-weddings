package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.Wedding;

import java.util.List;

public interface WeddingService {
    public List<Wedding> findAll();
}
