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
        List<Wedding> resultList = weddingService.findAll();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/{weddingId}")
    public ResponseEntity<Wedding> findById(@PathVariable("weddingId") UUID weddingId) {
        Wedding result = weddingService.findById(weddingId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wedding> saveWedding(@RequestBody Wedding newWedding) {
        Wedding saved = weddingService.save(newWedding);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{weddingId}")
    public ResponseEntity<Wedding> updateWedding(@PathVariable("weddingId") UUID weddingId,
                                                 @RequestBody Wedding updatedWedding) {
        return new ResponseEntity<>(weddingService.update(weddingId, updatedWedding), HttpStatus.OK);
    }

    @DeleteMapping("/{weddingId}")
    public ResponseEntity deleteWedding(@PathVariable("weddingId") UUID weddingId) {
        weddingService.delete(weddingId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
