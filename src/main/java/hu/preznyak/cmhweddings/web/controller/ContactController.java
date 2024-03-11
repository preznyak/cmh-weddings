package hu.preznyak.cmhweddings.web.controller;

import hu.preznyak.cmhweddings.web.model.ContactDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/contact")
@RestController
public class ContactController {

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contactId") UUID contactId) {
        return new ResponseEntity<>(ContactDto.builder().id(contactId).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto contactDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@PathVariable("contactId") UUID contactId,
                              @Valid @RequestBody ContactDto contactDto) {
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("contactId") UUID contactId) {

    }
}
