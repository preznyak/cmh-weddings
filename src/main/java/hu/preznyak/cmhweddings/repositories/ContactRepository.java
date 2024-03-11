package hu.preznyak.cmhweddings.repositories;

import hu.preznyak.cmhweddings.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    Optional<Contact> findByWeddingId(UUID weddingId);
}
