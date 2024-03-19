package hu.preznyak.cmhweddings.utils;

import hu.preznyak.cmhweddings.web.model.WeddingDto;

import java.time.format.DateTimeFormatter;

public class CodeGenerator {
    public static String generate(WeddingDto weddingDto) {
        return new StringBuilder()
                .append(weddingDto.getGroomName().substring(0,3).toUpperCase())
                .append(weddingDto.getBrideName().substring(0,3).toUpperCase())
                .append(weddingDto.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .toString();
    }
}
