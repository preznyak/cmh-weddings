package hu.preznyak.cmhweddings.web.controller;

import hu.preznyak.cmhweddings.services.WeddingService;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/wedding")
@RestController
public class WeddingController {

    private final WeddingService weddingService;

    public WeddingController(WeddingService weddingService) {
        this.weddingService = weddingService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<WeddingDto>> findAll(){
        List<WeddingDto> resultList = weddingService.findAll();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/{weddingId}")
    public ResponseEntity<WeddingDto> findById(@PathVariable("weddingId") UUID weddingId) {
        WeddingDto result = weddingService.findById(weddingId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WeddingDto> saveWedding(@Valid @RequestBody WeddingDto newWeddingDto) {
        WeddingDto saved = weddingService.save(newWeddingDto);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{weddingId}")
    public ResponseEntity<WeddingDto> updateWedding(@PathVariable("weddingId") UUID weddingId,
                                                    @Valid @RequestBody WeddingDto updatedWeddingDto) {
        return new ResponseEntity<>(weddingService.update(weddingId, updatedWeddingDto), HttpStatus.OK);
    }

    @DeleteMapping("/{weddingId}")
    public ResponseEntity deleteWedding(@PathVariable("weddingId") UUID weddingId) {
        weddingService.delete(weddingId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
