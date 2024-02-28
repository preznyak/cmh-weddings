package hu.preznyak.cmhweddings.web.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Document(collection = "wedding")
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
