package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.repositories.WeddingRepository;
import hu.preznyak.cmhweddings.utils.CodeGenerator;
import hu.preznyak.cmhweddings.web.exception.ErrorCode;
import hu.preznyak.cmhweddings.web.mappers.WeddingMapper;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        val weddingDto = wedding.map(weddingMapper::weddingToWeddingDto)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WEDDING_NOT_FOUND));
        return weddingDto;
    }

    @Override
    public WeddingDto save(WeddingDto newWeddingDto) {
        newWeddingDto.setCode(CodeGenerator.generate(newWeddingDto));
        Wedding toSave = weddingMapper.weddingDtoToWedding(newWeddingDto);
        return weddingMapper.weddingToWeddingDto(weddingRepository.save(toSave));
    }

    @Override
    public WeddingDto update(UUID weddingId, WeddingDto updatedWeddingDto) {
        Wedding saved = weddingRepository.findById(weddingId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WEDDING_NOT_FOUND));

        saved.setDate(updatedWeddingDto.getDate());
        saved.setLocation(updatedWeddingDto.getLocation());
        saved.setPrice(updatedWeddingDto.getPrice());
        saved.setCurrency(updatedWeddingDto.getCurrency().name());

        return weddingMapper.weddingToWeddingDto(weddingRepository.save(saved));
    }

    @Override
    public void delete(UUID weddingId) {
        weddingRepository.deleteById(weddingId);
    }
}
