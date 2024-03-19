package hu.preznyak.cmhweddings.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints =
    @UniqueConstraint(columnNames = {"bride_name", "groom_name"})
)
public class Wedding {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true)
    private String code;

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

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastUpdatedDate;
}
