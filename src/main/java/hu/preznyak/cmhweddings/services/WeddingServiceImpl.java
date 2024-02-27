package hu.preznyak.cmhweddings.services;

import hu.preznyak.cmhweddings.web.model.Wedding;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class WeddingServiceImpl implements WeddingService{
    @Override
    public List<Wedding> findAll() {
        return Arrays.asList(
                Wedding.builder()
                        .id(UUID.randomUUID())
                        .brideName("The Bride")
                        .groomName("The Groom")
                        .date(LocalDate.now())
                        .location("The Location")
                        .build());
    }
}
