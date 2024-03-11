package hu.preznyak.cmhweddings.web.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {

    private UUID id;

    @Size(min = 5, max = 50, message = "Name length should be between 5 and 50 characters.")
    @NotBlank
    private String name;

    @Email(message = "Email should be valid.")
    private String emailAddress;

    @Size(min = 8, max = 20, message = "Phone number length should be between 8 and 20 characters.")
    private String phoneNumber;

    @NotNull
    private UUID weddingDtoId;
}
