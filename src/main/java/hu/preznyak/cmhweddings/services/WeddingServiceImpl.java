package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.Wedding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
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
}
