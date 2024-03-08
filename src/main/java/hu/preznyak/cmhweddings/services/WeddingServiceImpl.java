package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.repositories.WeddingRepository;
import hu.preznyak.cmhweddings.domain.Wedding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class WeddingServiceImpl implements WeddingService{

    @Autowired
    private WeddingRepository weddingRepository;

    @Override
    public List<Wedding> findAll() {
        return weddingRepository.findAll();
    }

    @Override
    public Wedding findById(UUID weddingId) {
        return weddingRepository.findById(weddingId).orElse(null); // todo
    }

    @Override
    public Wedding save(Wedding newWedding) {
        return weddingRepository.save(newWedding);
    }

    @Override
    public Wedding update(UUID weddingId, Wedding updatedWedding) {
        Wedding saved = weddingRepository.findById(weddingId).orElse(null); // todo
        if (Objects.isNull(saved)) {

        } else {
            saved.setDate(updatedWedding.getDate());
            saved.setLocation(updatedWedding.getLocation());
            saved.setPrice(updatedWedding.getPrice());
            saved.setCurrency(updatedWedding.getCurrency());
        }

        return weddingRepository.save(saved);
    }

    @Override
    public void delete(UUID weddingId) {
        weddingRepository.deleteById(weddingId);
    }
}
