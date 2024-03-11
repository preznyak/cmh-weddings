package hu.preznyak.cmhweddings.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException {
    private String message;
    private String code;
}
