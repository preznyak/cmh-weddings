package hu.preznyak.cmhweddings.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "wedding")
public class Wedding {
    @Id
    private UUID id;
    private String brideName;
    private String groomName;
    private String location;
    private LocalDate date;
}
