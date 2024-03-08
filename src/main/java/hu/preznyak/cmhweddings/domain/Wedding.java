package hu.preznyak.cmhweddings.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
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

    @Column(length = 50, nullable = false)
    private String brideName;

    @Column(length = 50, nullable = false)
    private String groomName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(length = 20)
    private String style;
}