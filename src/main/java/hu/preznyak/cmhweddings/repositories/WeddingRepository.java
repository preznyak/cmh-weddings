package hu.preznyak.cmhweddings.repositories;

import hu.preznyak.cmhweddings.domain.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WeddingRepository extends JpaRepository<Wedding, UUID> {
    Optional<Wedding> findByBrideNameAndGroomName(String brideName, String groomName);
}
