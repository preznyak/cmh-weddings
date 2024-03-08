package hu.preznyak.cmhweddings.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Wedding {
    @Id
    @UuidGenerator
    private UUID id;
    private String brideName;
    private String groomName;
    private String location;
    private String contactInfo;
    private LocalDate date;
    private Double price;
}
