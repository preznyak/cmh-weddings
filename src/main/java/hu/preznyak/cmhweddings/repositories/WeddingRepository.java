package hu.preznyak.cmhweddings.repositories;

import hu.preznyak.cmhweddings.domain.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeddingRepository extends JpaRepository<Wedding, UUID> {
}
