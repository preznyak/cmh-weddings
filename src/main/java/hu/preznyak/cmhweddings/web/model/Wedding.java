package hu.preznyak.cmhweddings.web.model;

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
public class Wedding {
    private UUID id;
    private String brideName;
    private String groomName;
    private String location;
    private LocalDate date;
}
