package hu.preznyak.cmhweddings.web.controller;

import hu.preznyak.cmhweddings.services.WeddingService;
import hu.preznyak.cmhweddings.web.model.Wedding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/wedding")
@RestController
public class WeddingController {

    private WeddingService weddingService;

    public WeddingController(WeddingService weddingService) {
        this.weddingService = weddingService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Wedding>> findAll(){
        return new ResponseEntity<>(weddingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{weddingId}")
    public ResponseEntity<Wedding> findById(@PathVariable("weddingId") UUID weddingId) {
        return new ResponseEntity<>(weddingService.findById(weddingId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wedding> saveWedding(@RequestBody Wedding newWedding) {
        return new ResponseEntity(weddingService.save(newWedding), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Wedding> updateWedding(@RequestBody Wedding updatedWedding) {
        return new ResponseEntity<>(weddingService.update(updatedWedding), HttpStatus.OK);
    }

    @DeleteMapping("/{weddingId}")
    public ResponseEntity deleteWedding(@PathVariable("weddingId") UUID weddingId) {
        weddingService.delete(weddingId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
