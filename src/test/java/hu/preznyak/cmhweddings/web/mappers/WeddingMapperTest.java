package hu.preznyak.cmhweddings.web.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.web.model.WeddingDto;
import hu.preznyak.cmhweddings.web.model.enums.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WeddingMapperTest {

    @Autowired
    WeddingMapper weddingMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void weddingDtoToWedding() throws IOException {
        //given
        String weddingDtoJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/wedding.json")));
        WeddingDto weddingDto = objectMapper.readValue(weddingDtoJson, WeddingDto.class);

        //when
        Wedding wedding = weddingMapper.weddingDtoToWedding(weddingDto);

        //then
        assertEquals(weddingDto.getId(), wedding.getId());
        assertEquals(weddingDto.getBrideName(), wedding.getBrideName());
        assertEquals(weddingDto.getGroomName(), wedding.getGroomName());
        assertEquals(weddingDto.getLocation(), wedding.getLocation());
        assertEquals(weddingDto.getDate(), wedding.getDate());
        assertEquals(weddingDto.getPrice(), wedding.getPrice());
        assertEquals(weddingDto.getCurrency(), Currency.valueOf(wedding.getCurrency()));

    }

    @Test
    void weddingToWeddingDto() throws IOException {
        //given
        String weddingJson = new String(Files.readAllBytes(Paths.get("src/test/resources/test-files/wedding.json")));
        Wedding wedding = objectMapper.readValue(weddingJson, Wedding.class);

        //when
        WeddingDto weddingDto = weddingMapper.weddingToWeddingDto(wedding);

        //then
        assertEquals(wedding.getId(), weddingDto.getId());
        assertEquals(wedding.getBrideName(), weddingDto.getBrideName());
        assertEquals(wedding.getGroomName(), weddingDto.getGroomName());
        assertEquals(wedding.getLocation(), weddingDto.getLocation());
        assertEquals(wedding.getDate(), weddingDto.getDate());
        assertEquals(wedding.getPrice(), weddingDto.getPrice());
        assertEquals(wedding.getCurrency(), weddingDto.getCurrency().name());
    }
}