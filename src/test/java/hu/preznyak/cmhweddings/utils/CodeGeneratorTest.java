package hu.preznyak.cmhweddings.utils;

import hu.preznyak.cmhweddings.web.model.WeddingDto;
import hu.preznyak.cmhweddings.web.model.enums.CMStyle;
import hu.preznyak.cmhweddings.web.model.enums.Currency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    private static final int LENGTH_OF_A_CODE = 14;

    @Test
    void generate() {
        //given
        WeddingDto weddingDto = WeddingDto.builder()
                .brideName("Mary Jane Watson")
                .groomName("Peter Benjamin Parker")
                .location("Marvel Universe")
                .date(LocalDate.of(2024, 8, 22))
                .price(new BigDecimal(400))
                .currency(Currency.EUR)
                .style(CMStyle.MODERN)
                .build();

        //when
        String generatedCode = CodeGenerator.generate(weddingDto);

        //then
        assertThat(generatedCode.length() == LENGTH_OF_A_CODE);
        assertEquals(weddingDto.getGroomName().substring(0,3).toUpperCase(), generatedCode.substring(0,3));
        assertEquals(weddingDto.getBrideName().substring(0,3).toUpperCase(), generatedCode.substring(3,6));
        assertEquals(weddingDto.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")), generatedCode.substring(6,14));


    }
}