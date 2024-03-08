package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.repositories.WeddingRepository;
import hu.preznyak.cmhweddings.web.mappers.WeddingMapper;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class WeddingServiceImpl implements WeddingService {

    private final WeddingRepository weddingRepository;
    private final WeddingMapper weddingMapper;

    @Override
    public List<WeddingDto> findAll() {
        val weddingList = weddingRepository.findAll();
        List<WeddingDto> weddingDtoList = new ArrayList<>(weddingList.size());
        weddingList.forEach(wedding -> weddingDtoList.add(weddingMapper.weddingToWeddingDto(wedding)));
        return weddingDtoList;
    }

    @Override
    public WeddingDto findById(UUID weddingId) {
        Optional<Wedding> wedding = weddingRepository.findById(weddingId);
        return wedding.map(weddingMapper::weddingToWeddingDto).orElse(null); // todo - fix the orElse part
    }

    @Override
    public WeddingDto save(WeddingDto newWeddingDto) {
        Wedding toSave = weddingMapper.weddingDtoToWedding(newWeddingDto);
        return weddingMapper.weddingToWeddingDto(weddingRepository.save(toSave));
    }

    @Override
    public WeddingDto update(UUID weddingId, WeddingDto updatedWeddingDto) {
        Wedding saved = weddingRepository.findById(weddingId).orElse(null); // todo
        if (Objects.isNull(saved)) {
            return null; // todo
        } else {
            saved.setDate(updatedWeddingDto.getDate());
            saved.setLocation(updatedWeddingDto.getLocation());
            saved.setPrice(updatedWeddingDto.getPrice());
            saved.setCurrency(updatedWeddingDto.getCurrency().name());
        }

        return weddingMapper.weddingToWeddingDto(weddingRepository.save(saved));
    }

    @Override
    public void delete(UUID weddingId) {
        weddingRepository.deleteById(weddingId);
    }
}
