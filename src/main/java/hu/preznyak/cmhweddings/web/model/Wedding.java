package hu.preznyak.cmhweddings.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Wedding {
    @Id
    private UUID id;
    private String brideName;
    private String groomName;
    private String location;
    private String contactInfo;
    private LocalDate date;
    private Double price;
}
