package hu.preznyak.cmhweddings.web.model;

import hu.preznyak.cmhweddings.web.model.enums.CMStyle;
import hu.preznyak.cmhweddings.web.model.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeddingDto {

    private UUID id;

    private String code;

    @Size(min = 5, max = 50)
    @NotBlank
    private String brideName;

    @Size(min = 5, max = 50)
    @NotBlank
    private String groomName;

    @NotBlank
    private String location;

    @NotNull
    private LocalDate date;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Currency currency;

    private CMStyle style;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastUpdatedDate;

}
