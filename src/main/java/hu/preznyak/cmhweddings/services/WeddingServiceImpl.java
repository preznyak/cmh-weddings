package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.Wedding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WeddingServiceImpl implements WeddingService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Wedding> findAll() {
        return mongoTemplate.findAll(Wedding.class);
    }

    @Override
    public Wedding findById(UUID weddingId) {
        return mongoTemplate.findById(weddingId, Wedding.class);
    }

    @Override
    public Wedding save(Wedding newWedding) {
        return mongoTemplate.insert(newWedding);
    }

    @Override
    public Wedding update(Wedding updatedWedding) {
        return mongoTemplate.save(updatedWedding);
    }

    @Override
    public void delete(UUID weddingId) {
        Wedding toDelete = Wedding.builder().build();
        toDelete.setId(weddingId);
        mongoTemplate.remove(toDelete);
    }
}
