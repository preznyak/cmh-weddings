package hu.preznyak.cmhweddings.web.controller;

import hu.preznyak.cmhweddings.services.ContactService;
import hu.preznyak.cmhweddings.web.model.ContactDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/contact")
@RestController
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contactId") UUID contactId) {
        ContactDto contactDto = contactService.findById(contactId);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto contactDto) {
        ContactDto saved = contactService.save(contactDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable("contactId") UUID contactId,
                              @Valid @RequestBody ContactDto contactDto) {
        ContactDto updated = contactService.update(contactId, contactDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("contactId") UUID contactId) {
        contactService.delete(contactId);
    }

    @GetMapping("/byWeddingId/{weddingId}")
    public ResponseEntity<ContactDto> findByWeddingId(@PathVariable("weddingId") UUID weddingId) {
        ContactDto contactDto = contactService.findByWeddingId(weddingId);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);

    }
}
