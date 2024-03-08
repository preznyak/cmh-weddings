package hu.preznyak.cmhweddings.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contact {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String emailAddress;

    @Column(length = 20)
    private String phoneNumber;
}
