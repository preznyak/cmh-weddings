package hu.preznyak.cmhweddings.web.controller;

import hu.preznyak.cmhweddings.services.WeddingService;
import hu.preznyak.cmhweddings.web.model.Wedding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
